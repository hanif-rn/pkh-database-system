/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
public class YearEvalu extends Application {

    GridPane main = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Yearly Evaluation");
    Label indolbl = new Label("Laporan Tahunan");
    Button opt1 = new Button("Graph/Grafik");
    Button opt2 = new Button("Show/Tampilkan");
    Button back = new Button("Back/Kembali");
    Button excel = new Button("Excel");
    ComboBox year = new ComboBox();
    TableView tb;
    TableView ph = new TableView();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);

    public void setLayout() {
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        int[] content = pkh.getYears();
        year.setEditable(true);
        for (int i = 0; i < 1000; i++) {
            if (content[i] != 0) {
                year.getItems().add(content[i]);
            }
        }
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt1.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        excel.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        opt2.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        ColumnConstraints cc0 = new ColumnConstraints(200);
        ColumnConstraints cc1 = new ColumnConstraints(200);
        ColumnConstraints cc2 = new ColumnConstraints(200);
        header.getColumnConstraints().addAll(cc0, cc1, cc2);
        gp.getColumnConstraints().addAll(cc0, cc1, cc2);
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
        opt2.setFont(tnr16);
        back.setFont(tnr16);
        excel.setFont(tnr16);
        header.add(englbl, 0, 0, 2, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(pkhP, 3, 0, 1, 2);
        header.add(year, 1, 1, 1, 1);
        gp.add(ph, 0, 0, 4, 3);
        gp.addRow(3, back, opt1, opt2,excel);
        main.addRow(0, header);
        main.addRow(1, gp);
        //main.addRow(2,new Label("© Hanif Rizky Noegroho"));
    }        
    


    @Override
    public void start(Stage primaryStage) {
        excel.setOnAction(e -> generateExcel());
        opt2.setOnAction(e -> createTable());
        ph.setPlaceholder(new Label("Enter a year\nMasukkan Tahun"));
        opt1.setOnAction(e -> {
            if(!year.getValue().equals("") || !year.getValue().equals(null)){
                GraphPage m8 = new GraphPage();
                m8.setX("");
                m8.setY(Integer.parseInt(year.getValue().toString()));
                m8.setLayout();
                m8.start(primaryStage);
            } else if(!year.getValue().equals("") || year.getValue().equals(null)){
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
            MainMenu m2 = new MainMenu();
            m2.setLayout();
            m2.start(primaryStage);
        });
        setLayout();
        Scene scene = new Scene(main, 950, 650);
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        
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
        
            TableColumn<String, YearWork> col3 = new TableColumn<>("I");
        col3.setCellValueFactory(new PropertyValueFactory<>("I1"));
        TableColumn<String, YearWorkB> col4 = new TableColumn<>("II");
        col4.setCellValueFactory(new PropertyValueFactory<>("II1"));
        TableColumn<String, YearWorkB> col5 = new TableColumn<>("III");
        col5.setCellValueFactory(new PropertyValueFactory<>("III1"));
        TableColumn<String, YearWorkB> col6 = new TableColumn<>("IV");
        col6.setCellValueFactory(new PropertyValueFactory<>("IV1"));
        TableColumn<String, YearWorkB> col7 = new TableColumn<>("V");
        col7.setCellValueFactory(new PropertyValueFactory<>("V1"));
        TableColumn<String, YearWorkB> col8 = new TableColumn<>("I");
        col8.setCellValueFactory(new PropertyValueFactory<>("I2"));
        TableColumn<String, YearWorkB> col9 = new TableColumn<>("II");
        col9.setCellValueFactory(new PropertyValueFactory<>("II2"));
        TableColumn<String, YearWorkB> col10 = new TableColumn<>("III");
        col10.setCellValueFactory(new PropertyValueFactory<>("III2"));
        TableColumn<String, YearWorkB> col11 = new TableColumn<>("IV");
        col11.setCellValueFactory(new PropertyValueFactory<>("IV2"));
        TableColumn<String, YearWorkB> col12 = new TableColumn<>("V");
        col12.setCellValueFactory(new PropertyValueFactory<>("V2"));
        TableColumn<String, YearWorkB> col13 = new TableColumn<>("I");
        col13.setCellValueFactory(new PropertyValueFactory<>("I3"));
        TableColumn<String, YearWorkB> col14 = new TableColumn<>("II");
        col14.setCellValueFactory(new PropertyValueFactory<>("II3"));
        TableColumn<String, YearWorkB> col15 = new TableColumn<>("III");
        col15.setCellValueFactory(new PropertyValueFactory<>("III3"));
        TableColumn<String, YearWorkB> col16 = new TableColumn<>("IV");
        col16.setCellValueFactory(new PropertyValueFactory<>("IV3"));
        TableColumn<String, YearWorkB> col17 = new TableColumn<>("V");
        col17.setCellValueFactory(new PropertyValueFactory<>("V3"));
        TableColumn<String, YearWorkB> col18 = new TableColumn<>("I");
        col18.setCellValueFactory(new PropertyValueFactory<>("I4"));
        TableColumn<String, YearWorkB> col19 = new TableColumn<>("II");
        col19.setCellValueFactory(new PropertyValueFactory<>("II4"));
        TableColumn<String, YearWorkB> col20 = new TableColumn<>("III");
        col20.setCellValueFactory(new PropertyValueFactory<>("III4"));
        TableColumn<String, YearWorkB> col21 = new TableColumn<>("IV");
        col21.setCellValueFactory(new PropertyValueFactory<>("IV4"));
        TableColumn<String, YearWorkB> col22 = new TableColumn<>("V");
        col22.setCellValueFactory(new PropertyValueFactory<>("V4"));
        TableColumn<String, YearWorkB> col23 = new TableColumn<>("I");
        col23.setCellValueFactory(new PropertyValueFactory<>("I5"));
        TableColumn<String, YearWorkB> col24 = new TableColumn<>("II");
        col24.setCellValueFactory(new PropertyValueFactory<>("II5"));
        TableColumn<String, YearWorkB> col25 = new TableColumn<>("III");
        col25.setCellValueFactory(new PropertyValueFactory<>("III5"));
        TableColumn<String, YearWorkB> col26 = new TableColumn<>("IV");
        col26.setCellValueFactory(new PropertyValueFactory<>("IV5"));
        TableColumn<String, YearWorkB> col27 = new TableColumn<>("V");
        col27.setCellValueFactory(new PropertyValueFactory<>("V5"));
        TableColumn<String, YearWorkB> col28 = new TableColumn<>("I");
        col28.setCellValueFactory(new PropertyValueFactory<>("I6"));
        TableColumn<String, YearWorkB> col29 = new TableColumn<>("II");
        col29.setCellValueFactory(new PropertyValueFactory<>("II6"));
        TableColumn<String, YearWorkB> col30 = new TableColumn<>("III");
        col30.setCellValueFactory(new PropertyValueFactory<>("III6"));
        TableColumn<String, YearWorkB> col31 = new TableColumn<>("IV");
        col31.setCellValueFactory(new PropertyValueFactory<>("IV6"));
        TableColumn<String, YearWorkB> col32 = new TableColumn<>("V");
        col32.setCellValueFactory(new PropertyValueFactory<>("V6"));
        TableColumn<String, YearWorkB> col33 = new TableColumn<>("I");
        col33.setCellValueFactory(new PropertyValueFactory<>("I7"));
        TableColumn<String, YearWorkB> col34 = new TableColumn<>("II");
        col34.setCellValueFactory(new PropertyValueFactory<>("II7"));
        TableColumn<String, YearWorkB> col35 = new TableColumn<>("III");
        col35.setCellValueFactory(new PropertyValueFactory<>("III7"));
        TableColumn<String, YearWorkB> col36 = new TableColumn<>("IV");
        col36.setCellValueFactory(new PropertyValueFactory<>("IV7"));
        TableColumn<String, YearWorkB> col37 = new TableColumn<>("V");
        col37.setCellValueFactory(new PropertyValueFactory<>("V7"));
        TableColumn<String, YearWorkB> col38 = new TableColumn<>("I");
        col38.setCellValueFactory(new PropertyValueFactory<>("I8"));
        TableColumn<String, YearWorkB> col39 = new TableColumn<>("II");
        col39.setCellValueFactory(new PropertyValueFactory<>("II8"));
        TableColumn<String, YearWorkB> col40 = new TableColumn<>("III");
        col40.setCellValueFactory(new PropertyValueFactory<>("III8"));
        TableColumn<String, YearWorkB> col41 = new TableColumn<>("IV");
        col41.setCellValueFactory(new PropertyValueFactory<>("IV8"));
        TableColumn<String, YearWorkB> col42 = new TableColumn<>("V");
        col42.setCellValueFactory(new PropertyValueFactory<>("V8"));
        TableColumn<String, YearWorkB> col43 = new TableColumn<>("I");
        col43.setCellValueFactory(new PropertyValueFactory<>("I9"));
        TableColumn<String, YearWorkB> col44 = new TableColumn<>("II");
        col44.setCellValueFactory(new PropertyValueFactory<>("II9"));
        TableColumn<String, YearWorkB> col45 = new TableColumn<>("III");
        col45.setCellValueFactory(new PropertyValueFactory<>("III9"));
        TableColumn<String, YearWorkB> col46 = new TableColumn<>("IV");
        col46.setCellValueFactory(new PropertyValueFactory<>("IV9"));
        TableColumn<String, YearWorkB> col47 = new TableColumn<>("V");
        col47.setCellValueFactory(new PropertyValueFactory<>("V9"));
        TableColumn<String, YearWorkB> col48 = new TableColumn<>("I");
        col48.setCellValueFactory(new PropertyValueFactory<>("I10"));
        TableColumn<String, YearWorkB> col49 = new TableColumn<>("II");
        col49.setCellValueFactory(new PropertyValueFactory<>("II10"));
        TableColumn<String, YearWorkB> col50 = new TableColumn<>("III");
        col50.setCellValueFactory(new PropertyValueFactory<>("III10"));
        TableColumn<String, YearWorkB> col51 = new TableColumn<>("IV");
        col51.setCellValueFactory(new PropertyValueFactory<>("IV10"));
        TableColumn<String, YearWorkB> col52 = new TableColumn<>("V");
        col52.setCellValueFactory(new PropertyValueFactory<>("V10"));
        TableColumn<String, YearWorkB> col53 = new TableColumn<>("I");
        col53.setCellValueFactory(new PropertyValueFactory<>("I11"));
        TableColumn<String, YearWorkB> col54 = new TableColumn<>("II");
        col54.setCellValueFactory(new PropertyValueFactory<>("II11"));
        TableColumn<String, YearWorkB> col55 = new TableColumn<>("III");
        col55.setCellValueFactory(new PropertyValueFactory<>("III11"));
        TableColumn<String, YearWorkB> col56 = new TableColumn<>("IV");
        col56.setCellValueFactory(new PropertyValueFactory<>("IV11"));
        TableColumn<String, YearWorkB> col57 = new TableColumn<>("V");
        col57.setCellValueFactory(new PropertyValueFactory<>("V11"));
        TableColumn<String, YearWorkB> col58 = new TableColumn<>("I");
        col58.setCellValueFactory(new PropertyValueFactory<>("I12"));
        TableColumn<String, YearWorkB> col59 = new TableColumn<>("II");
        col59.setCellValueFactory(new PropertyValueFactory<>("II12"));
        TableColumn<String, YearWorkB> col60 = new TableColumn<>("III");
        col60.setCellValueFactory(new PropertyValueFactory<>("III12"));
        TableColumn<String, YearWorkB> col61 = new TableColumn<>("IV");
        col61.setCellValueFactory(new PropertyValueFactory<>("IV12"));
        TableColumn<String, YearWorkB> col62 = new TableColumn<>("V");
        col62.setCellValueFactory(new PropertyValueFactory<>("V12"));

        TableColumn<String, YearWorkB> Total1 = new TableColumn<>("Total");
        Total1.setCellValueFactory(new PropertyValueFactory<>("Tot1"));
        TableColumn<String, YearWorkB> Total2 = new TableColumn<>("Total");
        Total2.setCellValueFactory(new PropertyValueFactory<>("Tot2"));
        TableColumn<String, YearWorkB> Total3 = new TableColumn<>("Total");
        Total3.setCellValueFactory(new PropertyValueFactory<>("Tot3"));
        TableColumn<String, YearWorkB> Total4 = new TableColumn<>("Total");
        Total4.setCellValueFactory(new PropertyValueFactory<>("Tot4"));
        TableColumn<String, YearWorkB> Total5 = new TableColumn<>("Total");
        Total5.setCellValueFactory(new PropertyValueFactory<>("Tot5"));
        TableColumn<String, YearWorkB> Total6 = new TableColumn<>("Total");
        Total6.setCellValueFactory(new PropertyValueFactory<>("Tot6"));
        TableColumn<String, YearWorkB> Total7 = new TableColumn<>("Total");
        Total7.setCellValueFactory(new PropertyValueFactory<>("Tot7"));
        TableColumn<String, YearWorkB> Total8 = new TableColumn<>("Total");
        Total8.setCellValueFactory(new PropertyValueFactory<>("Tot8"));
        TableColumn<String, YearWorkB> Total9 = new TableColumn<>("Total");
        Total9.setCellValueFactory(new PropertyValueFactory<>("Tot9"));
        TableColumn<String, YearWorkB> Total10 = new TableColumn<>("Total");
        Total10.setCellValueFactory(new PropertyValueFactory<>("Tot10"));
        TableColumn<String, YearWorkB> Total11 = new TableColumn<>("Total");
        Total11.setCellValueFactory(new PropertyValueFactory<>("Tot11"));
        TableColumn<String, YearWorkB> Total12 = new TableColumn<>("Total");
        Total12.setCellValueFactory(new PropertyValueFactory<>("Tot12"));
        
        TableColumn<String, YearWorkB> monCol1= new TableColumn<>("January/Januari");
        TableColumn<String, YearWorkB> monCol2= new TableColumn<>("February/Februari");
        TableColumn<String, YearWorkB> monCol3= new TableColumn<>("March/Maret");
        TableColumn<String, YearWorkB> monCol4= new TableColumn<>("April");
        TableColumn<String, YearWorkB> monCol5= new TableColumn<>("May/Mei");
        TableColumn<String, YearWorkB> monCol6= new TableColumn<>("Juni/June");
        TableColumn<String, YearWorkB> monCol7= new TableColumn<>("July/Juli");
        TableColumn<String, YearWorkB> monCol8= new TableColumn<>("August/Agustus");
        TableColumn<String, YearWorkB> monCol9= new TableColumn<>("September");
        TableColumn<String, YearWorkB> monCol10= new TableColumn<>("October/Oktober");
        TableColumn<String, YearWorkB> monCol11= new TableColumn<>("November");
        TableColumn<String, YearWorkB> monCol12= new TableColumn<>("December/Desember");
        TableColumn<String, YearWorkB> Total = new TableColumn<>("Total");
        Total.setCellValueFactory(new PropertyValueFactory<>("Tot"));

        monCol1.getColumns().addAll(col3,col4,col5,col6,col7,Total1);
        if(Integer.parseInt(year.getValue().toString())%4!=0){
            monCol2.getColumns().addAll(col8,col9,col10,col11,Total2);
        } else {
            monCol2.getColumns().addAll(col8,col9,col10,col11,col12,Total2);
        }
        monCol3.getColumns().addAll(col13,col14,col15,col16,col17,Total3);
        monCol4.getColumns().addAll(col18,col19,col20,col21,col22,Total4);
        monCol5.getColumns().addAll(col23,col24,col25,col26,col27,Total5);
        monCol6.getColumns().addAll(col28,col29,col30,col31,col32,Total6);
        monCol7.getColumns().addAll(col33,col34,col35,col36,col37,Total7);
        monCol8.getColumns().addAll(col38,col39,col40,col41,col42,Total8);
        monCol9.getColumns().addAll(col43,col44,col45,col46,col47,Total9);
        monCol10.getColumns().addAll(col48,col49,col50,col51,col52,Total10);
        monCol11.getColumns().addAll(col53,col54,col55,col56,col57,Total11);
        monCol12.getColumns().addAll(col58,col59,col60,col61,col62,Total12);
        tb.getColumns().add(monCol1);
        tb.getColumns().add(monCol2);
        tb.getColumns().add(monCol3);
        tb.getColumns().add(monCol4);
        tb.getColumns().add(monCol5);
        tb.getColumns().add(monCol6);
        tb.getColumns().add(monCol7);
        tb.getColumns().add(monCol8);
        tb.getColumns().add(monCol9);
        tb.getColumns().add(monCol10);
        tb.getColumns().add(monCol11);
        tb.getColumns().add(monCol12);
        Total.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        tb.getColumns().add(Total);
        WorkerWeek[][] wwArr = new WorkerWeek[12][1000];
        wwArr[0]=pkh.getMnthData("January/Januari", Integer.parseInt(year.getValue().toString()));
        wwArr[1]=pkh.getMnthData("February/Februari", Integer.parseInt(year.getValue().toString()));
        wwArr[2]=pkh.getMnthData("March/Maret", Integer.parseInt(year.getValue().toString()));
        wwArr[3]=pkh.getMnthData("April", Integer.parseInt(year.getValue().toString()));
        wwArr[4]=pkh.getMnthData("May/Mei", Integer.parseInt(year.getValue().toString()));
        wwArr[5]=pkh.getMnthData("June/Juni", Integer.parseInt(year.getValue().toString()));
        wwArr[6]=pkh.getMnthData("July/Juli", Integer.parseInt(year.getValue().toString()));
        wwArr[7]=pkh.getMnthData("August/Agustus", Integer.parseInt(year.getValue().toString()));
        wwArr[8]=pkh.getMnthData("September", Integer.parseInt(year.getValue().toString()));
        wwArr[9]=pkh.getMnthData("October/Oktober", Integer.parseInt(year.getValue().toString()));
        wwArr[10]=pkh.getMnthData("November/November", Integer.parseInt(year.getValue().toString()));
        wwArr[11]=pkh.getMnthData("December/Desember", Integer.parseInt(year.getValue().toString()));
        YearWorkB yw = new YearWorkB(wwArr);
        YearWorkB[] ywArr = new YearWorkB[1000];
        for (int i = 0; i < ywArr.length; i++) {
            if(yw.wwArr[0][yw.count]!=null){
                ywArr[i]=new YearWorkB(wwArr);
                ywArr[i].count=yw.count;
                yw.count++;
            }
        }
              
        for (int i = 0; i < ywArr.length; i++) {
            if(ywArr[i]!=null){
               ywArr[i].setCount(i);
               tb.getItems().addAll(ywArr[i]);
            }
        }                
       

        gp.add(tb, 0, 0, 4, 3);
    }

    public void generateExcel(){
        CreateExcel ce = new CreateExcel();
        ce.createYR(Integer.parseInt(year.getValue().toString()));
    }
    


}
