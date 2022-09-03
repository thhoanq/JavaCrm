package com.java_crm.controller;

import java.io.IOException;
import java.util.List;

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
import com.java_crm.model.TaskModelImpl;
import com.java_crm.model.TasksModel;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Tasks;
import com.java_crm.service.AccountsService;
import com.java_crm.service.AccountsServiceImpl;

@WebServlet("/user_details")
public class UserDetailsController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	TasksModel tasksModel = new TaskModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String emailUser = "";
		HttpSession session = req.getSession();
		if(session == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			emailUser = (String) session.getAttribute(Const.SESSION_USER);
			int idUserDetails = Integer.parseInt(req.getParameter("id_details"));
			Accounts userDetail = accountsModel.getAccount(idUserDetails);
			userDetail.setTaskPending(accountsService.getNumTaskPending(idUserDetails));
			userDetail.setTaskDoing(accountsService.getNumTaskDoing(idUserDetails));
			userDetail.setTaskDone(accountsService.getNumTaskDone(idUserDetails));
			req.setAttribute("userDetails", userDetail);
			
			Accounts user = accountsModel.getAccount(emailUser);
			req.setAttribute("user", user);
			
			List<Tasks> tasks = tasksModel.getListTasksByUser(idUserDetails);
			req.setAttribute("tasks", tasks);
			req.getRequestDispatcher("views/user_details.jsp").forward(req, resp);
		}
	}
}
