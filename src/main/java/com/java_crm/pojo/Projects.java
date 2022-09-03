package com.java_crm.pojo;

public class Projects {

	private int id;
	private String projectName;
	private int employeeCreatId;
	private int employeeLeaderId;
	private String descriptions;
	private String dayStart;
	private String dayEnd;
	private int progress;
	private int numTask;
	private int TaskPending;
	private int TaskDoing;
	private int TaskDone;

	public int getNumTask() {
		return numTask;
	}
	public void setNumTask(int numTask) {
		this.numTask = numTask;
	}
	public int getTaskPending() {
		return TaskPending;
	}
	public void setTaskPending(int taskPending) {
		TaskPending = taskPending;
	}
	public int getTaskDoing() {
		return TaskDoing;
	}
	public void setTaskDoing(int taskDoing) {
		TaskDoing = taskDoing;
	}
	public int getTaskDone() {
		return TaskDone;
	}
	public void setTaskDone(int taskDone) {
		TaskDone = taskDone;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getEmployeeCreatId() {
		return employeeCreatId;
	}
	public void setEmployeeCreatId(int employeeCreatId) {
		this.employeeCreatId = employeeCreatId;
	}
	public int getEmployeeLeaderId() {
		return employeeLeaderId;
	}
	public void setEmployeeLeaderId(int employeeLeaderId) {
		this.employeeLeaderId = employeeLeaderId;
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
}
