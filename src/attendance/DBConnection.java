/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author jugal
 */
public class DBConnection {
    private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/students";
    
    public static Connection getDBConnection(){
        try{
            Class.forName(JDBC_Driver);
            Connection c = DriverManager.getConnection(DB_URL,"root","");
            return c;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
