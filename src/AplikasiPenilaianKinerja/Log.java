/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class Log extends Application {
    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Edit Log");
    Label indolbl = new Label("Log Edit");

    Button back = new Button("Back/Kembali");  
    TableView tb= new TableView();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);
    
    public void setLayout(){
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        
        ColumnConstraints cc0 = new ColumnConstraints(100);
        gp.getColumnConstraints().addAll(cc0, cc0, cc0,cc0);
        header.getColumnConstraints().addAll(cc0, cc0, cc0,cc0);
        gp.setVgap(15);
        gp.setHgap(15);
        pkhP.setFitWidth(200);
        pkhP.setFitHeight(107);
        gp.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        main.setAlignment(Pos.CENTER);
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        
        back.setFont(tnr16);
        header.add(englbl, 0, 0, 1, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(pkhP, 2, 0, 1, 2);
        gp.add(tb, 0, 0, 4, 3);
        gp.add(back, 0, 3, 2, 1);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        main.addRow(0, header);
        main.addRow(1, gp);
        createTable();
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }
    
    @Override
    public void start(Stage primaryStage) {
        setLayout();
        back.setOnAction(e -> {
            MainMenu m2 = new MainMenu();
            m2.setLayout();
            m2.start(primaryStage);
        });
        Scene scene = new Scene(main,500,700);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    //Describes the programmatic entering of values to the JavaFX TableView
    public void createTable(){
        tb = new TableView();
        TableColumn<String, LogContent> col1 = new TableColumn<>("Edit");
        col1.setCellValueFactory(new PropertyValueFactory<>("edit"));
        TableColumn<String, WorkerWeek> col2 = new TableColumn<>("Editor");
        col2.setCellValueFactory(new PropertyValueFactory<>("user"));
        TableColumn<String, WorkerWeek> col3 = new TableColumn<>("Time/Waktu");
        col3.setCellValueFactory(new PropertyValueFactory<>("date"));

        tb.getColumns().add(col1);
        tb.getColumns().add(col2);
        tb.getColumns().add(col3);
        
        LogContent[] lc = pkh.displayLog();
        for (int i = 0; i < lc.length; i++) {
            if(lc[i]!=null){
                tb.getItems().add(lc[i]);
                System.out.println(lc[i]);
            }
        }
        gp.add(tb, 0, 0, 4, 3);
    }
    


}
