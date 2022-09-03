package com.java_crm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java_crm.model.AccountsModel;
import com.java_crm.model.AccountsModelImpl;
import com.java_crm.pojo.Accounts;

@WebServlet("/api/deleta_user_project")
public class DeleteUserProjectAPI extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Accounts user = accountsModel.getAccount(Integer.parseInt(req.getParameter("id_delete")));
		boolean isUpdate = accountsModel.deleteAccountProject(user);
		if(isUpdate) {
			resp.sendRedirect(req.getContextPath() + "/projects");
		}
	}
}
