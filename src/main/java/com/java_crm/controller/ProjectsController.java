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
import com.java_crm.model.ProjectsModel;
import com.java_crm.model.ProjectsModelImpl;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Projects;
import com.java_crm.service.ProjectsService;
import com.java_crm.service.ProjectsServiceImpl;

@WebServlet("/projects")
public class ProjectsController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	ProjectsModel projectsModel = new ProjectsModelImpl();
	ProjectsService projectsService = new ProjectsServiceImpl();
	
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
			if(user.getRole().getId() == 1) {			
				List<Projects> projects = projectsService.getAllProject(projectsModel.getAllProjects());
				req.setAttribute("projects", projects);
			} else {
				List<Projects> projects = projectsService.getAllProject(projectsModel.getProjectByIdLeader(user.getId()));
				req.setAttribute("projects", projects);
			}
			req.getRequestDispatcher("views/projects_table.jsp").forward(req, resp);
		}
	}
}
