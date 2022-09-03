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

@WebServlet("/tasks_all")
public class TasksAllController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
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
			Accounts user = accountsModel.getAccount(emailUser);
			req.setAttribute("user", user);
			if(user.getRole().getId() == Const.ROLE_ADMIN) {
				List<Tasks> tasks = tasksModel.getAllTasks();
				req.setAttribute("tasks", tasks);
				req.getRequestDispatcher("views/tasks_all.jsp").forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/forbidden");
			}
			
		}
	}
}
