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
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Projects;
import com.java_crm.pojo.Tasks;

@WebServlet("/project_edit")
public class ProjectEditController extends HttpServlet {

	AccountsModel accountsModel = new AccountsModelImpl();
	ProjectsModel projectsModel = new ProjectsModelImpl();
	
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
			int proId = Integer.parseInt(req.getParameter("id_pro"));
			Projects project = projectsModel.getProject(proId);
			req.setAttribute("project", project);
			if(user.getRole().getId() == Const.ROLE_ADMIN) {
				req.getRequestDispatcher("views/pro_edit_ad.jsp").forward(req, resp);
			}else {
				List<Accounts> users = accountsModel.getListByProject(proId);
				req.setAttribute("users", users);
				req.getRequestDispatcher("views/pro_edit_lead.jsp").forward(req, resp);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String emailUser = (String) session.getAttribute(Const.SESSION_USER);
		Accounts user = accountsModel.getAccount(emailUser);
		if(user.getRole().getId() == Const.ROLE_ADMIN) {
			Projects pro = projectsModel.getProject(Integer.parseInt(req.getParameter("id")));
			pro.setProjectName(req.getParameter("namepro"));
			pro.setDescriptions(req.getParameter("descriptions"));
			pro.setDayStart(req.getParameter("dstart"));
			pro.setDayEnd(req.getParameter("dend"));
			boolean isUpdate = projectsModel.updateProject(pro);
			if(isUpdate) {
				resp.sendRedirect(req.getContextPath() + "/projects");
			} else {
				System.out.println("Edit fail.");
				doGet(req, resp);
			}
		} else {
			
		}
		
	}
}
