package com.java.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 分析：
         * 1.获取请求参数，参数为文件名称
         * 2.获取请求信息的请求体，并存储到字节输入流中
         * 3.设置响应头，服务器告诉客户端这次响应体的文件数据类型mime类型和编码格式，以什么格式打开响应体
         * 4.设置响应体，
         */
        String filename = req.getParameter("filename");
        System.out.println(filename);
        //2.使用字节输入流将文件加载到内存,将文件存储到字节输入流
        //找到文件服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/"+filename);
        FileInputStream fileInputStream=new FileInputStream(realPath);
        //3.设置响应头
        String mimeType = servletContext.getMimeType(filename);
        //设响应头类型
        resp.setHeader("content-type",mimeType);
        //设置响应头打开方式
        resp.setHeader("content-disposition","attachment;filename="+filename);
        //将输入流的数据输出到输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] by=new byte[1024];
        int rs=0;
        while ((rs=fileInputStream.read(by))!=-1){
            outputStream.write(by,0,rs);
        }
        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
