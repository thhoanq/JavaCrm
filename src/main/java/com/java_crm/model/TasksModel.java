package com.java_crm.model;

import java.util.List;

import com.java_crm.pojo.Tasks;

public interface TasksModel {

	public List<Tasks> getAllTasks();
	public List<Tasks> getListTasksByUser(int idUser);
	public List<Tasks> getListTasksByProject(int idProject);
	public Tasks getTask(int idTask);
	public boolean uppdateTask(Tasks data);
	public boolean insertTask(Tasks data);
}
