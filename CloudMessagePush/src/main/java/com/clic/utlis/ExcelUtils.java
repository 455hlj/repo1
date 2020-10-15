package com.clic.utlis;

import com.clic.Exception.FileException;
import com.clic.domain.CloudMessage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>CloudMessagePush</h3>
 * <p>操作和解析Excel文件</p>
 *
 * @author : John Fallen
 * @date : 2020-09-14 10:39
 **/

public class ExcelUtils {
    private static String EXCEL_XLS = ".xls";
    private static String EXCEL_XLSX = ".xlsx";
    public static String feedBack = "";

    public String getFeedBack() {
        return feedBack;
    }



    /**
     * @author: John Fallen
     * @description:
     * @date: 2020/9/12 20:23
     * @param file 传过来的文件
     * @return:
     */
    public static ArrayList<CloudMessage> getExcelContent(File file) throws FileException,IOException {


        ArrayList<CloudMessage> list = new ArrayList<CloudMessage>();

        StringBuilder stringBuilder = new StringBuilder("");
        String filename = file.getName();
        System.out.println(filename);
        //String filePath = file.getAbsolutePath()

        try {
            if(!filename.endsWith(EXCEL_XLSX)&&!filename.endsWith(EXCEL_XLS))
            {
                throw new FileException(FileException.WrongSuffix);
            }
            else{
                Workbook wb = WorkbookFactory.create(file);
                DataFormatter formatter = new DataFormatter();
                //得到第一页
                Sheet sheet = wb.getSheetAt(0);
                //先解析头部
                //
                Map<String,String> mapper = new HashMap<String ,String>();
                Row row1 = sheet.getRow(0);
                System.out.println(row1.getLastCellNum());

                //得到表头 ，包括表头的行数。一般来说是6.
                for (int i = 0; i < row1.getLastCellNum(); i++)
                {
                    Cell cell = row1.getCell(i);
                    CellReference cellRef = new CellReference(row1.getRowNum(), cell.getColumnIndex());

                    //A1 B2
                    String cellRefString = cellRef.formatAsString();
                    String header = formatter.formatCellValue(cell);
                    //得到列的英文，以(A,字段名)的形式存入map，表示pojo对应的字段,之后取出反射set方法
                    String column = cellRefString.substring(0, 1);


                    if (!header.equals(CloudMessage.FIELDSNAME[i]))
                    //如果对应的字段在list集合中，就继续加载，如果不在就打断，抛出异常
                    {
                        throw new FileException(FileException.WrongTemplate);
                    }
                    String field = CloudMessage.FIELDSARR[i];
                    mapper.put(column, field);
                }

                //初始化频道对应字段
                Map<String,String> channels = new HashMap<String,String>();
                channels.put("通知","3506000001");
                channels.put("广东销售支持","3507000001");
                channels.put("东莞专区","4402999911");
                channels.put("e职场","4402999903");
                channels.put("云会办","4400000002");

                //从第二行开始，将对应的数据放到message的字段中
                Row row=null;
                System.out.println(sheet.getLastRowNum());
                //不要用(foreach)
                outer:for(int rowNum =1;rowNum<sheet.getLastRowNum();rowNum++) {

                    row= sheet.getRow(rowNum);
                    CloudMessage cloudMessage =  new CloudMessage();
                    Cell cell=null ;

                    for(int cellColumn=0;cellColumn<row1.getLastCellNum();cellColumn++){
                   //for (Cell cell : row) {
                        //A1 B2等代表单元格位置的
                        cell = row.getCell(cellColumn);
                        CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

                        System.out.print(cellRef.formatAsString());
                        String fieldRef = cellRef.formatAsString().substring(0,1);
                        String fieldName = mapper.get(fieldRef);
                        //得到单元格内容，先输出未格式化的内容
                        // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                        String text = formatter.formatCellValue(cell);
                        if("MessageType".equals(fieldName)) {
                            //如果没有text表示本行为空，文件结束。直接break；
                            if("".equals(text))
                            {break outer;}
                            else {
                                //将得到的中文频道名换成对应的messagetype
                                text = channels.get(text);
                                if (text == null) {
                                    throw new FileException(FileException.WrongData);
                                }
                            }
                        }
                        String setMethodName = "set"+fieldName;
                        //执行对应的set方法。循环执行完成输出对象的内容。
                        Method setMethod = cloudMessage.getClass().getMethod(setMethodName,String.class);
                        setMethod.invoke(cloudMessage,text);
                        System.out.println(text);

                    }
                    stringBuilder.append("信息标题:\t"+cloudMessage.getMessageTitle()+"\r\n");
                    stringBuilder.append("发送对象:\t"+cloudMessage.getToUser()+"\r\n");
                    stringBuilder.append("发送频道:\t"+cloudMessage.getMessageType()+"\r\n");
                    stringBuilder.append("发送形式:\t"+cloudMessage.getContentType()+"\r\n");
                    stringBuilder.append("是否可复制:\t"+cloudMessage.getCopy()+"\r\n");
                    stringBuilder.append("信息内容:\t"+cloudMessage.getMessageContent()+"\r\n");
                    stringBuilder.append("-------------------------------------\r\n");
                    list.add(cloudMessage);
                    System.out.println(cloudMessage);
                    System.out.println("_______________________________________________");
                }
                feedBack = stringBuilder.toString();
                wb.close();
                }
        }
       catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException | InvocationTargetException e) {
            throw new FileException(FileException.WrongData);

        }
        if(list.isEmpty()) {
            throw new FileException(FileException.NoData);
        }
        return list;
    }
    public static Object convertCellValue(Cell cell)
    {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }

}
