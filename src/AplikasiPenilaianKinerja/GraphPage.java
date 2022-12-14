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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
public class GraphPage extends Application {
    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Graph of Worker Scores");
    Label indolbl = new Label("Grafik Nilai Pendamping");
    Button opt1 = new Button("Graph/Grafik");
    Button back = new Button("Back/Kembali");  
    ComboBox year = new ComboBox();
    TableView tb = new TableView();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    BorderPane rootPane = new BorderPane();
    Scene scene;
    String x;
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);
    int y;
    boolean empty = true;

    public void setX(String x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
/*  Preconditions : The user selects a month and/or a year in which to display total score
*                   data and clicks on the option to display the data as a graph
*   Postconditions: The data requested is fetched from the database and displayed as a
*                   bar graph, ordered from greatest score by one person to smallest.
*/
    public void setLayout(){
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Name/Nama");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Score/ Nilai Total");

        BarChart bc = new BarChart(xAxis, yAxis);

        XYChart.Series scores = new XYChart.Series();
        scores.setName("Scores/Nilai-Nilai");
        
        WorkerCalc[] data = pkh.retWork(x,y);
        for (int i = 0; i < data.length; i++) {
            if(data[i]!=null){
                scores.getData().add(new XYChart.Data(data[i].getName(), data[i].totscor));
            }
        }
        bc.getData().add(scores);
        
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
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        opt1.setFont(tnr16);
        back.setFont(tnr16);
        header.add(englbl, 0, 0, 2, 1);
        header.add(indolbl, 0, 1, 2, 1);
        header.add(pkhP, 2, 0, 1, 2);
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        gp.add(bc, 0, 0, 3, 3);
        gp.addRow(3, back);
        main.addRow(0, header);
        main.addRow(1, gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }
    
    @Override
    public void start(Stage primaryStage) {
        scene = new Scene(main,800,650);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        back.setOnAction(e -> {
            YearEvalu m7b = new YearEvalu();
            MonthEval m6 = new MonthEval();
            if(x.equals("")){
                m7b.start(primaryStage);
            } else {
                m6.start(primaryStage);
            }
        });
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */

    
}
