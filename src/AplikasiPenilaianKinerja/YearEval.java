/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author 53792
 */
public class YearEval extends Application {

    GridPane gp = new GridPane();
    Label englbl = new Label("Yearly Evaluation");
    Label indolbl = new Label("Laporan Tahunan");
    Button opt1 = new Button("Graph/Grafik");
    Button opt2 = new Button("Show/Tampilkan");
    Button back = new Button("Back/Kembali");
    ComboBox year = new ComboBox();
    TableView tb;
    TableView ph = new TableView();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();

    public void setLayout() {
        int[] content = pkh.getYears();
        year.setEditable(true);
        for (int i = 0; i < 1000; i++) {
            if (content[i] != 0) {
                year.getItems().add(content[i]);
            }
        }
        ColumnConstraints cc0 = new ColumnConstraints(200);
        ColumnConstraints cc1 = new ColumnConstraints(200);
        ColumnConstraints cc2 = new ColumnConstraints(200);
        gp.getColumnConstraints().addAll(cc0, cc1, cc2);
        gp.setVgap(15);
        gp.setHgap(15);
        gp.setAlignment(Pos.CENTER);
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        opt1.setFont(tnr16);
        back.setFont(tnr16);
        gp.add(englbl, 0, 0, 2, 1);
        gp.add(indolbl, 0, 1, 2, 1);
        gp.add(year, 2, 0, 1, 1);
        gp.add(ph, 0, 2, 3, 3);
        gp.addRow(5, back, opt1, opt2);
    }

    @Override
    public void start(Stage primaryStage) {
        opt2.setOnAction(e -> createTable());
        ph.setPlaceholder(new Label("Enter a year\nMasukkan Tahun"));
        opt1.setOnAction(e -> {
            GraphPage m8 = new GraphPage();
            m8.setX("");
            m8.setY(Integer.parseInt(year.getValue().toString()));
            m8.setLayout();
            m8.start(primaryStage);
        });
        back.setOnAction(e -> {
            MainMenu m2 = new MainMenu();
            m2.setLayout();
            m2.start(primaryStage);
        });
        setLayout();
        Scene scene = new Scene(gp, 750, 550);

        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createTable() {

        gp.getChildren().remove(ph);
        tb = new TableView();
        TableColumn<String, YearWork> col1 = new TableColumn<>("District/Kecamatan");
            col1.setCellValueFactory(new PropertyValueFactory<>("Dist"));
            TableColumn<String, YearWork> col2 = new TableColumn<>("Name/Nama");
            col2.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tb.getColumns().add(col1);
            tb.getColumns().add(col2);
        for (int i = 1; i < 13; i++) {
            
            TableColumn<String, YearWork> col3 = new TableColumn<>("I");
            col3.setCellValueFactory(new PropertyValueFactory<>("I"));
            TableColumn<String, YearWork> col4 = new TableColumn<>("II");
            col4.setCellValueFactory(new PropertyValueFactory<>("II"));
            TableColumn<String, YearWork> col5 = new TableColumn<>("III");
            col5.setCellValueFactory(new PropertyValueFactory<>("III"));
            TableColumn<String, YearWork> col6 = new TableColumn<>("IV");
            col6.setCellValueFactory(new PropertyValueFactory<>("IV"));
            TableColumn<String, YearWork> col7 = new TableColumn<>("V");
            col7.setCellValueFactory(new PropertyValueFactory<>("V"));

            String month;
            TableColumn<String, YearWork> monCol;
            if (i == 1) {
                month = "January/Januari";
                monCol = new TableColumn<>(month);
            } else if (i == 2) {
                month = "February/Februari";
                monCol = new TableColumn<>(month);
            } else if (i == 3) {
                month = "March/Maret";
                monCol = new TableColumn<>(month);
            } else if (i == 4) {
                month = "April";
                monCol = new TableColumn<>(month);
            } else if (i == 5) {
                month = "May/Mei";
                monCol = new TableColumn<>(month);
            } else if (i == 6) {
                month = "June/Juni";
                monCol = new TableColumn<>(month);
            } else if (i == 7) {
                month = "July/Juli";
                monCol = new TableColumn<>(month);
            } else if (i == 8) {
                month = "August/Agustus";
                monCol = new TableColumn<>(month);
            } else if (i == 9) {
                month = "September";
                monCol = new TableColumn<>(month);
            } else if (i == 10) {
                month = "October/Oktober";
                monCol = new TableColumn<>(month);
            } else if (i == 11) {
                month = "November";
                monCol = new TableColumn<>(month);
            } else if (i == 12) {
                month = "December/Desember";
                monCol = new TableColumn<>(month);
            } else {
                month = "Error";
                monCol = new TableColumn<>("error");
            }

            TableColumn<String, YearWork> col8 = new TableColumn<>("Total");
            col8.setCellValueFactory(new PropertyValueFactory<>("Tot"));

            

            if (month.equals("February/Februari") && Integer.parseInt(year.getValue().toString()) % 4 != 0) {
                monCol.getColumns().addAll(col3, col4, col5, col6);
            } else {
                monCol.getColumns().addAll(col3, col4, col5, col6, col7);
            }

            
            tb.getColumns().add(monCol);
            tb.getColumns().add(col8);
            YearWork yw = new YearWork(2020);
            for (int k = 0; k < yw.wwArr[0].length; k++) {
                if (yw.wwArr[i - 1][k] != null) {
                    System.out.println(yw.wwArr[i - 1][k]);
                    System.out.println("k= " +k);
                    tb.getItems().add(yw.wwArr[i - 1][k]);
                }
            }
               
        }

        gp.add(tb, 0, 2, 3, 3);
    }



}
