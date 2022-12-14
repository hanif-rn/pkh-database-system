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
public class FamServedData extends Application {
    
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Data of Families Served");
    Label indolbl = new Label("Data Jumlah KPM per SDM");
    Button opt1 = new Button("Excel");
    Button back = new Button("Back/Kembali");  
    TableView tb;
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    
    public void setLayout(){
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        ColumnConstraints cc0 = new ColumnConstraints(200);
        ColumnConstraints cc1 = new ColumnConstraints(200);
        ColumnConstraints cc2 = new ColumnConstraints(200);
        gp.getColumnConstraints().addAll(cc0, cc1, cc2);
        header.getColumnConstraints().addAll(cc0, cc1, cc2);
        gp.setVgap(15);
        gp.setHgap(15);
        pkhP.setFitWidth(200);
        pkhP.setFitHeight(107);
        gp.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        main.setAlignment(Pos.CENTER);
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        opt1.setFont(tnr16);
        back.setFont(tnr16);
        header.add(englbl, 0, 0, 2, 1);
        header.add(indolbl, 0, 1, 2, 1);
        header.add(pkhP, 2, 0, 1, 2);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        gp.add(back, 0, 3, 1, 1);
        gp.add(opt1, 1, 3, 1, 1);
        createTable();
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt1.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        main.addRow(0, header);
        main.addRow(1, gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }
    
    @Override
    public void start(Stage primaryStage) {
        opt1.setOnAction(e -> generateExcel());
        back.setOnAction(e -> {
            MainMenu m2 = new MainMenu();
            m2.setLayout();
            m2.start(primaryStage);
        });
        Scene scene = new Scene(main,750,550);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createTable(){
        
        tb = new TableView();
        TableColumn<String, FamiliesServed> col1 = new TableColumn<>("District/Kecamatan");
        col1.setCellValueFactory(new PropertyValueFactory<>("District"));
        TableColumn<String, FamiliesServed> col2 = new TableColumn<>("Name/Nama");
        col2.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<String, FamiliesServed> col3 = new TableColumn<>("SubDistrict/Kelurahan");
        col3.setCellValueFactory(new PropertyValueFactory<>("Subdistrict"));
        TableColumn<String, FamiliesServed> col4 = new TableColumn<>("Total (SubDistrict/Kelurahan)");
        col4.setCellValueFactory(new PropertyValueFactory<>("SbDFamilies"));
        TableColumn<String, FamiliesServed> col5 = new TableColumn<>("Total (Person/Pendamping)");
        col5.setCellValueFactory(new PropertyValueFactory<>("TotalFamilies"));
        TableColumn<String, FamiliesServed> col6 = new TableColumn<>("Total (District/Kecamatan)");
        col6.setCellValueFactory(new PropertyValueFactory<>("DistFamilies"));
        
        tb.getColumns().addAll(col1,col2,col3,col4,col5,col6);
        FamiliesServed[] fs = pkh.famServed();
        for (int i = 0; i < fs.length; i++) {
            if(fs[i]!=null){
                tb.getItems().add(fs[i]);
            }
        }
        //int[] x = {9,8,7,8,5};
        //tb.getItems().add(new WorkerWeek("SKZ", "Seungmin", x));
        gp.add(tb, 0, 0, 3, 3);
        
    }
    
    public void generateExcel(){
        CreateExcel ce = new CreateExcel();
        ce.createFS(pkh.famServed());
    }
    


}
