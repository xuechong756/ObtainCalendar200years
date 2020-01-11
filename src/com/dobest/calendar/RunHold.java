package com.dobest.calendar;

import com.dobest.calendar.file.FileManager;
import com.google.gson.Gson;

public class RunHold {
	private boolean bo = false;//是否请求到数据
	private boolean faile = false;//数据是否有误
	private String dateTime;//请求的日期
	private int countDay;//距 1900-1-1的天数
	private boolean changeYear = false;//是否切换到下一年
	private FileManager fileManager;//请求到的数据存放路径
	private Gson gson;
	
	
	public Gson getGson() {
		return gson;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	public boolean isBo() {
		if(bo)
		{
			bo = false;
			return true;
		}
		return false;
	}
	public void setBo(boolean bo) {
		this.bo = bo;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getCountDay() {
		return countDay;
	}
	public void setCountDay(int countDay) {
		this.countDay = countDay;
	}
	public boolean isChangeYear() {
		if(changeYear)
		{
			changeYear = false;
			return true;
		}
		return false;
	}
	public void setChangeYear(boolean changeYear) {
		this.changeYear = changeYear;
	}
	public FileManager getFileManager() {
		return fileManager;
	}
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	public boolean isFaile() {
		if(faile)
		{
			faile = false;
			return true;
		}
		return false;
	}
	public void setFaile(boolean faile) {
		this.faile = faile;
	}
	
	
}
