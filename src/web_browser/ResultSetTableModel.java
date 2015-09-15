package web_browser;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel
{
    private ResultSet rs;
    private ResultSetMetaData rsMeta;
    private int columnCount;
    private final Vector<String> columnNames = new Vector<String>();
    private final Vector<Object[]> cache = new Vector<Object[]>();
    
    public void setResultSet(ResultSet rs) throws SQLException
    {
        if (this.rs != null)
            this.rs.close();
        cache.clear();
        
        rsMeta = rs.getMetaData();
        columnCount = rsMeta.getColumnCount();
        columnNames.clear();
        for(int col = 1 ; col <= columnCount; col++) 
        {
            columnNames.add(rsMeta.getColumnName(col));
        }
        
        while(rs.next()) 
        {
            Object rowData[] = new Object[columnCount];
            for(int col = 1; col <= columnCount; col++)
            {
                rowData[col - 1] = rs.getObject(col); 
            }
            cache.add(rowData);
        }
        fireTableStructureChanged();
    }
    
    
    public void setResultSet(ArrayList<ResultSet> rs) throws SQLException 
    {
        if (this.rs != null)
            this.rs.close();
        cache.clear();
        
        rsMeta = rs.get(0).getMetaData();
        columnCount = rsMeta.getColumnCount();
        columnNames.clear();
        for(int col = 1 ; col <= columnCount; col++) 
        {
            columnNames.add(rsMeta.getColumnName(col));
        }
        
        for (int i=0; i< rs.size();i++)
        {
            while(rs.get(i).next()) 
            {
                Object rowData[] = new Object[columnCount];
                for(int col = 1; col <= columnCount; col++)
                {
                    rowData[col - 1] = rs.get(i).getObject(col);
                }
                cache.add(rowData);
            }
        }
        fireTableStructureChanged();
    }
    
    
    public void clearAllRows()
    {
        cache.clear();
    }
    
    public void close() throws SQLException 
    {
        rs.close();
    }
   
    @Override 
    public String getColumnName(int column) 
    {
        return columnNames.get(column);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        Object[] row = cache.get(rowIndex);
        return row[columnIndex];
    }
    
    @Override
    public int getRowCount() 
    {
        return cache.size();
    }
   
    @Override
    public int getColumnCount() 
    {
        return columnCount;
    }   
}
