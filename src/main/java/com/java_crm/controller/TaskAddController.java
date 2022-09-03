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
import com.java_crm.model.TaskModelImpl;
import com.java_crm.model.TasksModel;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Projects;
import com.java_crm.pojo.Tasks;

@WebServlet("/task_add")
public class TaskAddController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	TasksModel tasksModel = new TaskModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String emailUser = (String) session.getAttribute(Const.SESSION_USER);
		Accounts user = accountsModel.getAccount(emailUser);
		req.setAttribute("user", user);
		int idAdd = Integer.parseInt(req.getParameter("id_add"));
		Accounts usTask = accountsModel.getAccount(idAdd);
		req.setAttribute("ustask", usTask);
		req.getRequestDispatcher("views/task_add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		Tasks data = new Tasks();
		data.setTaskName(req.getParameter("nametask"));
		data.setDescriptions(req.getParameter("descriptions"));
		data.setDayStart(req.getParameter("daystart"));
		data.setDayEnd(req.getParameter("dayend"));
		data.setSaveEdit(req.getParameter("saveedit"));
		data.setEmployeeGive(req.getParameter("usergive"));
		Projects pro = new Projects();
		pro.setId(Integer.parseInt(req.getParameter("idpro")));
		data.setProject(pro);
		Accounts acc = new Accounts();
		acc.setId(Integer.parseInt(req.getParameter("idus")));
		data.setEmployeeTask(acc);
		boolean isInsert = tasksModel.insertTask(data);
		if(isInsert) {
			resp.sendRedirect(req.getContextPath() + "/project_detail?id_pro=" + pro.getId());
		} else {
			System.out.println("Fail!");
		}
		
	}
}
