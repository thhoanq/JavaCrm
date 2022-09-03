package com.java_crm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java_crm.model.ProjectsModel;
import com.java_crm.model.ProjectsModelImpl;

@WebServlet("/api/project_delete")
public class ProjectDeleteAPI extends HttpServlet {

	ProjectsModel projectsModel = new ProjectsModelImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idDelete = Integer.parseInt(req.getParameter("id_pro"));
		Boolean isDelete = projectsModel.deleteProject(idDelete);
		if(isDelete) {
			resp.sendRedirect(req.getContextPath() + "/projects");
		} else {
			System.out.println("Delete fall!");
		}
	}
}
