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
import com.java_crm.pojo.Status;
import com.java_crm.pojo.Tasks;

@WebServlet("/task_edit")
public class TaskEditController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
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
			Tasks task = tasksModel.getTask(Integer.parseInt(req.getParameter("id_task")));
			req.setAttribute("task", task);
			req.setAttribute("user", user);
			req.getRequestDispatcher("views/task_edit.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Tasks task = tasksModel.getTask(Integer.parseInt(req.getParameter("id")));
		String uppdateEdit = task.getSaveEdit() + "\n" + req.getParameter("uppdateedit");
		task.setSaveEdit(uppdateEdit);
		String newStatus = req.getParameter("status");
		Status status = new Status();
		switch(newStatus) {
		case "Pending":
			status.setId(1);
			task.setStatus(status);
			break;
		case "Doing":
			status.setId(2);
			task.setStatus(status);
			break;
		case "Done":
			status.setId(3);
			task.setStatus(status);
			break;
		}
		Boolean isUpdate = tasksModel.uppdateTask(task);
		if(isUpdate) {
			resp.sendRedirect(req.getContextPath() + "/profile");
		} else {
			System.out.println("Sửa thất bại!");
			doGet(req, resp);
		}
	}
}
