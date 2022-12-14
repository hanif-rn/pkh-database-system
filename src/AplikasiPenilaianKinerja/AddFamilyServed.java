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
import javafx.scene.control.ComboBox;
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
public class AddFamilyServed extends Application {
    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Add Families Served Data");
    Label indolbl = new Label("Tambahkan Data Keluarga yang Didampingi");
    Button opt1 = new Button("Add Details\nMasukkan Detil");
    Button back = new Button("Back/Kembali");  
    Label distP = new Label("District/Kecamatan");
    ComboBox dist = new ComboBox();
    Label nameP = new Label("Name/Nama");
    ComboBox name = new ComboBox();
    Label sbdistP = new Label("Subdistrict/Kelurahan");
    TextField sbdist = new TextField();
    Label famP = new Label("Families Served/Jumlah Keluarga yang Didampingi");
    TextField fam = new TextField();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);
    int count = 1;
    String mult ="";
    
    public void setLayout(){
        dist.setEditable(true);
        name.setEditable(true);
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        ColumnConstraints cc0 = new ColumnConstraints(325);
        ColumnConstraints cc1 = new ColumnConstraints(200);
        ColumnConstraints cc2 = new ColumnConstraints(375);
        ColumnConstraints cc3 = new ColumnConstraints(150);
        header.getColumnConstraints().addAll(cc0, cc1);
        gp.getColumnConstraints().addAll(cc2, cc3);
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
        sbdistP.setFont(tnr16);
        famP.setFont(tnr16);
        opt1.setFont(tnr16);
        back.setFont(tnr16);
        String[] content1 = pkh.getDistricts();
        for (int i = 0; i < 1000; i++) {
            if(content1[i]!=null){
                dist.getItems().add(content1[i]);
            }
        }
        header.add(englbl, 0, 0, 1, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(pkhP, 1, 0, 2, 2);
        gp.addRow(0, distP);
        gp.addRow(1, dist);
        gp.addRow(2, nameP);
        gp.addRow(3, name);
        gp.addRow(4, sbdistP);
        gp.addRow(5, sbdist);
        gp.addRow(6, famP);
        gp.addRow(7, fam);
        opt1.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        gp.add(opt1, 1, 2, 1, 2);
        gp.add(back, 1, 5, 1, 1);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        main.addRow(0, header);
        main.addRow(1, gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        dist.setOnAction(e -> {
            name.getItems().clear();
            String[] content= pkh.getWorkerNames(dist.getValue().toString());
                    System.out.println(dist.getValue().toString());
            for (int i = 0; i < 1000; i++) {
                if(content[i]!=null){
                    name.getItems().add(content[i]);
                }
            }
        });
        opt1.setOnAction(e -> {
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
                });
        back.setOnAction(e -> {
            AddToDatabase m3 = new AddToDatabase();
            m3.setLayout();
            m3.start(primaryStage);
        });
        Scene scene = new Scene(main,625,650);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    public void addToDB(){
        pkh.addFamilies(name.getValue().toString(), dist.getValue().toString(), sbdist.getText(), 
                Integer.parseInt(fam.getText()));
        
    }

}
