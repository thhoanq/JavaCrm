package com.java_crm.model;

import java.util.List;

import com.java_crm.pojo.Accounts;

public interface AccountsModel {

	public boolean insertAccounts(Accounts data);
	public Accounts getAccount(String email);
	public Accounts getAccount(int id);
	public Accounts getAccountByName(String userName);
	public List<Accounts> getListAccount();
	public boolean deleteAccounts(int id);
	public boolean updateAccounts(Accounts user);
	public List<Accounts> getListByRole(int idRole);
	public List<Accounts> getListByProject(int idProject);
	public boolean deleteAccountProject(Accounts user);
	public boolean insertAccountProject(Accounts user, int idPro);
}
