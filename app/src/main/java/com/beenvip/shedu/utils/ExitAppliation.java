package com.beenvip.shedu.utils;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class ExitAppliation extends Application
{
	private List<Activity> activityList = new LinkedList();
	private static ExitAppliation instance;
	private ExitAppliation()
	{
	}
	// 单例模式中获取唯一的MyApplication实例
	public static ExitAppliation getInstance()
	{
		if (null == instance)
		{
			instance = new ExitAppliation();
		}
		return instance;
	}
	//添加Activity到容器中
	public void addActivity(Activity activity)
	{
		activityList.add(activity);
	}
	// 遍历所有Activity并finish
	public void exit()
	{
		for (Activity activity : activityList)
		{
			activity.finish();
		}

		System.exit(0);
	}
}