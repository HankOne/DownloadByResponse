package cn.hank.download;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*String path = this.getServletContext().getRealPath("download/a.jpg");
		System.out.println(path);*/
		
		//获得要下载文件的名称
		String filename = request.getParameter("filename");
		
		//要下载的这个文件的类型-----客户端通过文件的MIME类型去区分类型
		response.setContentType(this.getServletContext().getMimeType(filename));
		//告诉客户端该文件不是直接解析 而是以附件形式打开(下载)
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
	
		//获得文件的绝对路径
		String path = this.getServletContext().getRealPath("download/"+filename);
		//获得文件输入流
		FileInputStream in = new FileInputStream(path);
		//获得输出流--response获得输出流用于向客户端写内容
		ServletOutputStream out = response.getOutputStream();
		//文件拷贝
		int len=0;
		byte[] buffer=new byte[1024];
		if((len=in.read(buffer))>0){
			out.write(buffer,0,len);
		}
		
		in.close();
		// out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}