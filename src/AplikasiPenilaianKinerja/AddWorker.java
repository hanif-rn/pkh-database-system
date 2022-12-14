/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author 53792
 */
public class AddWorker extends Application {
    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Add Worker");
    Label indolbl = new Label("Tambahkan Informasi Pendamping");
    Button opt1 = new Button("Add Details\nMasukkan Detil");
    Button back = new Button("Back/Kembali");  
    Label nameP = new Label("Name/Nama");
    TextField name = new TextField();
    Label distP = new Label("District/Kecamatan");
    TextField dist = new TextField();
    //Label sbdistP = new Label("Subdistrict/Kelurahan");
    //TextField sbdist = new TextField();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);
    int count = 1;
    String mult ="";
    
/*
*   This method sets the layout and aesthetics of the 
*   program through use of JavaFX features.
*/
    public void setLayout(){
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        ColumnConstraints cc0 = new ColumnConstraints(275);
        ColumnConstraints cc1 = new ColumnConstraints(200);
        header.getColumnConstraints().addAll(cc0, cc1);
        gp.getColumnConstraints().addAll(cc0, cc1);
        gp.setVgap(15);
        gp.setHgap(15);
        pkhP.setFitWidth(200);
        pkhP.setFitHeight(107);
        gp.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        main.setAlignment(Pos.CENTER);
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        nameP.setFont(tnr16);
        distP.setFont(tnr16);
        opt1.setFont(tnr16);
        back.setFont(tnr16);
        header.add(englbl, 0, 0, 1, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(pkhP, 1, 0, 2, 2);
        gp.addRow(0, nameP);
        gp.addRow(1, name);
        gp.addRow(2, distP);
        gp.addRow(3, dist);
        opt1.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        gp.add(opt1, 1, 2, 1, 2);
        gp.addRow(4, back);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        main.addRow(0, header);
        main.addRow(1, gp);   
    }
    
    @Override
    public void start(Stage primaryStage) {
        opt1.setOnAction(e -> {
            if(!name.getText().equals("") || !dist.getText().equals("")){
                addToDB();
                GridPane pu = new GridPane();
                Popup popup = new Popup(); 
                Label text = new Label("Success/Berhasil");
                Button b = new Button("Close/Tutup");
                pu.setPadding(new Insets(15, 15, 15, 15));
                pu.addRow(0, text);
                pu.addRow(1, b);
                pu.setVgap(5);
                text.setFont(tnr16);
                b.setFont(tnr16);
                pu.setEffect(new DropShadow());
                b.setStyle("-fx-background-color: royalblue;"
                    + " -fx-text-fill: white;");
                text.setStyle(" -fx-background-color: orange;"
                    + " -fx-text-fill: white;");
                pu.setStyle(" -fx-background-color: orange;"); 
                popup.getContent().add(pu); 
                popup.show(primaryStage);
                b.setOnAction(x -> {
                    popup.hide();
                });
            } else{
                GridPane pu = new GridPane();
                Popup popup = new Popup(); 
                Label text = new Label("There is an empty field");
                Label text2 = new Label("Ada bagian tidak terisi");
                Button b = new Button("Close/Tutup");
                pu.setPadding(new Insets(15, 15, 15, 15));
                pu.addRow(0, text);
                pu.addRow(1, text2);
                pu.addRow(2, b);
                pu.setVgap(5);
                text.setFont(tnr16);
                text2.setFont(tnr16);
                b.setFont(tnr16);
                pu.setEffect(new DropShadow());
                b.setStyle("-fx-background-color: royalblue;"
                    + " -fx-text-fill: white;");
                text.setStyle(" -fx-background-color: orange;"
                    + " -fx-text-fill: white;");
                text2.setStyle(" -fx-background-color: orange;"
                    + " -fx-text-fill: white;");
                pu.setStyle(" -fx-background-color: orange;"); 
                popup.getContent().add(pu); 
                popup.show(primaryStage);
                b.setOnAction(x -> {
                    popup.hide();
                });
            }
            
                });
        back.setOnAction(e -> {
            AddToDatabase m3 = new AddToDatabase();
            m3.setLayout();
            m3.start(primaryStage);
        });
        Scene scene = new Scene(main,625,425);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void addToDB(){
        pkh.addWorkerData(0, name.getText(), dist.getText());
        mult="";
        
    }

}
