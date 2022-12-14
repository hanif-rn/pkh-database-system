/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenilaianKinerja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 53792
 */
public class TestConn {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:DetailDB;create=true";


    Connection conn;

    public TestConn() {
        try {
            this.conn=DriverManager.getConnection(JDBC_URL);
            if(this.conn !=null){
                System.out.println("connected");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testing(){
        String workerArray[] = new String[1000];
        try {
            Statement stmt = (Statement) conn.createStatement();

            String get = "SELECT Name \n"
                    + "FROM Workers ORDER BY Name";
            ResultSet rs = stmt.executeQuery(get);
            int count = 0;
            while (rs.next()) {
                workerArray[count] = rs.getString("Name");
                count++;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        for (int i = 0; i < 10; i++) {
            if(workerArray!=null){
                System.out.println(workerArray[i]);
            }

        }
    }
    
}
class tester{
    public static void main(String[] args) {
        TestConn tc = new TestConn();
        tc.testing();
    }
}