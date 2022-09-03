package com.java_crm.pojo;

public class Tasks {

	private int id;
	private String taskName;
	private String descriptions;
	private String dayStart;
	private String dayEnd;
	private String saveEdit;
	private Status status;
	private Projects project;
	private Accounts employeeTask;
	private String employeeGive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getDayStart() {
		return dayStart;
	}
	public void setDayStart(String dayStart) {
		this.dayStart = dayStart;
	}
	public String getDayEnd() {
		return dayEnd;
	}
	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
	}
	public String getSaveEdit() {
		return saveEdit;
	}
	public void setSaveEdit(String saveEdit) {
		this.saveEdit = saveEdit;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Projects getProject() {
		return project;
	}
	public void setProject(Projects project) {
		this.project = project;
	}
	public Accounts getEmployeeTask() {
		return employeeTask;
	}
	public void setEmployeeTask(Accounts employeeTask) {
		this.employeeTask = employeeTask;
	}
	public String getEmployeeGive() {
		return employeeGive;
	}
	public void setEmployeeGive(String employeeGive) {
		this.employeeGive = employeeGive;
	}
}
