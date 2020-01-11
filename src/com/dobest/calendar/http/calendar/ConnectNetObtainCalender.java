package com.dobest.calendar.http.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.dobest.calendar.RunHold;
import com.dobest.calendar.constant.Constent;
import com.dobest.calendar.entity.CalContent;
import com.dobest.calendar.entity.HuangLi;
import com.dobest.calendar.entity.Result;
import com.dobest.calendar.file.FileManager;
import com.dobest.calendar.tools.HttpConnectManager;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ConnectNetObtainCalender {

	private SimpleDateFormat simpleDateFormat;
	private int fromYear;
	private FileOutputStream fileOutputStream;
	
	public ConnectNetObtainCalender(int fromYear)
	{
		this.fromYear = fromYear;
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		initialCalendar();
		try {
			File file = new File(Constent.EXCE + "ex.txt");
			if(!file.exists())
				file.createNewFile();
			fileOutputStream = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Calendar initialCalendar()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, fromYear);//设置哪一年
		calendar.set(Calendar.MONTH, 0); //设置1月
		calendar.set(Calendar.DAY_OF_MONTH, 1); //设置1号
		return calendar;
	}
	
	public String getDataTime(Calendar calendar)
	{
		return simpleDateFormat.format(calendar.getTime());
	}
	
	public int getYear(Calendar calendar)
	{
		String dataTime = simpleDateFormat.format(calendar.getTime());
		String[] year = dataTime.split("-");
		return Integer.parseInt(year[0]);
	}
	
	public int getMonth(Calendar calendar)
	{
		String dataTime = simpleDateFormat.format(calendar.getTime());
		String[] year = dataTime.split("-");
		return Integer.parseInt(year[1]);
	}
	
	public void startup()
	{
		obtainMonth();
		//simulate();
	}
	
	
	private void operatRequest(final RunHold runHold)
	{
		String url = Constent.getCalendarUrl(runHold.getDateTime());
		HttpConnectManager.getInstance().HttpPostConnect(url, null, null, new Callback()
		{
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				try {
					fileOutputStream.write(("请求失败:"+runHold.getDateTime() + "\r\n").getBytes());
					fileOutputStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onResponse(Response arg0) throws IOException {
				String message = arg0.body().string();
			//	System.out.println(message);
				Gson gson = runHold.getGson();
				CalContent calContent = gson.fromJson(message, CalContent.class);
				//test(calContent);
				if("ok".equals(calContent.getMsg()))
				{
					String result = gson.toJson(calContent.getResult());
					runHold.getFileManager().writeFile(result);
					runHold.setBo(true);
					System.out.println(runHold.getDateTime());
				}
				else
				{
					runHold.setFaile(true);
					fileOutputStream.write(("msg不为ok"+runHold.getDateTime() + "\r\n").getBytes());
					fileOutputStream.flush();
				}
			}
		});
	}
	
	public void test(CalContent calContent)
	{
		System.out.println("Status" + calContent.getStatus());
		System.out.println("Msg："+calContent.getMsg());
		Result result = calContent.getResult();
		System.out.println("Year:"+result.getYear());
		System.out.println("Month:"+result.getMonth());
		System.out.println("Day:"+result.getDay());
		System.out.println("Week:"+result.getWeek());
		System.out.println("Lunaryear:"+result.getLunaryear());
		System.out.println("Lunarmonth:"+result.getLunarmonth());
		System.out.println("Lunarday:"+result.getLunarday());
		System.out.println("Ganzhi:"+result.getGanzhi());
		System.out.println("shengxiao:"+result.getShengxiao());
		System.out.println("star:"+result.getStar());
		HuangLi huangli = result.getHuangli();
		System.out.println("Nongli:" + huangli.getNongli());
		System.out.println("taishen:" + huangli.getTaishen());
		System.out.println("wuxing:" + huangli.getWuxing());
		System.out.println("chong:" + huangli.getChong());
		System.out.println("sha:" + huangli.getSha());
		System.out.println("jiri:" + huangli.getJiri());
		System.out.println("zhiri:" + huangli.getZhiri());
		System.out.println("xiongshen:" + huangli.getXiongshen());
		System.out.println("jishenyiqu:" + huangli.getJishenyiqu());
		System.out.println("caishen:" + huangli.getCaishen());
		System.out.println("xishen" + huangli.getXishen());
		System.out.println("fushen" + huangli.getFushen());
		List<String> suici = huangli.getSuici();
		Iterator<String> iterator = suici.iterator();
		while(iterator.hasNext())
			System.out.println("suici:" + iterator.next());
		
		List<String> yi = huangli.getYi();
		iterator = yi.iterator();
		while(iterator.hasNext())
			System.out.println("yi:" + iterator.next());
		
		List<String> ji = huangli.getJi();
		iterator = ji.iterator();
		while(iterator.hasNext())
			System.out.println("ji:" + iterator.next());
	}

	
	//
	private void sleep(int ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void obtainMonth()
	{
		RunHold runHold = new RunHold();
		FileManager fileManager = new FileManager(Constent.FILE_SAVE + this.fromYear + ".txt");
		runHold.setFileManager(fileManager);
		runHold.setGson(new Gson());
		//71001
		for(int month = 0; month < 12; month++)
		{
			Calendar calendar = initialCalendar();
			calendar.set(Calendar.MONTH, month);
			int sumDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			for(int day = 1; day <= sumDay; day++)
			{
				if(runHold.isFaile())
					day--;
				calendar.set(Calendar.DAY_OF_MONTH, day);
				runHold.setDateTime(getDataTime(calendar));
				operatRequest(runHold);
				while(!runHold.isBo())
				{
					sleep(50);	
				}
			}
		}
		fileManager.close();
		try {
			fileOutputStream.write(("Finish:"+this.fromYear + "\r\n").getBytes());
			fileOutputStream.close();
			System.out.println("Finish:"+this.fromYear);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
