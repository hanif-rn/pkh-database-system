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
import javafx.scene.control.TextField;
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
public class MonthEval extends Application {
    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Monthly Evaluation");
    Label indolbl = new Label("Laporan Bulanan");
    Button opt1 = new Button("Graph/Grafik");
    Button opt2 = new Button("Show/Tampilkan");  
    Button opt3 = new Button("Excel");  
    Button back = new Button("Back/Kembali");  
    ComboBox month = new ComboBox();
    ComboBox year = new ComboBox();
    TableView tb;
    TableView ph= new TableView();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);
    
    public void setLayout(){
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        ph.setPlaceholder(new Label("Enter a year and month\n Masukkan Tahun dan Bulan"));
        month.setEditable(true);
        year.setEditable(true);
        String[] months = {"January/Januari", "February/Februari", "March/Maret", "April", "May/Mei","June/Juni", 
            "July/Juli", "August/Agustus", "September", "October/Oktober", "November", "December/Desember"};
        for (int i = 0; i < months.length; i++) {
            month.getItems().add(months[i]);
        }
        int[] content = pkh.getYears();
        for (int i = 0; i < 1000; i++) {
            if(content[i]!=0){
                year.getItems().add(content[i]);
            }
        }
        ColumnConstraints cc0 = new ColumnConstraints(200);
        gp.getColumnConstraints().addAll(cc0, cc0, cc0,cc0);
        header.getColumnConstraints().addAll(cc0, cc0, cc0,cc0);
        gp.setVgap(15);
        gp.setHgap(15);
        pkhP.setFitWidth(200);
        pkhP.setFitHeight(107);
        gp.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        main.setAlignment(Pos.CENTER);
        opt1.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt2.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt3.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        opt1.setFont(tnr16);
        opt2.setFont(tnr16);
        opt3.setFont(tnr16);
        back.setFont(tnr16);
        header.add(englbl, 0, 0, 1, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(month, 1, 1, 1, 1);
        header.add(year, 2, 1, 1, 1);
        header.add(pkhP, 3, 0, 1, 2);
        gp.add(ph, 0, 0, 4, 3);
        gp.addRow(3,back,opt1,opt2,opt3);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        main.addRow(0, header);
        main.addRow(1, gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }
    
    @Override
    public void start(Stage primaryStage) {
        setLayout();
        opt2.setOnAction(e -> createTable());
        opt1.setOnAction(e -> {
            GraphPage m8 = new GraphPage();
            m8.setX(month.getValue().toString());
            m8.setY(Integer.parseInt(year.getValue().toString()));
            m8.setLayout();
            m8.start(primaryStage);
        });
        opt3.setOnAction(e -> generateExcel());
        back.setOnAction(e -> {
            MainMenu m2 = new MainMenu();
            m2.setLayout();
            m2.start(primaryStage);
        });
        Scene scene = new Scene(main,950,700);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //Describes the programmatic entering of values to the JavaFX TableView
    public void createTable(){
        gp.getChildren().remove(ph);
        tb = new TableView();
        TableColumn<String, WorkerWeek> col1 = new TableColumn<>("District/Kecamatan");
        col1.setCellValueFactory(new PropertyValueFactory<>("Dist"));
        TableColumn<String, WorkerWeek> col2 = new TableColumn<>("Name/Nama");
        col2.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<String, WorkerWeek> col3 = new TableColumn<>("I");
        col3.setCellValueFactory(new PropertyValueFactory<>("I"));
        TableColumn<String, WorkerWeek> col4 = new TableColumn<>("II");
        col4.setCellValueFactory(new PropertyValueFactory<>("II"));
        TableColumn<String, WorkerWeek> col5 = new TableColumn<>("III");
        col5.setCellValueFactory(new PropertyValueFactory<>("III"));
        TableColumn<String, WorkerWeek> col6 = new TableColumn<>("IV");
        col6.setCellValueFactory(new PropertyValueFactory<>("IV"));
        TableColumn<String, WorkerWeek> col7 = new TableColumn<>("V");
        col7.setCellValueFactory(new PropertyValueFactory<>("V"));
        TableColumn<String, WorkerWeek> monCol = new TableColumn<>(month.getValue().toString());
        TableColumn<String, WorkerWeek> col8 = new TableColumn<>("Total");
        col8.setCellValueFactory(new PropertyValueFactory<>("Tot"));
        
        
        tb.getColumns().add(col1);
        tb.getColumns().add(col2);
        
        if(month.getValue().toString().equals("February/Februari") && Integer.parseInt(year.getValue().toString())%4!=0){
            monCol.getColumns().addAll(col3,col4,col5,col6);
        } else {
            monCol.getColumns().addAll(col3,col4,col5,col6,col7);
        }
        tb.getColumns().add(monCol);
        tb.getColumns().add(col8);
        WorkerWeek[] ww = pkh.getMnthData(month.getValue().toString(), Integer.parseInt(year.getValue().toString()));
        for (int i = 0; i < ww.length; i++) {
            if(ww[i]!=null){
                tb.getItems().add(ww[i]);
            }
        }
        gp.add(tb, 0, 0, 4, 3);
    }
    
    public void generateExcel(){
        CreateExcel ce = new CreateExcel();
        ce.createWW(pkh.getMnthData(month.getValue().toString(), Integer.parseInt(year.getValue().toString())), (month.getValue().toString()+year.getValue().toString()));
    }

}
