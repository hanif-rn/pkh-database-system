/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.sql.Connection;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 53792
 */
public class PKHDatabaseRef {

    private static final String cs = "jdbc:mysql://localhost:8889/pkhdatabase?useSSL=false";

    private static String un;
    private static String pw;
    private static String currentuser;

    
/*
*   Preconditions : the users input un and pw through GUI text fields; un and pw must 
*                   not be empty Strings
    
*   Postconditions: if an account it the database with username un and password pw is
*                   found, then the user will be logged in and connected to the 
*                   database. The GUI display will change from the login page to the
*                   main menu page.
*/
    public boolean setCredentials(String un, String pw) {
        
        this.un = un;
        this.pw = pw;
        currentuser = un;
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (SQLException ex) {
            Logger.getLogger(PKHDatabaseRef.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection(cs, un, pw);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        
        
        try {
            conn.createStatement().execute("Create TABLE Workers(Name varchar(100), District varchar(50))");
        } catch (SQLException e) {
            System.err.println(e);
        }
        try {
            conn.createStatement().execute("Create TABLE WeeklyReport(Name varchar(100), Week INT, Month INT, YearNo INT, "
                    + "Score INT)");
        } catch (SQLException e) {
            System.err.println(e);
        }
        try {
            conn.createStatement().execute("Create TABLE KPMperSDM(Name varchar(100), District varchar(50), SubDistrict varchar(50), Families INT)");
        } catch (SQLException e) {
            System.err.println(e);
        }
        try {
            conn.createStatement().execute("Create TABLE Log(Action varchar(100), Name varchar(50), Date date, Time time)");
        } catch (SQLException e) {
            System.err.println(e);
        }
        return true;
    }

    public void addWorkerData(int id, String name, String district) {
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String insert = "INSERT INTO Workers\n"
                    + "VALUES ('" + name + "','" + district + "')";
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
/*      Preconditions : The user (while logged in to an account) creates a change
*                       into the contents of a database
*       Postconditions: The program generates a date-time data, records what 
*                       change was made, and by which user (username is recorded
*                       upon login) made the change. These data points are then
*                       saved in the database.
*/
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(calendar.getTime()).substring(0, 10);
            String time = format.format(calendar.getTime()).substring(12);
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String insert = "INSERT INTO Log\n"
                    + "VALUES ('" + "Inserted worker: "+name + "','" + currentuser 
                    + "','" + date + "','" + time + "')";
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
    }
    
    /*  Preconditions : the user chooses the option to open the edit log after editing
     *                  content in the database
     *  Postconditions: the data is fetched from the database and each edit (containing
     *                  information on change, by who and when they were made), are 
     *                  converted to an array of objects which are then returned to the
     *                  GUI class in order to display them in JavaFX TableViews
     */
    public LogContent[] displayLog(){
        LogContent[] lc = new LogContent[10000];
        int index = 0;
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String get = "SELECT DISTINCT Action, Name, Date, Time\n"
                    + "                 FROM Log \n ORDER BY Date DESC, Time DESC";
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()) {
                lc[index] = new LogContent(rs.getString("Action"), rs.getString("Name"),
                        rs.getString("Date") + " " + rs.getString("Time"));
                index++;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lc;
    }
    
    public void addFamilies(String name, String district, String subdistrict, int families) {
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();

            String insert = "INSERT INTO KPMperSDM\n"
                    + "VALUES ('" + name + "','" + district +"','" + subdistrict + "'," + families +")";
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            System.err.println(e);
        }
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(calendar.getTime()).substring(0, 10);
            String time = format.format(calendar.getTime()).substring(12);
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String insert = "INSERT INTO Log\n"
                    + "VALUES ('" + "Inserted family aid: "+name + "','" + currentuser + "','" + date + "','" + time + "')";
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public WorkerWeek[] getMnthData(String month, int year) {
        int mon = convert(month);
        int index = 0;
        WorkerWeek[] ww = new WorkerWeek[1000];
        String[] d = new String[1000];
        String[] n = new String[1000];
        String[] k = new String[1000];
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            //System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String get4 = "SELECT  Name, District \n"
                    + "FROM Workers \n"
                    + "\nORDER BY District";
            ResultSet rs4 = stmt.executeQuery(get4);
            int[] empty = {0, 0, 0, 0, 0};
            while (rs4.next()) {
                ww[index] = new WorkerWeek(rs4.getString("District"), rs4.getString("Name"), empty);
                index++;
            }
            String get1 = "SELECT DISTINCT WeeklyReport.Name, Workers.District \n"
                    + "FROM WeeklyReport, Workers \n"
                    + "WHERE WeeklyReport.Month = " + mon + " AND WeeklyReport.YearNo = " + year + " AND Workers.Name = WeeklyReport.Name"
                    + "\nORDER BY WeeklyReport.Name";
            ResultSet rs1 = stmt.executeQuery(get1);
            int count = 0;
            while (rs1.next()) {
                d[count] = rs1.getString("District");
                n[count] = rs1.getString("Name");
                count++;
            }
            String get2 = "SELECT Name \n"
                    + "FROM WeeklyReport \n"
                    + "WHERE Month = " + mon + " AND YearNo = " + year
                    + "\nORDER BY Name";
            ResultSet rs2 = stmt.executeQuery(get2);
            int count2 = 0;
            while (rs2.next()) {
                k[count2] = rs2.getString("Name");
                count2++;
            }
            String get3 = "SELECT Score, Week \n"
                    + "FROM WeeklyReport \n"
                    + "WHERE Month = " + mon + " AND YearNo = " + year
                    + "\nORDER BY Name";
            ResultSet rs3 = stmt.executeQuery(get3);
            int[] arr = new int[5];
            int count3 = 0;
            int count4 = 0;
            while (rs3.next()) {
                arr[Integer.parseInt(rs3.getString("Week")) - 1] = Integer.parseInt(rs3.getString("Score"));
                if (!k[count3].equals(k[count3 + 1])) {
                    ww[index] = new WorkerWeek(d[count4], k[count3], arr);
                    count++;
                    index++;
                    arr = new int[5];
                    count4++;
                }
                count3++;
            }
            for (int i = 0; i < ww.length; i++) {
                for (int j = 0; j < ww.length; j++) {
                    if (ww[i] != null && ww[j] != null && i != j) {
                        if (ww[i].getName().equals(ww[j].getName())) {
                            ww[i] = ww[j];
                            ww[j] = null;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return ww;
    }
    
   /*   Preconditions : The user chooses the option to display the data on families served
    *   Postconditions: The data is fetched from the database (or generated as "---" if no
    *                   data is found for a certain worker) and returned to the GUI class
    *                   to display as a JavaFX TableView
    */
    public FamiliesServed[] famServed() {
        FamiliesServed[] fs = new FamiliesServed[10000];
        int index = 0;
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String get = "SELECT DISTINCT District, Name, SubDistrict, Families\n"
                    + "                 FROM KPMperSDM\n"
                    + "                 ORDER BY District, Name, SubDistrict";
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()) {
                fs[index] = new FamiliesServed(rs.getString("District"), rs.getString("Name"),
                        rs.getString("SubDistrict"));
                index++;
            }
            System.out.println("done");
            String exception = "";
            for (int i = 0; i < fs.length; i++) {
                if (fs[i] != null) {
                    exception += "Name!='" + fs[i].getName() + "' AND ";
                }
            }
            exception = exception.substring(0, exception.length() - 5);
            String get2 = "SELECT DISTINCT District, Name "
                    + "                 FROM Workers\n WHERE " + exception;
            ResultSet rs2 = stmt.executeQuery(get2);
            boolean add = true;
            while (rs2.next()) {

                fs[index] = new FamiliesServed(rs2.getString("District"), rs2.getString("Name"),
                        "---", 0);
                index++;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        FamSort sorter = new FamSort();
        FamiliesServed[] sorted = sorter.sort(fs);
        return sorted;
    }

    public int[] getTotalFam(String name, String SubDistrict, String District) {
        int[] indiv1 = new int[1000];
        int[] indiv2 = new int[1000];
        int[] indiv3 = new int[1000];
        int total1 = 0;
        int index1 = 0;
        int total2 = 0;
        int index2 = 0;
        int total3 = 0;
        int index3 = 0;
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            Statement stmt = (Statement) conn.createStatement();
            String get = "SELECT Families \n"
                    + "FROM KPMperSDM \n"
                    + "\nWHERE Name = '" + name + "'";
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()) {
                indiv1[index1] = Integer.parseInt(rs.getString("Families"));
                index1++;
            }
            for (int i = 0; i < indiv1.length; i++) {
                total1 += indiv1[i];
            }
            String get1 = "SELECT Families \n"
                    + "FROM KPMperSDM \n"
                    + "\nWHERE Name = '" + name + "' AND SubDistrict = '" + SubDistrict + "'";
            ResultSet rs1 = stmt.executeQuery(get1);
            while (rs1.next()) {
                indiv2[index2] = Integer.parseInt(rs1.getString("Families"));
                index2++;
            }
            for (int i = 0; i < indiv1.length; i++) {
                total2 += indiv2[i];
            }
            String get2 = "SELECT Families \n"
                    + "FROM KPMperSDM \n"
                    + "\nWHERE District = '" + District + "'";
            ResultSet rs2 = stmt.executeQuery(get2);
            while (rs2.next()) {
                indiv3[index3] = Integer.parseInt(rs2.getString("Families"));
                index3++;
            }
            for (int i = 0; i < indiv3.length; i++) {
                total3 += indiv3[i];
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return new int[]{total1, total2,total3};
    }

    public WorkerCalc[] retWork(String month, int year) {
        String workerArray[] = new String[1000];
        int scoreArray[] = new int[1000];
        WorkerCalc[] wd = new WorkerCalc[1000];
        int mon = convert(month);
        String get;
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            if (mon != 0) {
                get = "SELECT Name, Score \n"
                        + "FROM WeeklyReport WHERE Month = " + mon + " AND YearNo = " + year + " ORDER BY Name";
            } else {
                get = "SELECT Name, Score \n"
                        + "FROM WeeklyReport WHERE YearNo = " + year + " ORDER BY Name";
            }
            ResultSet rs = stmt.executeQuery(get);
            int count = 0;
            while (rs.next()) {
                workerArray[count] = rs.getString("Name");
                scoreArray[count] = Integer.parseInt(rs.getString("Score"));
                count++;
            }
            wd[0] = new WorkerCalc(workerArray[0], scoreArray[0]);
            count = 1;
            for (int i = 0; i < workerArray.length - 1; i++) {
                if (workerArray[i] != null && workerArray[i + 1] != null) {
                    if (workerArray[i].equals(workerArray[i + 1])) {
                        wd[count - 1].addToScore(scoreArray[i + 1]);
                    } else {
                        wd[count] = new WorkerCalc(workerArray[i + 1], scoreArray[i + 1]);
                        count++;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        for (int j = 0; j < wd.length - 1; j++) {
            for (int i = 0; i < wd.length - 1; i++) {
                if (wd[i] != null && wd[i + 1] != null) {
                    if (wd[i].getTotscor() < wd[i + 1].getTotscor()) {
                        WorkerCalc temp = wd[i + 1];
                        wd[i + 1] = wd[i];
                        wd[i] = temp;
                    }
                }
            }
        }
        return wd;
    }

    public void addActivity(String name, int week, String month, int year, int score) {
        int mon = convert(month);

        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();

            String insert = "INSERT INTO WeeklyReport "
                    + "\nVALUES ('" + name + "'," + week + "," + mon + "," + year + "," + score + ")";
            System.out.println(insert);
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            System.err.println(e);
        }
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(calendar.getTime()).substring(0, 10);
            String time = format.format(calendar.getTime()).substring(12);
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String insert = "INSERT INTO Log\n"
                    + "VALUES ('" + "Inserted activity for: "+name + "','" + currentuser + "','" + date + "','" + time + "')";
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    public String[] getWorkerNames(String dist) {
        String workerArray[] = new String[1000];
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();

            String get = "SELECT DISTINCT Name \n"
                    + "FROM Workers\n"
                    + "WHERE District = '" + dist + "'";
            System.out.println(get);
            ResultSet rs = stmt.executeQuery(get);
            int count = 0;
            while (rs.next()) {
                workerArray[count] = rs.getString("Name");
                count++;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return workerArray;
    }

    public String[] getDistricts() {
        String distArray[] = new String[1000];
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();

            String get = "SELECT DISTINCT District \n"
                    + "FROM Workers ORDER BY District";
            ResultSet rs = stmt.executeQuery(get);
            int count = 0;
            while (rs.next()) {
                distArray[count] = rs.getString("District");
                count++;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return distArray;
    }

    public int[] getYears() {
        int years[] = new int[1000];
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String get = "SELECT DISTINCT YearNo \n"
                    + "FROM WeeklyReport";
            ResultSet rs = stmt.executeQuery(get);
            int count = 0;
            while (rs.next()) {
                years[count] = Integer.parseInt(rs.getString("YearNo"));
                count++;
            }
            //System.out.println("here");
        } catch (SQLException e) {
            System.err.println(e);
        }
        return years;
    }

    public int convert(String month) {
        int mon;
        if (month.equals("January/Januari")) {
            mon = 1;
        } else if (month.equals("February/Februari")) {
            mon = 2;
        } else if (month.equals("March/Maret")) {
            mon = 3;
        } else if (month.equals("April")) {
            mon = 4;
        } else if (month.equals("May/Mei")) {
            mon = 5;
        } else if (month.equals("June/Juni")) {
            mon = 6;
        } else if (month.equals("July/Juli")) {
            mon = 7;
        } else if (month.equals("August/Agustus")) {
            mon = 8;
        } else if (month.equals("September")) {
            mon = 9;
        } else if (month.equals("October/Oktober")) {
            mon = 10;
        } else if (month.equals("November")) {
            mon = 11;
        } else if (month.equals("December/Desember")) {
            mon = 12;
        } else {
            mon = 0;
        }
        return mon;
    }

    public String[] getSubDistricts() {
        String sbDist[] = new String[1000];
        try {
            Connection conn = DriverManager.getConnection(cs, un, pw);
            System.out.println("connected");
            Statement stmt = (Statement) conn.createStatement();
            String get = "SELECT DISTINCT SubDistrict \n"
                    + "FROM KPMperSDM ORDER by SubDistrict";
            ResultSet rs = stmt.executeQuery(get);
            int count = 0;
            while (rs.next()) {
                sbDist[count] = rs.getString("SubDistrict");
                count++;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return sbDist;
    }

}
class testing{
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = format.format(calendar.getTime()).substring(12);
        System.out.println(string);
    }
}