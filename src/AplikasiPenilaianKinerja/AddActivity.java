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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
public class AddActivity extends Application {
    
    ScrollPane sp= new ScrollPane();
    GridPane main = new GridPane();
    GridPane checkboxes = new GridPane();
    GridPane header = new GridPane();
    GridPane gp = new GridPane();
    Label englbl = new Label("Add Activity");
    Label indolbl = new Label("Tambahkan informasi aktivitas");
    Button add = new Button("Add Data/Masukkan Data");
    Button back = new Button("Back/Kembali");  
    Label distP = new Label("District/Kecamatan");
    Label nameP = new Label("Name of Worker/Nama Pendamping");
    //Label sbdistP = new Label("Subdistrict/Kelurahan");
    //Label famP = new Label("Families Aided/Jumlah Keluarga yang Dibantu");
    Label yearP = new Label("Year/Tahun");
    Label monthP = new Label("Month/Bulan");
    Label weekP = new Label("Week#/Minggu ke-");
    CheckBox act1= new CheckBox("1)Sosialisasi PKH");
    CheckBox act2= new CheckBox("2)Pertemuan Awal & Validasi");
    CheckBox act3= new CheckBox("3)Pemutakhiran Data Peserta PKH");
    CheckBox act4= new CheckBox("4)Kunjungan ke Rumah Peserta PKH");
    CheckBox act5= new CheckBox("5)Verifikasi Pendidikan");
    CheckBox act6= new CheckBox("6)Verifikasi Kesehatan");
    CheckBox act7= new CheckBox("7)Pertemuan dengan Petugas Faskes");
    CheckBox act8= new CheckBox("8)Pertemuan dengan Petugas Fasdik");
    CheckBox act9= new CheckBox("9)Pertemuan Kelompok/FDS");
    CheckBox act10= new CheckBox("10)Pertemuan dengan UPPKH Kab./Kota");
    CheckBox act11= new CheckBox("11)Koordinasi dengan aparat Kecamatan/Desa");
    CheckBox act12= new CheckBox("12)Koordinasi dengan Petugas Bayar");
    CheckBox act13= new CheckBox("13)Penyaluran Bantuan");
    CheckBox act14= new CheckBox("14)Rekonsiliasi Penyaluran Bantuan");
    CheckBox act15= new CheckBox("15)Pelatihan dan pendidikan (Diklat)");
    CheckBox act16= new CheckBox("16)Bimbingan Teknis (Bimtek)");
    CheckBox act17= new CheckBox("17)Pelatihan Lainnya");
    CheckBox act18= new CheckBox("18)Penanganan Pengaduan Peserta");
    CheckBox act19= new CheckBox("19)Penanganan Pengaduan Non Peserta");
    CheckBox act20= new CheckBox("20)Penyerahan Dokumen ke UPPH Kab./Kota");
    CheckBox[] cbarr = {act1,act2,act3,act4,act5,act6,act7,act8,act9,act10,act11,act12,act13,act14,act15,act16,act17,act18,act19,act20};
    ComboBox month = new ComboBox();
    ComboBox dist = new ComboBox();
    TextField week = new TextField();
    TextField year = new TextField();
    ComboBox name = new ComboBox();
    //ComboBox sbdist = new ComboBox();
    //TextField fam = new TextField();
    Font tnr24 = new Font("Tahoma", 22);
    Font tnr16 = new Font("Tahoma", 16);
    PKHDatabaseRef pkh = new PKHDatabaseRef();
    Image pkhI = new Image(this.getClass().getResourceAsStream("/pkh.jpg"));
    ImageView pkhP = new ImageView(pkhI);
    
    public void setLayout(){
        year.setEditable(true);
        String[] months = {"January/Januari", "February/Februari", "March/Maret", "April", "May/Mei","June/Juni", 
            "July/Juli", "August/Agustus", "September", "October/Oktober", "November", "December/Desember"};
        for (int i = 0; i < months.length; i++) {
            month.getItems().add(months[i]);
        }
        
        ColumnConstraints cc0 = new ColumnConstraints(350);
        ColumnConstraints cc1 = new ColumnConstraints(250);
        ColumnConstraints cc2 = new ColumnConstraints(280);
        ColumnConstraints cc3 = new ColumnConstraints(320);
        gp.getColumnConstraints().addAll(cc0, cc1);
        header.getColumnConstraints().addAll(cc0, cc1);
        checkboxes.getColumnConstraints().addAll(cc2, cc3);
        englbl.setTextFill(Color.web("#ffffff"));
        indolbl.setTextFill(Color.web("#ffffff"));
        gp.setVgap(15);
        gp.setHgap(10);
        checkboxes.setVgap(15);
        gp.setAlignment(Pos.CENTER);
        checkboxes.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        main.setAlignment(Pos.CENTER);
        name.setEditable(true);
        dist.setEditable(true);
        //sbdist.setEditable(true);
        englbl.setFont(tnr24);
        indolbl.setFont(tnr16);
        nameP.setFont(tnr16);
        //sbdistP.setFont(tnr16);
        //famP.setFont(tnr16);
        distP.setFont(tnr16);
        add.setFont(tnr16);
        back.setFont(tnr16);
        yearP.setFont(tnr16);
        monthP.setFont(tnr16);
        weekP.setFont(tnr16);
        pkhP.setFitWidth(200);
        pkhP.setFitHeight(107);
        main.setPadding(new Insets(15, 15, 15, 15)); 
        gp.setPadding(new Insets(15, 15, 15, 15)); 
        checkboxes.setPadding(new Insets(15, 15, 15, 15)); 
        header.setPadding(new Insets(15, 15, 15, 15));
        add.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        back.setStyle("-fx-background-color: royalblue;"
                + " -fx-text-fill: white;");
        String[] content1 = pkh.getDistricts();
        for (int i = 0; i < 1000; i++) {
            if(content1[i]!=null){
                dist.getItems().add(content1[i]);
            }
        }
//        String[] sbd = pkh.getSubDistricts();
//        for (int i = 0; i < 1000; i++) {
//            if(sbd[i]!=null){
//                sbdist.getItems().add(sbd[i]);
//            }
//        }
        header.add(englbl, 0, 0, 1, 1);
        header.add(indolbl, 0, 1, 1, 1);
        header.add(pkhP, 1, 0, 2, 2);
        gp.addRow(0, distP,yearP);
        gp.addRow(1, dist,year);
        gp.addRow(2, nameP, monthP);
        gp.addRow(3, name, month);
        gp.addRow(4, new Label(), weekP);
        gp.addRow(5, new Label(), week);

        checkboxes.addRow(0, act1, act11);
        checkboxes.addRow(1, act2, act12);
        checkboxes.addRow(2, act3, act13);
        checkboxes.addRow(3, act4, act14);
        checkboxes.addRow(4, act5, act15);
        checkboxes.addRow(5, act6, act16);
        checkboxes.addRow(6, act7, act17);
        checkboxes.addRow(7, act8, act18);
        checkboxes.addRow(8, act9, act19);
        checkboxes.addRow(9, act10, act20);
        checkboxes.addRow(10,back,add);
        
        main.addRow(0,header);
        main.addRow(1,gp);
        main.addRow(2,checkboxes);
        //main.addRow(3,new Label("© Hanif Rizky Noegroho"));
        sp.setContent(main);
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        add.setOnAction(e -> {
            addToDB();
            GridPane pu = new GridPane();
            Popup popup = new Popup(); 
            Label text = new Label("Success/Berhasil");
            Button b = new Button("Close/Tutup");
            text.setFont(tnr16);
            b.setFont(tnr16);
            pu.setEffect(new DropShadow());            
            pu.setPadding(new Insets(15, 15, 15, 15));
            pu.addRow(0, text);
            pu.addRow(1, b);
            pu.setVgap(5);
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
        Scene scene = new Scene(sp,700,500);
        sp.setStyle("-fx-background-color: white;");
        header.setStyle("-fx-background-color: royalblue;");
        main.setStyle("-fx-background-color: white;");
        gp.setStyle("-fx-background-color: whitesmoke;");
        checkboxes.setStyle("-fx-background-color: whitesmoke;");
        
        primaryStage.setTitle("PKH Worker Evaluation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
/*
*   The application calculates the score of a worker
*   programatically by making an array of JavaFX 
*   checkboxes and looping to count which checkboxes
*   are or are not ticked.
*/
    public void addToDB(){
        int count = 0;
        for (int i = 0; i < cbarr.length; i++) {
            if(cbarr[i].isSelected()==true){
                count++;
                System.out.println(count);
            }
        }
        
        
        pkh.addActivity(name.getValue().toString(), 
            Integer.parseInt(week.getText()), month.getValue().toString(), Integer.parseInt(year.getText()),
            count);
        for (int i = 0; i < cbarr.length; i++) {
            if(cbarr[i].isSelected()==true){
                cbarr[i].setSelected(false);
            }
        }
    }
    
    
}


// 'name',
