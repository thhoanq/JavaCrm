package com.java_crm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.java_crm.common.Const;
import com.java_crm.model.AccountsModel;
import com.java_crm.model.AccountsModelImpl;
import com.java_crm.model.RolesModel;
import com.java_crm.model.RolesModelImpl;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Roles;

@WebServlet("/user_add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class UserAddController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	AccountsModel accountsModel = new AccountsModelImpl();
	RolesModel rolesmodel = new RolesModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String emailUser = "";
		HttpSession session = req.getSession();
		if(session == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			emailUser = (String) session.getAttribute(Const.SESSION_USER);
			Accounts user = accountsModel.getAccount(emailUser);
			req.setAttribute("user", user);
			req.getRequestDispatcher("views/user_add.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		Accounts user = new Accounts();
		user.setFullName(req.getParameter("fullname"));
		user.setGender(req.getParameter("gender"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		user.setPhoneNum(req.getParameter("phonenum"));
		
		String author = req.getParameter("author");
		Roles role = new Roles();
		switch(author) {
		case "Admin":
			role.setId(Const.ROLE_ADMIN);
			user.setRole(role);
			break;
		case "Leader":
			role.setId(Const.ROLE_LEADER);
			user.setRole(role);
			break;
		case "User":
			role.setId(Const.ROLE_USER);
			user.setRole(role);
			break;
		}
		//------------------------ UploadFile ------------------------
		String uploadPath = Const.UPLOAD_PATH;
		uploadPath = uploadPath.replace("\\", "/");
		
		String fullSavePath = null;
        if (uploadPath.endsWith("/")) {
            fullSavePath = uploadPath + Const.UPLOAD_DIR;
        } else {
            fullSavePath = uploadPath + "/" + Const.UPLOAD_DIR;
        }
		
		File fileUploadDirectory = new File(fullSavePath);
		if(!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}
		
		String fileName = "";
		for(Part part : req.getParts()) {
			try {
				fileName = extractFileName(part);
				part.write(fullSavePath + File.separator + fileName);			
			} catch(IOException ioObj) {
			}
		}
		//-------------------------------------------------------------
		user.setImage("http://localhost:8080" + req.getContextPath() + "/api/download?fileName=" + fileName);
		
		Boolean isInsert = accountsModel.insertAccounts(user);
		if(isInsert) {
			resp.sendRedirect(req.getContextPath() + "/user_table");
		} else {
			System.out.println("Thêm thất bại!");
			req.getRequestDispatcher("views/user_add.jsp").forward(req, resp);
		}
	}	
	
	private String extractFileName(Part part) {
		String fileName = "";
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for (String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}
}
