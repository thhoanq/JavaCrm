package com.java_crm.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java_crm.common.Const;

@WebServlet("/api/download")
public class FileDownloadServletAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static int BUFFER_SIZE = 1024 * 100;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleRequest(req, resp);
	}
	
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fileName");
		String applicationPath = Const.UPLOAD_PATH;
		applicationPath = applicationPath.replace("\\", "/");
		String downloadPath = null;
        if (applicationPath.endsWith("/")) {
        	downloadPath = applicationPath + Const.UPLOAD_DIR;
        } else {
        	downloadPath = applicationPath + "/" + Const.UPLOAD_DIR;
        }
		String filePath = downloadPath + File.separator + fileName;
		
		File file = new File(filePath);
		OutputStream outStream = null;
		FileInputStream inputStream = null;
		
		if(file.exists()) {
			String mimeType = "application/octet-stream";
			resp.setContentType(mimeType);
			
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
			resp.setHeader(headerKey, headerValue);
		
			try {
			    /**** Get The Output Stream Of The Response ****/
			    outStream = resp.getOutputStream();
			    inputStream = new FileInputStream(file);
			    byte[] buffer = new byte[BUFFER_SIZE];
			    int bytesRead = -1;
			    
			    while((bytesRead = inputStream.read(buffer)) != -1) {
			    	outStream.write(buffer, 0, bytesRead);
			    }    
			} catch(IOException ioExObj) {
			    System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
			} finally {    
			    if(inputStream != null) {
			    	inputStream.close();
			    }
			    outStream.flush();
			    if(outStream != null) {
			    	outStream.close();
			    }
			}
		} else {
		   /***** Set Response Content Type *****/
		   resp.setContentType("text/html");
		   /***** Print The Response *****/
		   resp.getWriter().println("<h3>File "+ fileName +" Is Not Present .....!</h3>");
		  }
	}
}
