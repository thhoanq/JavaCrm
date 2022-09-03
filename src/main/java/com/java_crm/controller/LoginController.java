package com.java_crm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java_crm.common.Const;
import com.java_crm.model.AccountsModel;
import com.java_crm.model.AccountsModelImpl;
import com.java_crm.pojo.Accounts;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	AccountsModel accountModel = new AccountsModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Accounts user = accountModel.getAccount(email);
		
		if(user != null && password.equals(user.getPassword())) {
			HttpSession session = req.getSession();
			session.setAttribute(Const.SESSION_USER, user.getEmail());;		
			resp.sendRedirect(req.getContextPath() + "/index");
		} else {
			System.out.println("That bai");
			req.getRequestDispatcher("views/login.jsp").forward(req, resp);
		}
	}
}
