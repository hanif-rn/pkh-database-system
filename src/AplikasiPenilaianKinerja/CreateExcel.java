/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 53792
 */
public class CreateExcel {
    XSSFWorkbook wb = new XSSFWorkbook();
    XSSFSheet sheet = wb.createSheet("Data Sheet");
    static Object[][] Data = new Object[1000][1000];

    public void createWW(WorkerWeek[] wwArr, String month) {

        Data[0][0]=month;
        Data[1][0]="Name/Nama";
        Data[1][1]="District/Kecamatan";
        Data[1][2]="I";
        Data[1][3]="II";
        Data[1][4]="III";
        Data[1][5]="IV";
        Data[1][6]="V";
        Data[1][7]="Total";
        
        for (int i = 2; i < wwArr.length+2; i++) {
            if (wwArr[i-2] != null) {
                Data[i][0] = wwArr[i-2].getName();
                Data[i][1] = wwArr[i-2].getDist();
                Data[i][2] = wwArr[i-2].getI();
                Data[i][3] = wwArr[i-2].getII();
                Data[i][4] = wwArr[i-2].getIII();
                Data[i][5] = wwArr[i-2].getIV();
                Data[i][6] = wwArr[i-2].getV();
                Data[i][7] = wwArr[i-2].getTot();
            }
        }
        
        try {
            generateSheet((/*month+*/" Laporan Bulanan"));
        } catch (IOException ex) {
            System.out.println("error");
        }
    }
    
    public void createYR(int year) {
        PKHDatabaseRef pkh = new PKHDatabaseRef();
        WorkerWeek[] wwJan = pkh.getMnthData("January/Januari", year);
        WorkerWeek[] wwFeb = pkh.getMnthData("February/Februari", year);
        WorkerWeek[] wwMar = pkh.getMnthData("March/Maret", year);
        WorkerWeek[] wwApr = pkh.getMnthData("April", year);
        WorkerWeek[] wwMay = pkh.getMnthData("May/Mei", year);
        WorkerWeek[] wwJun = pkh.getMnthData("June/Juni", year);
        WorkerWeek[] wwJul = pkh.getMnthData("July/Juli", year);
        WorkerWeek[] wwAug = pkh.getMnthData("August/Agustus", year);
        WorkerWeek[] wwSep = pkh.getMnthData("September", year);
        WorkerWeek[] wwOct = pkh.getMnthData("October/Oktober", year);
        WorkerWeek[] wwNov = pkh.getMnthData("November", year);
        WorkerWeek[] wwDec = pkh.getMnthData("December/Desember", year);
        WorkerWeek[][] wwArr = {wwJan,wwFeb,wwMar,wwApr,wwMay,wwJun,wwJul,wwAug,wwSep,wwOct,wwNov,wwDec};
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 7));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 13));
        Data[1][0]="Name/Nama";
        Data[1][1]="District/Kecamatan";
        int index = 2;
        for (int i = 0; i < 12; i++) {
            Data[1][index]="I";
            index++;
            Data[1][index]="II";
            index++;
            Data[1][index]="III";
            index++;
            Data[1][index]="IV";
            index++;
            Data[1][index]="V";
            index++;
            Data[1][index]="Total";
            index++;
            System.out.println(index);
        }
        Data[1][74]="Total";
        
        
        for (int i = 2; i < wwJan.length+2; i++) {
            if (wwJan[i-2] != null) {
                Data[i][0] = wwJan[i-2].getName();
                Data[i][1] = wwJan[i-2].getDist();
            }
        }
        index=2;
        int totalYr=0;
        for (int i = 2; i < wwJan.length+2; i++) {
            for (int j = 0; j < 12; j++) {
                if (wwArr[j][i-2] != null) {
                    Data[i][index] = wwArr[j][i-2].getI();
                    index++;
                    Data[i][index]=wwArr[j][i-2].getII();
                    index++;
                    Data[i][index]=wwArr[j][i-2].getIII();
                    index++;
                    Data[i][index]=wwArr[j][i-2].getIV();
                    index++;
                    Data[i][index]=wwArr[j][i-2].getV();
                    index++;
                    Data[i][index]=wwArr[j][i-2].getTot();
                    totalYr+=wwArr[j][i-2].getTot();
                    index++;
                    Data[i][74]= totalYr;
                }
            }
            index = 2;
            totalYr=0;
        }
        
        try {
            generateSheet((year+" Laporan Tahunan"));
        } catch (IOException ex) {
            System.out.println("error");
        }
    }
    
    
    public void createFS(FamiliesServed[] fsArr) {

        Data[0][0]="District/Kecamatan";
        Data[0][1]="Name/Nama";
        Data[0][2]="SubDistrict/Kelurahan";
        Data[0][3]="Total (Subdistrict/Kelurahan)";
        Data[0][4]="Total (Person/Pendamping)";
        Data[0][5]="Total (District/Kecamatan)";
        
        for (int i = 1; i < fsArr.length+1; i++) {
            if (fsArr[i-1] != null) {
                Data[i][0] = fsArr[i-1].getDistrict();
                Data[i][1] = fsArr[i-1].getName();
                Data[i][2] = fsArr[i-1].getSubdistrict();
                Data[i][3] = fsArr[i-1].getSbDFamilies();
                Data[i][4] = fsArr[i-1].getTotalFamilies();
                Data[i][5] = fsArr[i-1].getDistFamilies();
            }
        }
        
        try {
            generateSheet(("KPM per SDM"));
        } catch (IOException ex) {
            System.out.println("error");
        }
    }

    private void generateSheet(String name) throws IOException {
        int rowCount = 0;

        for (Object[] x : Data) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : x) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        for (int i = 0; i < 75; i++) {
            sheet.autoSizeColumn(i);
        }
       // Date date = Calendar.getInstance().getTime();  
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");  
        //String strDate = dateFormat.format(date);  
        String fileName = name+/*strDate+*/".xlsx";
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            wb.write(outputStream);
        } catch (FileNotFoundException ex) {
            String error = ex.toString();
            System.out.println("error");
        }

    }

}

