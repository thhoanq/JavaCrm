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

@WebServlet("/project_add")
public class ProjectAddController extends HttpServlet {

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
			List<Accounts> usersLeader = accountsModel.getListByRole(Const.ROLE_LEADER);
			req.setAttribute("user", user);
			req.setAttribute("usersLeader", usersLeader);
			req.getRequestDispatcher("views/project_add.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		Projects data = new Projects();
		data.setProjectName(req.getParameter("namepro"));
		Accounts userCre = accountsModel.getAccountByName(req.getParameter("usercre"));
		data.setEmployeeCreatId(userCre.getId());
		Accounts userLead = accountsModel.getAccountByName(req.getParameter("userlead"));
		data.setEmployeeLeaderId(userLead.getId());
		data.setDescriptions(req.getParameter("descriptions"));
		data.setDayStart(req.getParameter("dstart"));
		data.setDayEnd(req.getParameter("dend"));
		
		Boolean isInsert = projectsModel.insertProject(data);
		if(isInsert) {
			resp.sendRedirect(req.getContextPath() + "/projects");
		} else {
			System.out.println("Thêm thất bại!");
			req.getRequestDispatcher("views/projects_table.jsp").forward(req, resp);
		}
	}
}
