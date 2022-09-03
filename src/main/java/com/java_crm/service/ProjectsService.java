package com.java_crm.service;

import java.util.List;

import com.java_crm.pojo.Projects;
import com.java_crm.pojo.Tasks;

public interface ProjectsService {

	public int getNumTask(int idProject);
	public int getNumTaskPending(int idProject);
	public int getNumTaskDoing(int idProject);
	public int getNumTaskDone(int idProject);
	public List<Tasks> getTasksPending(List<Tasks> tasks);
	public List<Tasks> getTasksDoing(List<Tasks> tasks);
	public List<Tasks> getTasksDone(List<Tasks> tasks);
	public List<Projects> getAllProject(List<Projects> pros);
}
