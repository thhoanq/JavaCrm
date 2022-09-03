package com.java_crm.controller;

import java.io.IOException;
import java.util.List;

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
import com.java_crm.service.AccountsService;
import com.java_crm.service.AccountsServiceImpl;

@WebServlet("/insert_user_project")
public class InsertUserProjectController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	AccountsService accountsServie = new AccountsServiceImpl();
	
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
			List<Accounts> users = accountsServie.getListUserProjectNull();
			req.setAttribute("user", user);
			req.setAttribute("users", users);
			req.getRequestDispatcher("/views/insert_us_pro.jsp").forward(req, resp);		
		}
	}
}
