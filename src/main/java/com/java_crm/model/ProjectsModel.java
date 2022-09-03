package com.java_crm.model;

import java.util.List;

import com.java_crm.pojo.Projects;

public interface ProjectsModel {

	public Projects getProject(int idProject);
	public List<Projects> getAllProjects();
	public List<Projects> getProjectByIdLeader(int idLeader);
	public boolean insertProject(Projects data);
	public boolean deleteProject(int idProject);
	public boolean updateProject(Projects data);
}
