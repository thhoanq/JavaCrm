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
import com.java_crm.model.ProjectsModel;
import com.java_crm.model.ProjectsModelImpl;
import com.java_crm.model.TaskModelImpl;
import com.java_crm.model.TasksModel;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Projects;
import com.java_crm.pojo.Tasks;
import com.java_crm.service.AccountsService;
import com.java_crm.service.AccountsServiceImpl;
import com.java_crm.service.ProjectsService;
import com.java_crm.service.ProjectsServiceImpl;

@WebServlet("/project_detail")
public class ProjectDetailController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	ProjectsModel projectsModel = new ProjectsModelImpl();
	ProjectsService projectsService = new ProjectsServiceImpl();
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
				
				int idProjDetail = Integer.parseInt(req.getParameter("id_pro"));
				Projects project = projectsModel.getProject(idProjDetail);
				project.setTaskPending(projectsService.getNumTaskPending(idProjDetail));
				project.setTaskDoing(projectsService.getNumTaskDoing(idProjDetail));
				project.setTaskDone(projectsService.getNumTaskDone(idProjDetail));
				req.setAttribute("projectdetail", project);	
				
				List<Accounts> users = accountsService.getUsersByProject(accountsModel.getListByProject(idProjDetail));
				req.setAttribute("users", users);
				
				List<Tasks> tasks = tasksModel.getListTasksByProject(idProjDetail);
				List<Tasks> tasksPending = projectsService.getTasksPending(tasks);
				req.setAttribute("taskspending", tasksPending);
				List<Tasks> tasksDoing = projectsService.getTasksDoing(tasks);
				req.setAttribute("tasksdoing", tasksDoing);
				List<Tasks> tasksDone = projectsService.getTasksDone(tasks);
				req.setAttribute("tasksdone", tasksDone);
				req.getRequestDispatcher("views/project_detail.jsp").forward(req, resp);
			}
	}
}
