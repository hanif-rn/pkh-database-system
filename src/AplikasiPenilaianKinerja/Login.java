/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author 53792
 */
public class Login extends Application {
    GridPane main = new GridPane();
    GridPane logos = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Worker Evaluation App");
    Label indolbl = new Label("Aplikasi Penilaian Kinerja Pendamping");
    Label unp = new Label("Username/Nama Pengguna");
    Label pwp = new Label("Password/Kata Sandi");
    Label invalid = new Label("INVALID CREDENTIALS\nEMAIL ATAU PASSWORD SALAH");
    TextField un = new TextField();
    PasswordField pf = new PasswordField();
    Button lgin = new Button("Login/Masuk");  
    Font tnr24 = new Font("Tahoma", 24);
    Font tnr16 = new Font("Tahoma", 16);
    Font tnr13 = new Font("Tahoma", 13);

    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    Image kemensosI = new Image(this.getClass().getResourceAsStream("/kemensos.jpg"));
    Image wonogiriI = new Image(this.getClass().getResourceAsStream("/wonogiri.jpg"));
    ImageView pkh = new ImageView(pkhI);
    ImageView kemensos = new ImageView(kemensosI);
    ImageView wonogiri = new ImageView(wonogiriI);
    
    public void setLayout(){
        ColumnConstraints cc0 = new ColumnConstraints(250);
        ColumnConstraints cc1 = new ColumnConstraints(220);
        ColumnConstraints cc2 = new ColumnConstraints(150);
        ColumnConstraints cc3 = new ColumnConstraints(50);
        ColumnConstraints cc4 = new ColumnConstraints(130);
        pkh.setFitWidth(200);
        pkh.setFitHeight(107);
        kemensos.setFitWidth(129);
        kemensos.setFitHeight(107);
        wonogiri.setFitWidth(86);
        wonogiri.setFitHeight(107);
        logos.getColumnConstraints().addAll(cc2, cc1, cc3,cc4);
        logos.addRow(0,kemensos,pkh,new Label(),wonogiri);
        englbl.setMaxWidth(Double.MAX_VALUE);
        englbl.setAlignment(Pos.CENTER);
        indolbl.setMaxWidth(Double.MAX_VALUE);
        indolbl.setAlignment(Pos.CENTER);
        gp.getColumnConstraints().addAll(cc0, cc0);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);
        logos.setVgap(10);
        main.setVgap(10);
        main.setAlignment(Pos.CENTER);
        logos.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10, 10, 10, 10)); 
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        unp.setFont(tnr13);
        pwp.setFont(tnr13);
        lgin.setFont(tnr13);
        logos.add(englbl, 0, 1, 4, 1);
        logos.add(indolbl, 0, 2, 4, 1);
        gp.addRow(0, unp, un);
        gp.addRow(1, pwp, pf);
        lgin.setAlignment(Pos.CENTER);
        lgin.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        gp.add(lgin, 1, 2, 1, 1);
        main.addRow(0,logos);
        main.addRow(1,gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }
    
    @Override
    public void start(Stage primaryStage) {
        PKHDatabaseRef pkhref = new PKHDatabaseRef();
        setLayout();
        
        lgin.setOnAction(e -> {
            System.out.println(un.getText()+" " +pf.getText());
            if(pkhref.setCredentials(un.getText(), pf.getText())==true){
                MainMenu m2 = new MainMenu();
                m2.setLayout();
                m2.start(primaryStage);
            } else{
                GridPane pu = new GridPane();
                Popup popup = new Popup(); 
                Label text = new Label("INVALID CREDENTIALS\nEMAIL ATAU PASSWORD SALAH");
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
            }
            
        });
        
         Scene scene = new Scene(main,625,400);
         logos.setStyle("-fx-background-color: white;");
         main.setStyle("-fx-background-color: white;");
         gp.setStyle("-fx-background-color: whitesmoke;");
        
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
