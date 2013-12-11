/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import java.awt.Label;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;

/**
 *
 * @author ManhLinh
 */
public class xls {
    public static void FileExcel(JTable table,File  file) throws IOException{
        //File file;
        //file=new File(“DoKhoa_Excel.xls”); cái này làm trong form roi truyen file vao
        
        FileWriter out = new FileWriter(file);
        
        for(int i = 0;i< table.getColumnCount();i++)
        {
            out.write(table.getColumnName(i)+"\t");
        }
        out.write("\n");
        
        for(int i=0;i<table.getRowCount();i++){
            for(int j=0;i<table.getColumnCount();j++){
                out.write(table.getValueAt(i, j).toString()+"\t");
            }
            out.write("\n");
        }
        out.close();
        
    }

}
