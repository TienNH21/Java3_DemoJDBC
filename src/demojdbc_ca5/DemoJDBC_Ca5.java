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
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

/**
 *
 * @author tiennh
 */
public class DemoJDBC_Ca5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dbUsername = "sa", dbPassword = "Aa@123456";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsv";

        try {
            Class.forName("SQLServerDriver");
            
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM sinh_vien";
            ResultSet resultSet = statement.executeQuery(query);
            
            while ( resultSet.next() )
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String maSv = resultSet.getString("ma_sv");
                int diem = resultSet.getInt("diem");
                
                System.out.println(id + " - " + name + " - " + email +
                    " - " + password + " - " + maSv + " - " + diem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DemoJDBC_Ca5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DemoJDBC_Ca5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
