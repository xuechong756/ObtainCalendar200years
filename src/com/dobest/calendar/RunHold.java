package com.dobest.calendar;

import com.dobest.calendar.file.FileManager;
import com.google.gson.Gson;

public class RunHold {
	private boolean bo = false;//�Ƿ���������
	private boolean faile = false;//�����Ƿ�����
	private String dateTime;//���������
	private int countDay;//�� 1900-1-1������
	private boolean changeYear = false;//�Ƿ��л�����һ��
	private FileManager fileManager;//���󵽵����ݴ��·��
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
