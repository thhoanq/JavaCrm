package com.java_crm.service;

import java.util.List;

import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Tasks;

public interface AccountsService {

	public int getNumTask(int idUser);
	public int getNumTaskPending(int idUser);
	public int getNumTaskDoing(int idUser);
	public int getNumTaskDone(int idUser);
	public List<Accounts> getUsersByProject(List<Accounts> accounts);
	public List<Accounts> getListUserProjectNull();
}
