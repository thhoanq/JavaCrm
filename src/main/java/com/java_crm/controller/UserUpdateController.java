package com.java_crm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java_crm.common.Const;
import com.java_crm.model.AccountsModel;
import com.java_crm.model.AccountsModelImpl;
import com.java_crm.model.RolesModel;
import com.java_crm.model.RolesModelImpl;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Roles;

@WebServlet("/user_update")
public class UserUpdateController extends HttpServlet {

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
			int idUserUpdate = Integer.parseInt(req.getParameter("id_update"));
			Accounts userUpdate = accountsModel.getAccount(idUserUpdate);
			req.setAttribute("userUpdate", userUpdate);
			
			Accounts user = accountsModel.getAccount(emailUser);
			req.setAttribute("user", user);
			req.getRequestDispatcher("views/user_update.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		Accounts user = new Accounts();
		user.setId(Integer.parseInt(req.getParameter("id")));
		user.setFullName(req.getParameter("fullname"));
		user.setGender(req.getParameter("gender"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		user.setPhoneNum(req.getParameter("phonenum"));
		
		String author = req.getParameter("author");
		Roles role = new Roles();
		switch(author) {
		case "Admin":
			role.setId(1);
			user.setRole(role);
			break;
		case "Leader":
			role.setId(2);
			user.setRole(role);
			break;
		case "User":
			role.setId(3);
			user.setRole(role);
			break;
		}

		
		Boolean isUpdate = accountsModel.updateAccounts(user);
		if(isUpdate) {
			resp.sendRedirect(req.getContextPath() + "/user_table");
		} else {
			System.out.println("Sửa thất bại!");
			req.getRequestDispatcher("views/user_table.jsp").forward(req, resp);
		}
	}	
}
