package com.cdel.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartServer implements ServletContextListener {

	public void contextInitialized(ServletContextEvent ctxEvt) {
		initContants(ctxEvt);
		//获取context文件的contextConfigLocation，用于加载spring配置文件
		System.setProperty("contextConfigLocation", Contants.siteFlag);
		System.setProperty("log4jHome", Contants.logPath);
	}

	public void contextDestroyed(ServletContextEvent ctxEvt) {
		System.gc();
	}

	private void initContants(ServletContextEvent ctxEvt){
		//File.separator + "web" + File.separator + "logs" + File.separator + "auth" + siteFlag + "Console";
		Contants.logPath = Contants.logs.get(Contants.siteFlag);

		Contants.COOKIENAME_WEBSITE = Contants.siteFlag.toUpperCase() + "_AUTHDefaultWebSite";
		Contants.COOKIENAME_BUSINESS = Contants.siteFlag.toUpperCase() + "_AUTHDefaultBusiness";
		Contants.COOKIENAME_MAJOR = Contants.siteFlag.toUpperCase() + "_AUTHDefaultMajor";
		Contants.COOKIENAME_COURSE = Contants.siteFlag.toUpperCase() + "_AUTHDefaultCourse";
	}

}
