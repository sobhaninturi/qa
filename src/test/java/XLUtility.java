import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XLUtility {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static int getRowCount(String xlfile, String xlsheet)throws Exception{
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        int rowcount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;

    }

}
