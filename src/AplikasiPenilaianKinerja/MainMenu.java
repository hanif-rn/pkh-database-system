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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
public class MainMenu extends Application {
    
    GridPane main = new GridPane();
    GridPane gp = new GridPane();
    GridPane header = new GridPane();
    Label englbl = new Label("Main Menu");
    Label indolbl = new Label("Menu Utama");
    Label no1 = new Label("Add Workers/Activities\nTambah Pendamping/Aktivitas");
    Label no2 = new Label("Display Monthly Report\nTampilkan Laporan Bulanan");
    Label no3 = new Label("Display Yearly Report\nTampilkan Laporan Tahunan");
    Label no4 = new Label("Display Data on Families Served\nTampilkan Data KPM per SDM");
    Label no5 = new Label("Edit Log\nLog Edit");
    Button opt1 = new Button("Go/\nMasuk");
    Button opt2 = new Button("Go/\nMasuk");
    Button opt3 = new Button("Go/\nMasuk");
    Button opt4 = new Button("Go/\nMasuk");
    Button opt5 = new Button("Go/\nMasuk");
    
    Button lgout = new Button("Logout/Keluar");  
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    Font tnr14 = new Font("Tahoma", 14);
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkh = new ImageView(pkhI);
    
    public void setLayout(){
        ColumnConstraints cc0 = new ColumnConstraints(275);
        ColumnConstraints cc1 = new ColumnConstraints(75);
        ColumnConstraints cc2 = new ColumnConstraints(150);
        ColumnConstraints cc3 = new ColumnConstraints(200);
        pkh.setFitWidth(200);
        pkh.setFitHeight(107);
        gp.getColumnConstraints().addAll(cc0, cc1);
        header.getColumnConstraints().addAll(cc2, cc3);
        gp.setVgap(20);
        header.setVgap(15);
        gp.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        main.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        englbl.setFont(tnr24);
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        indolbl.setFont(tnr16);
        no1.setFont(tnr14);
        no2.setFont(tnr14);
        no3.setFont(tnr14);
        no4.setFont(tnr14);
        no5.setFont(tnr14);
        opt1.setFont(tnr14);
        opt2.setFont(tnr14);
        opt3.setFont(tnr14);
        opt4.setFont(tnr14);
        opt5.setFont(tnr14);
        opt1.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt2.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt3.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt4.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt5.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");        
        lgout.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        lgout.setFont(tnr16);
        header.add(englbl, 0, 0, 1, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(pkh, 1, 0, 2, 2);
        gp.addRow(0, no1, opt1);
        gp.addRow(1, no2, opt2);
        gp.addRow(2, no3, opt3);
        gp.addRow(3, no4, opt4);
        gp.addRow(4, no5, opt5);
        gp.add(lgout, 0, 5, 2, 1);
        main.addRow(0, header);
        main.addRow(1, gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }
    
    @Override
    public void start(Stage primaryStage) {
        opt1.setOnAction(e -> {
            AddToDatabase m3 = new AddToDatabase();
            m3.setLayout();
            m3.start(primaryStage);
        });
        opt2.setOnAction(e -> {
            MonthEval m6 = new MonthEval();
            m6.start(primaryStage);
        });
        opt3.setOnAction(e -> {
            YearEvalu m7 = new YearEvalu();
            m7.start(primaryStage);
        });
        opt4.setOnAction(e -> {
            FamServedData m9 = new FamServedData();
            m9.setLayout();
            m9.start(primaryStage);
        });
        opt5.setOnAction(e -> {
            Log m11 = new Log();
            m11.start(primaryStage);
        });
        lgout.setOnAction(e -> {
            Login m1 = new Login();
            m1.start(primaryStage);
        });
            
        Scene scene = new Scene(main,500,600);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */

}
