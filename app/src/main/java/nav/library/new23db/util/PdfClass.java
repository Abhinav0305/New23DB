package nav.library.new23db.util;

/**
 * Created by abhin on 4/7/2017.
 */
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.bouncycastle.jce.provider.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import nav.library.new23db.model.Employee;

/**
 * Created by abhin on 4/2/2017.
 */

public class PdfClass {
    private static String USER_PASSWORD=null;
    private static String OWNER_PASSWORD=null;

    public boolean createPdfFile(Employee employee, String month, Context context){
        boolean status=false;
        if(employee!=null){
            Document document=new Document();
            try{
                String filename=null;
                if(Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED){
                    filename=Environment.getExternalStorageDirectory().toString()+"Salary_"+employee.getEmployeeName()+".pdf";
                }else{
                    filename=context.getFilesDir().toString()+"/Salary_"+employee.getEmployeeName()+".pdf";
                }
                PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(filename));
                writer.setEncryption(employee.getDateOfBirth().getBytes(),employee.getEmployeeName().getBytes(), PdfWriter.ALLOW_PRINTING,PdfWriter.ENCRYPTION_AES_128);
                document.open();
                Paragraph p=new Paragraph();
                p.add("Payslip for the month of "+month);
                p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(1, 1, 1)));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                PdfPTable table=new PdfPTable(2);
                table.setWidthPercentage(100); //Width 100%
                table.setSpacingBefore(10f); //Space before table
                table.setSpacingAfter(10f); //Space after table
                float[] columnWidths = {1.5f, 1.5f};
                table.setWidths(columnWidths);
                table.addCell("Employee Code");
                table.addCell(employee.getEmployeeID());
                table.addCell("Department");
                table.addCell(employee.getDepartment().getDepartmentName());
                table.addCell("Date of Birth");
                table.addCell(employee.getDateOfBirth());
                table.addCell("Date of Joining");
                table.addCell(employee.getDateOfJoining());
                table.addCell("Employee Name");
                table.addCell(employee.getEmployeeName());
                table.addCell("Gender");
                table.addCell(employee.getGender());
                table.addCell("Salary");
                table.addCell(Double.toString(employee.getSalary()));
                document.add(table);
                document.close();
                writer.close();
                status=true;
            }catch (FileNotFoundException e) {
                status=false;
                Log.e("PDF exception aaa",e.getMessage());
            }catch (DocumentException e) {
                status=false;
                Log.e("PDF exception abc",e.getMessage());
            } catch (IOException e) {
                status=false;
                Log.e("PDF exception",e.getMessage());
            }
        }
        return status;
    }
}


