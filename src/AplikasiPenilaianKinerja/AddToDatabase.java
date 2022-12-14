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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author 53792
 */
public class AddToDatabase extends Application {
    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Add to Database");
    Label indolbl = new Label("Tambah informasi ke database");
    Label no1 = new Label("Add Worker/Tambah Pendamping");
    Label no2 = new Label("Add Activity/Tambah Aktivitas");
    Label no3 = new Label("Add Families/Tambah Keluarga Yang Didampingi");
    Button opt1 = new Button("Go/Masuk");
    Button opt2 = new Button("Go/Masuk");
    Button opt3 = new Button("Go/Masuk");
    Button back = new Button("Back/Kembali");  
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    Font tnr14 = new Font("Tahoma", 14);
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkh = new ImageView(pkhI);
    
    public void setLayout(){
        ColumnConstraints cc0 = new ColumnConstraints(250);
        ColumnConstraints cc1 = new ColumnConstraints(200);
        ColumnConstraints cc2 = new ColumnConstraints(350);
        ColumnConstraints cc3 = new ColumnConstraints(100);
        gp.getColumnConstraints().addAll(cc2, cc3);
        header.getColumnConstraints().addAll(cc0, cc1);
        gp.setVgap(20);
        header.setVgap(15);
        pkh.setFitWidth(200);
        pkh.setFitHeight(107);
        gp.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        main.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        opt1.setFont(tnr16);
        opt2.setFont(tnr16);
        no1.setFont(tnr14);
        no2.setFont(tnr14);
        back.setFont(tnr16);
        opt3.setFont(tnr16);
        no3.setFont(tnr14);
        opt1.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt2.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt3.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        header.add(englbl, 0, 0, 1, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(pkh, 1, 0, 2, 2);
        gp.addRow(0, no1, opt1);
        gp.addRow(1, no2, opt2);
        gp.addRow(2, no3, opt3);
        gp.add(back, 0, 3, 2, 1);
        main.addRow(0,header);
        main.addRow(1,gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }
    
    @Override
    public void start(Stage primaryStage) {

        opt1.setOnAction(e -> {
            AddWorker m5 = new AddWorker();
            m5.setLayout();
            m5.start(primaryStage);
        });
        opt2.setOnAction(e -> {
            AddActivity m4 = new AddActivity();
            m4.setLayout();
            m4.start(primaryStage);
        });
        opt3.setOnAction(e -> {
            AddFamilyServed m10 = new AddFamilyServed();
            m10.setLayout();
            m10.start(primaryStage);
        });
        back.setOnAction(e -> {
            MainMenu m2 = new MainMenu();
            m2.setLayout();
            m2.start(primaryStage);
        });
        Scene scene = new Scene(main,550,400);
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
    
}
