/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_browser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Blogger
 */
public class Utilities 
{
    final JFileChooser fc = new JFileChooser();
    
    ArrayList<File> chromeFiles = new ArrayList<>();
    ArrayList<File> mozillaFiles = new ArrayList<>();
    
    public void toExcel(JTable table, JFrame parentFrame){
        try
        {
            int returnVal = fc.showSaveDialog(parentFrame);
            
            if (returnVal == 0)
            {
                File file = fc.getSelectedFile();
                TableModel model = table.getModel();
                FileWriter excel = new FileWriter(file);

                System.out.println("Hi");

                for(int i = 0; i < model.getColumnCount(); i++)
                {
                    excel.write(model.getColumnName(i) + "\t");
                }

                excel.write("\n");

                for(int i=0; i< model.getRowCount(); i++) 
                {
                    for(int j=0; j < model.getColumnCount(); j++) 
                    {
                        excel.write(model.getValueAt(i,j).toString()+"\t");
                    }
                    excel.write("\n");
                }
                System.out.println("Hi1");
                excel.close();
                JOptionPane.showMessageDialog(parentFrame, "File saved");
            }
            else
            {
                JOptionPane.showMessageDialog(parentFrame, "History File not saved");
            }
            
        }
        catch(Exception e)
        { 
            System.out.println(e); 
        }
    }
    
    public static void setFrameLocation(JFrame frame)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dim.width- 200, dim.height - 200);
        frame.setLocation( (dim.width / 2) - (frame.getSize().width/2), (dim.height/2) - (frame.getSize().height/2));
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public ArrayList<File> getChromeFiles(String path, String filename)
    {
        chromeFiles.clear();
        if (new File(path).exists())
        {
            getChromeFilesRecursive(path,filename);
        }
        return chromeFiles;
    }
    
    public void getChromeFilesRecursive(String path, String filename)
    {
        File root = new File(path);
        File[] list = root.listFiles();

        for ( File f : list ) 
        {
            if ( f.isDirectory() ) 
            {
                if (f.getName().startsWith("Profile") || f.getName().startsWith("Default"))
                {   
                    getChromeFilesRecursive( f.getAbsolutePath(), filename );   
                }
            }
            else 
            {
                if(f.getName().equalsIgnoreCase(filename) && f.length() > 0)
                {
                    chromeFiles.add(f);
                }
            }
        }
    }
    
    public ArrayList<File> getMozillaFiles(String path, String filename)
    {
        mozillaFiles.clear();
        if (new File(path).exists())
        {
            getMozillaFilesRecursive(path,filename);
        }
        return mozillaFiles;
    }
    
    public void getMozillaFilesRecursive(String path, String filename)
    {
        File root = new File(path);
        File[] list = root.listFiles();

        for ( File f : list ) 
        {
            if ( f.isDirectory() ) 
            { 
                getMozillaFilesRecursive( f.getAbsolutePath(), filename );
            }
            else 
            {
                if(f.getName().equalsIgnoreCase(filename) && f.length() > 0)
                {
                    mozillaFiles.add(f);
                }
            }
        }
    }
}
