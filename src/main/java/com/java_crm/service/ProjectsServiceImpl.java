package com.java_crm.service;

import java.util.ArrayList;
import java.util.List;

import com.java_crm.model.TaskModelImpl;
import com.java_crm.model.TasksModel;
import com.java_crm.pojo.Projects;
import com.java_crm.pojo.Tasks;

public class ProjectsServiceImpl implements ProjectsService {

	TasksModel tasksModel = new TaskModelImpl();
	
	@Override
	public int getNumTask(int idProject) {
		// TODO Auto-generated method stub
		List<Tasks> tasks = tasksModel.getListTasksByProject(idProject);
		return tasks.size();
	}

	@Override
	public int getNumTaskPending(int idProject) {
		// TODO Auto-generated method stub
		int numTaskPending = 0;
		List<Tasks> tasks = tasksModel.getListTasksByProject(idProject);
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 1) {
				numTaskPending++;
			}
		}
		return numTaskPending;
	}

	@Override
	public int getNumTaskDoing(int idProject) {
		// TODO Auto-generated method stub
		int numTaskDoing = 0;
		List<Tasks> tasks = tasksModel.getListTasksByProject(idProject);
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 2) {
				numTaskDoing++;
			}
		}
		return numTaskDoing;
	}

	@Override
	public int getNumTaskDone(int idProject) {
		// TODO Auto-generated method stub
		int numTaskDone = 0;
		List<Tasks> tasks = tasksModel.getListTasksByProject(idProject);
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 3) {
				numTaskDone++;
			}
		}
		return numTaskDone;
	}

	@Override
	public List<Tasks> getTasksPending(List<Tasks> tasks) {
		// TODO Auto-generated method stub
		List<Tasks> tasksPending = new ArrayList<Tasks>();
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 1) {
				tasksPending.add(task);
			}
		}
		return tasksPending;
	}

	@Override
	public List<Tasks> getTasksDoing(List<Tasks> tasks) {
		// TODO Auto-generated method stub
		List<Tasks> tasksDoing = new ArrayList<Tasks>();
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 2) {
				tasksDoing.add(task);
			}
		}
		return tasksDoing;
	}

	@Override
	public List<Tasks> getTasksDone(List<Tasks> tasks) {
		// TODO Auto-generated method stub
		List<Tasks> tasksDone = new ArrayList<Tasks>();
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 3) {
				tasksDone.add(task);
			}
		}
		return tasksDone;
	}

	@Override
	public List<Projects> getAllProject(List<Projects> pros) {
		// TODO Auto-generated method stub
		for (Projects pro : pros) {
			pro.setNumTask(getNumTask(pro.getId()));
			float numTaskDone = getNumTaskDone(pro.getId());	
			if(numTaskDone == 0) {
				pro.setProgress(0);
			} else {
				int progress = (int) ((numTaskDone / getNumTask(pro.getId())) * 100);
				pro.setProgress(progress);
			}
		}
		return pros;
	}

}
