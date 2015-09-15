/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_browser;

import java.sql.*;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Blogger
 */
public class DbController 
{
    public static Connection getConnection(String fileName)
    {
        Connection c = null;
        Statement stmt = null;
        try 
        {
            SQLiteConfig config = new SQLiteConfig();
            config.setReadOnly(true);
            Class.forName("org.sqlite.JDBC");
            if( c == null)
            {
                c = DriverManager.getConnection("jdbc:sqlite:file:" + fileName, config.toProperties());
                
            }
            return c;
        }
        catch (Exception ex) 
        {
            System.out.println("Error :" + ex.getClass().getName() + ": " + ex.getMessage() );
            return null;
        }
    }
}
