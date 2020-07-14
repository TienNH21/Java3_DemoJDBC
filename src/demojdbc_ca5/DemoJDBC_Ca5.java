/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demojdbc_ca5;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author tiennh
 */
public class DemoJDBC_Ca5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String dbUsername = "sa", dbPassword = "Aa@123456";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsv";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            Statement statement = conn.createStatement();
            String query = "SELECT * FROM sinh_vien";

            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int diem = resultSet.getInt("diem");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String maSv = resultSet.getString("ma_sv");
                
                System.out.println(id + " - " + name + " - " + email + " - " +
                    maSv + " - " + diem);
            }
            
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DemoJDBC_Ca5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DemoJDBC_Ca5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
