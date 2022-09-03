package com.java_crm.api;

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

@WebServlet("/api/insert_us_pro")
public class InsertUserProjectAPI extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute(Const.SESSION_USER);
		Accounts user = accountsModel.getAccount(email);
		Accounts userInsert = accountsModel.getAccount(Integer.parseInt(req.getParameter("id_insert")));
		boolean isInsert = accountsModel.insertAccountProject(userInsert, user.getProject().getId());
		if(isInsert) {
			resp.sendRedirect(req.getContextPath() + "/insert_user_project");
		}
	}
}
