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
import com.java_crm.model.TaskModelImpl;
import com.java_crm.model.TasksModel;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Tasks;
import com.java_crm.service.AccountsService;
import com.java_crm.service.AccountsServiceImpl;

@WebServlet("/profile")
public class ProfileController extends HttpServlet{
	
	AccountsModel accountsModel = new AccountsModelImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	TasksModel tasksModel = new TaskModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			List<Tasks> tasks = tasksModel.getListTasksByUser(user.getId());
			req.setAttribute("tasks", tasks);
			req.setAttribute("user", user);
			req.getRequestDispatcher("views/profile.jsp").forward(req, resp);
		}
	}
}
