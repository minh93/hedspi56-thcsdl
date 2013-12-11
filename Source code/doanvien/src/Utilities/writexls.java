package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.swing.JTable;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class writexls{

  private WritableCellFormat timesBoldUnderline;
  private WritableCellFormat times;
  private String inputFile;
  
public void setOutputFile(String inputFile) {
  this.inputFile = inputFile;
  }

  public void write(JTable table) throws IOException, WriteException {
    File file = new File(inputFile);
    WorkbookSettings wbSettings = new WorkbookSettings();

    wbSettings.setLocale(new Locale("en", "EN"));

    WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Report", 0);
    WritableSheet excelSheet = workbook.getSheet(0);
    createLabel(excelSheet,table);
    createContent(excelSheet,table);

    workbook.write();
    workbook.close();
  }

  private void createLabel(WritableSheet sheet,JTable table)
      throws WriteException {
   
    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
    times = new WritableCellFormat(times10pt);
    times.setWrap(true);

    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
        UnderlineStyle.NO_UNDERLINE);
    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
    timesBoldUnderline.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBoldUnderline);
    cv.setAutosize(true);

     for(int i = 0;i< table.getColumnCount();i++){
       addCaption(sheet, i, 0, table.getColumnName(i));
     }
  }

  private void createContent(WritableSheet sheet,JTable table) throws WriteException,
      RowsExceededException {
     CellView cell = new CellView();
      for(int i=1;i<table.getRowCount();i++){
            for(int j=0;j<table.getColumnCount();j++){
      addLabel(sheet, j, i, table.getValueAt(i, j).toString());
    }
  }
      for(int j=0;j<table.getColumnCount();j++){
        cell=sheet.getColumnView(j);
        //cell.setAutosize(true);
      cell.setSize(20*256+100); 
        sheet.setColumnView(j, cell);
      }
       
  }
  
  private void addCaption(WritableSheet sheet, int column, int row, String s)
      throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, timesBoldUnderline);
    sheet.addCell(label);
  }

  private void addLabel(WritableSheet sheet, int column, int row, String s)
      throws WriteException, RowsExceededException {
    Label label;
    label = new Label(column, row, s, times);
    sheet.addCell(label);
  }

  public static void writexlss(JTable table,String s) throws WriteException, IOException {
    writexls test = new writexls();
    test.setOutputFile(s);
    test.write(table);
    System.out
        .println("Please check the result file under ");
  }
} 