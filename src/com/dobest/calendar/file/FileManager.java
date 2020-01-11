package com.dobest.calendar.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileManager {
	
	private FileOutputStream fileOuputStream = null;
	private OutputStreamWriter outputStreamWriter = null;
	
	public FileManager(String path)
	{
		try {
			File file = new File(path);
			if(!file.exists())
				file.createNewFile();
			fileOuputStream = new FileOutputStream(file, true);
			outputStreamWriter = new OutputStreamWriter(fileOuputStream, "UTF-8");
		}catch (UnsupportedEncodingException|FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeFile(String containt)
	{
		try {
			outputStreamWriter.write(containt + "\r\n");
			outputStreamWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		if(outputStreamWriter != null)
		{
			try {
				outputStreamWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(fileOuputStream != null)
		{
			try {
				fileOuputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
