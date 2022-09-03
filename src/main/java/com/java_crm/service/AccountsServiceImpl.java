package com.java_crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.java_crm.common.Const;
import com.java_crm.model.AccountsModel;
import com.java_crm.model.AccountsModelImpl;
import com.java_crm.model.TaskModelImpl;
import com.java_crm.model.TasksModel;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Tasks;

public class AccountsServiceImpl implements AccountsService {

	TasksModel tasksModel = new TaskModelImpl();
	AccountsModel accountsModel = new AccountsModelImpl();
	
	@Override
	public int getNumTask(int idUser) {
		// TODO Auto-generated method stub
		List<Tasks> tasks = tasksModel.getListTasksByUser(idUser);
		return tasks.size();
	}

	@Override
	public int getNumTaskPending(int idUser) {
		// TODO Auto-generated method stub
		int numTaskPending = 0;
		List<Tasks> tasks = tasksModel.getListTasksByUser(idUser);
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 1) {
				numTaskPending++;
			}
		}
		return numTaskPending;
	}

	@Override
	public int getNumTaskDoing(int idUser) {
		// TODO Auto-generated method stub
		int numTaskDoing = 0;
		List<Tasks> tasks = tasksModel.getListTasksByUser(idUser);
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 2) {
				numTaskDoing++;
			}
		}
		return numTaskDoing;
	}

	@Override
	public int getNumTaskDone(int idUser) {
		// TODO Auto-generated method stub
		int numTaskDone = 0;
		List<Tasks> tasks = tasksModel.getListTasksByUser(idUser);
		for (Tasks task : tasks) {
			if(task.getStatus().getId() == 3) {
				numTaskDone++;
			}
		}
		return numTaskDone;
	}

	@Override
	public List<Accounts> getUsersByProject(List<Accounts> accounts) {
		// TODO Auto-generated method stub
		List<Accounts> users = new ArrayList<Accounts>();
		for (Accounts user : accounts) {
			if(user.getRole().getId() == Const.ROLE_USER) {
				users.add(user);
			}
		}
		return users;
	}

	@Override
	public List<Accounts> getListUserProjectNull() {
		// TODO Auto-generated method stub
		List<Accounts> users = accountsModel.getListAccount();
		List<Accounts> usProNull = new ArrayList<Accounts>();
		for (Accounts us : users) {
			if(Objects.equals(us.getProject().getId(), 0) && us.getRole().getId() == Const.ROLE_USER	) {
				usProNull.add(us);
			}
		}
		return usProNull;
	}
}
