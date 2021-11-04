package checkTables;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Test {
    private static final String prefix = "A:\\work\\";
    private static final String postfix = ".xls";

    private static final String nameFile1 = prefix + "\\old" + postfix;
    private static final String nameFile2 = prefix + "\\new" + postfix;

    public static void main(String[] args) throws Exception {

        FileInputStream inputStream1 = new FileInputStream(new File(nameFile1));
        FileInputStream inputStream2 = new FileInputStream(new File(nameFile2));

        HSSFWorkbook workbook1 = new HSSFWorkbook(inputStream1);
        HSSFWorkbook workbook2 = new HSSFWorkbook(inputStream2);

        HSSFSheet sheet1 = workbook1.getSheetAt(0);
        HSSFSheet sheet2 = workbook2.getSheetAt(0);

        Iterator<Row> rowIterator1 = sheet1.iterator();
        Iterator<Row> rowIterator2 = sheet2.iterator();

        while (rowIterator1.hasNext()) {
            Row row1 = rowIterator1.next();
            Row row2 = rowIterator2.next();

            Iterator<Cell> cellIterator1 = row1.cellIterator();
            Iterator<Cell> cellIterator2 = row2.cellIterator();

            while (cellIterator1.hasNext()) {

                Cell cell1 = cellIterator1.next();
                Cell cell2 = cellIterator2.next();

                if(!cell1.toString().equals(cell2.toString())){
                   throw new Exception("беда");
                }

                System.out.println(cell1.toString().equals(cell2.toString()));

            }
        }
    }
}
