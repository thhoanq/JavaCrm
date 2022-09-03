package com.java_crm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java_crm.model.AccountsModel;
import com.java_crm.model.AccountsModelImpl;

@WebServlet("/api/user_delete")
public class UserDeleteAPI extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idDelete = Integer.parseInt(req.getParameter("id_delete"));
		Boolean isDelete = accountsModel.deleteAccounts(idDelete);
		if(isDelete) {
			resp.sendRedirect(req.getContextPath() + "/user_table");
		} else {
			System.out.println("Delete fall!");
		}
	}
}
