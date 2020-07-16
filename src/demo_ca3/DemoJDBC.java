/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_ca3;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author tiennh
 */
public class DemoJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String dbUsername = "sa", dbPassword = "Aa@123456";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsv";
            Class.forName("SQLServerDriver");

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "SELECT * FROM sinh_vien";
            Statement statement = connection.createStatement();

//            statement.getR
            ResultSet resultSet = statement.executeQuery(query);

            while ( resultSet.next() ) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String maSv = resultSet.getString("ma_sv");
                int diem = resultSet.getInt("diem");
                
                System.out.println(id + " - " + name + " - " + email + " - " + 
                    maSv + " - " + diem);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DemoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DemoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
