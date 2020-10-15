package com.clic.controller;

import com.clic.Exception.FileException;
import com.clic.domain.Admin;
import com.clic.domain.CloudMessage;
import com.clic.utlis.ExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>CloudMessagePush</h3>
 * <p>文件上传处理</p>
 *
 * @author : John Fallen
 * @date : 2020-09-16 11:29
 **/
@Controller
public class FileUploadController {

    public static String TEMPLATE_NAME = "template.xlsx";
    @RequestMapping("/upload")
    @ResponseBody
    public Map parseExcel(@RequestParam("excelFile") MultipartFile excelFile , HttpServletRequest request)
    {
        File excel = new File("") ;
        if(request.getSession().getAttribute("admin")==null)
        {
            request.getRequestDispatcher("/WEB-INF/page/login.jsp");
            return null;
        }
        Admin admin =(Admin)request.getSession().getAttribute("admin");
        String adminUsername = admin.getUsername();
        Map<String,String> resultMap = new HashMap<String,String>();
        String filename = excelFile.getOriginalFilename();

            try {
                //先保存
                String basePath  = request.getServletContext().getRealPath("/uploadExcels");
                File userDirectory =  new File(basePath+"/"+adminUsername);
                if (!userDirectory.exists())
                {
                    userDirectory.mkdirs();//
                }
               /* */


                //上级目录加下级目录,保存excel。
                excel = new File(userDirectory,filename);
                if(!excel.exists())
                {
                    excel.createNewFile();
                }

                String finalExcelPath = excel.getPath();
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(excel));
                out.write(excelFile.getBytes());
                out.flush();
                out.close();
                System.out.println(finalExcelPath);
                //excelFile.transferTo(excel);

                //将得到的list集合暂时存下来，如果下一步在页面点击确定就插入，取消就清空
                CloudMessage.setMessageBatch(ExcelUtils.getExcelContent(excel)) ;

                //ArrayList<CloudMessage> list = ExcelUtils.getExcelContent(excel);
                //批量操作数据库。


                String feedBack = ExcelUtils.feedBack;
                resultMap.put("code", "1");
                resultMap.put("msg", "上传成功");
                resultMap.put("data", feedBack);
                resultMap.put("src", finalExcelPath);
        }catch (IOException e)
            {
                e.printStackTrace();
                resultMap.put("code","0");
                resultMap.put("msg","保存文件出错");
                resultMap.put("data","");
                resultMap.put("src","");

            }  catch (FileException e) {
                resultMap.put("code","0");
                resultMap.put("msg",e.getMessage());
                resultMap.put("data","");
                resultMap.put("src","");
            }
        return resultMap;

    }
    @RequestMapping("/download")
    public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path =request.getServletContext().getRealPath("/download");
        File template = new File(path+"/"+TEMPLATE_NAME);
        System.out.println(template.getPath());
        if(!template.exists()){
            try {
                response.sendError(404,"文件未找到");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(template));
        byte[] buf = new byte[1024];
        int len = 0;
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + template.getName());

       OutputStream out = response.getOutputStream();
         while ((len = br.read(buf)) > 0)
         {      out.write(buf, 0, len);}
        br.close();
        out.close();
    }
}
