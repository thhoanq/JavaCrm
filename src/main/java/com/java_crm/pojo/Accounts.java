package com.java_crm.pojo;

public class Accounts {
	private int id;
	private String fullName;
	private String gender;
	private String email;
	private String password;
	private String image;
	private String phoneNum;
	private int taskNum;
	private Roles role;
	private Projects project;
	private int taskPending;
	private int taskDoing;
	private int taskDone;
	
	public int getTaskPending() {
		return taskPending;
	}
	public void setTaskPending(int taskPending) {
		this.taskPending = taskPending;
	}
	public int getTaskDoing() {
		return taskDoing;
	}
	public void setTaskDoing(int taskDoing) {
		this.taskDoing = taskDoing;
	}
	public int getTaskDone() {
		return taskDone;
	}
	public void setTaskDone(int taskDone) {
		this.taskDone = taskDone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Projects getProject() {
		return project;
	}
	public void setProject(Projects project) {
		this.project = project;
	}
}
