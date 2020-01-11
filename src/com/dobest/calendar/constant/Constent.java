package com.dobest.calendar.constant;

public class Constent {
	
	private final static String URL_CALENDAR = "http://api.jisuapi.com/calendar/query?appkey=afc7e26577d3fc60&date=";
	public final static String FILE_SAVE = "E:\\WorkSpace\\EclipseObject\\JavaWork\\ObtainCalendar200years\\calendar\\";
	public final static String SIMULATE_FILE = "E:\\WorkSpace\\EclipseObject\\JavaWork\\ObtainCalendar200years\\simulate\\";
	public final static String EXCE = "E:\\WorkSpace\\EclipseObject\\JavaWork\\ObtainCalendar200years\\exce\\";
	public static String getCalendarUrl(String data)
	{
		return URL_CALENDAR + data;
	}
}
