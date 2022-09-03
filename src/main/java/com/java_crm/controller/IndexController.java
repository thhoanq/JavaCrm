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
import com.java_crm.pojo.Accounts;
import com.java_crm.service.AccountsService;
import com.java_crm.service.AccountsServiceImpl;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	
	AccountsModel accountsModel = new AccountsModelImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailUser = "";
		HttpSession session = req.getSession(false);
		if(session == null) {
			resp.sendRedirect(req.getContextPath() + "/login");		
		} else {
			emailUser = (String) session.getAttribute(Const.SESSION_USER);
			Accounts user = accountsModel.getAccount(emailUser);
			user.setTaskPending(accountsService.getNumTaskPending(user.getId()));
			user.setTaskDoing(accountsService.getNumTaskDoing(user.getId()));
			user.setTaskDone(accountsService.getNumTaskDone(user.getId()));
			req.setAttribute("user", user);
			req.getRequestDispatcher("views/index.jsp").forward(req, resp);
		}
	}
}
