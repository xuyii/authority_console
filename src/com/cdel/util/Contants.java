package com.cdel.util;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Contants {
	public static String siteFlag = "default";//网站标识
	public static String VIEW = "/view";
	public static String OPT = "/operate";
	public static final String CODE = "UTF-8";
	public static final String code = "utf-8";
	public static int JIAOWUORGID = 1;
	// 日志路径
	public static String logPath = "";
	
	// session名
	public static final String ADMIN_USER_ID = "com.chnedu.plat.grant.adminID";
	public static final String ADMIN_USER_NAME = "com_chnedu_plat_grant_adminUser_name";
	public static final String ADMIN_REAL_NAME = "com_chnedu_plat_grant_admin_real_name";
	public static final String ADMIN_PASSWORD = "com_chnedu_plat_grant_admin_password";
	public static final String ADMIN_USER_IS_SUPER = "com_chnedu_plat_grant_adminUser_super";
	public static final String ADMIN_USER_ROLES = "com.chnedu.plat.grant.role";
	public static final String ADMIN_USER_AUTH = "com.chnedu.plat.grant.auth";
	public static final String ADVC_CCSTRATEGY_WEBSITE = "ADVC_CCSTRATEGY_WEBSITE"; // 当前网站
	public static final String QZ_CCSTRATEGY_BUSINESS = "QZ_CCSTRATEGY_BUSINESS"; // 当前专业
	public static final String QZ_CCSTRATEGY_MAJOR = "QZ_CCSTRATEGY_MAJOR"; // 当前辅导
	public static final String QZ_CCSTRATEGY_COURSE = "QZ_CCSTRATEGY_COURSE"; // 当前课程
	public static final String CLASS_TEACHER = "class_teacher"; // 当前用户--班级管理员

	// 验证码
	public static final String YZ = "loginValidate_save_number";

	// cookie名
	public static String COOKIENAME_WEBSITE = siteFlag.toUpperCase() + "_AUTHDefaultWebSite";
	public static String COOKIENAME_BUSINESS = siteFlag.toUpperCase() + "_AUTHDefaultBusiness";
	public static String COOKIENAME_MAJOR = siteFlag.toUpperCase() + "_AUTHDefaultMajor";
	public static String COOKIENAME_COURSE = siteFlag.toUpperCase() + "_AUTHDefaultCourse";

	// 数据库标示
	public static final String DATA_KEY = "dataKey";
	public static final String JIAO_WU = "jiaowu";
	public static final String COURSE_WARE = "courseWare";

	public static int BUFFER_SIZE = 10 * 1024;
	public static int FILE_SIZE = 1024 * 1024 * 10;

	// 有效性
	public static Map<Short, String> status = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "无效");
			put(Short.parseShort("1"), "有效");
		}
	};

	// 基础题型
	public static Map<Short, String> quesTypeIDs = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("1"), "单选题");
			put(Short.parseShort("2"), "多选题");
			put(Short.parseShort("3"), "判断题");
			put(Short.parseShort("4"), "简答题");
			put(Short.parseShort("5"), "阅读理解");
			put(Short.parseShort("6"), "Flash题");
			put(Short.parseShort("7"), "分录题");
			put(Short.parseShort("8"), "电算化题");
			put(Short.parseShort("9"), "填空题");
			put(Short.parseShort("10"), "Excel题");
			put(Short.parseShort("11"), "上传附件题");
		}
	};
	
	// 容错分规则
	/*1、	少选得分，多选错选不得分
	2、	少选得分，多选错选扣分，允许负分
	3、	少选得分，多选错选扣分，不允许负分*/

	public static Map<Short, String> splitScoreType = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("1"), "少选得分，多选错选不得分");
			put(Short.parseShort("2"), "少选得分，多选错选扣分，允许负分");
			put(Short.parseShort("3"), "少选得分，多选错选扣分，不允许负分");
		}
	};

	// 年份
	public static Map<Short, String> yearNos = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("2010"), "2010");
			put(Short.parseShort("2011"), "2011");
			put(Short.parseShort("2012"), "2012");
			put(Short.parseShort("2013"), "2013");
			put(Short.parseShort("2014"), "2014");
			put(Short.parseShort("2015"), "2015");
			put(Short.parseShort("2016"), "2016");
			put(Short.parseShort("2017"), "2017");
			put(Short.parseShort("2018"), "2018");
			put(Short.parseShort("2019"), "2019");
			put(Short.parseShort("2020"), "2020");
		}
	};

	// 网站
	public static Map<Integer, String> siteNames = new LinkedHashMap<Integer, String>() {
		{
			put(1, "会计网");
			put(2, "法律网");
			put(3, "自考网");
			put(4, "建设网");
			put(5, "医学网");
			put(6, "中小学网");
			put(7, "外语网");
			put(8, "考研网");
			put(9, "人事网");
			put(10, "中文网");
			put(11, "招生网");
			put(12, "ITAT网");
		}
	};

	// 服务类型
	public static Map<Short, String> serverTypes = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("85"), "免费");
			put(Short.parseShort("0"), "普通");
			put(Short.parseShort("1"), "实验");
			put(Short.parseShort("2"), "精品");
			put(Short.parseShort("3"), "精品实验");
			put(Short.parseShort("5"), "高端");
			put(Short.parseShort("6"), "精品班新");
			put(Short.parseShort("7"), "实验班新");
			put(Short.parseShort("8"), "定制班");
		}
	};

	// 开通状态
	public static Map<Short, String> openStatuss = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "未开通");
			put(Short.parseShort("1"), "已开通");
		}
	};

	public static Map<Short, String> showStatuss = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "隐藏");
			put(Short.parseShort("1"), "显示");
		}
	};

	public static Map<Short, String> yesornos = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "否");
			put(Short.parseShort("1"), "是");
		}
	};

	// 知识点考频指数
	public static Map<Short, String> pointLevels = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("1"), "一星级");
			put(Short.parseShort("2"), "二星级");
			put(Short.parseShort("3"), "三星级");
			put(Short.parseShort("4"), "四星级");
			put(Short.parseShort("5"), "五星级");
		}
	};
	// 是否是高频知识点考频指数
	public static Map<Short, String> isHighFrequency = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "不是");
			put(Short.parseShort("1"), "是");
		}
	};

	// 角色类别
	public static Map<Integer, String> roleCategorys = new LinkedHashMap<Integer, String>() {
		{
			put(1, "超级管理员");
			put(2, "学生");
			put(3, "教师");
			put(4, "系主任");
		}
	};

	// 系统
	public static Map<Integer, String> systemTypes = new LinkedHashMap<Integer, String>() {
		{
			put(1, "成绩查看系统");
		}
	};


	//获取不同网站的名字
	public static Map<Integer, String> mainTypes =  new LinkedHashMap<Integer, String>() {
		{
			put(1, "accMain");
			put(2, "chinalawMain");
			put(3, "zikao365Main");
			put(4, "jiansheMain");
			put(5, "medMain");
			put(6, "g12eMain");
			put(7, "for68Main");
			put(9, "chinatatMain");
		}
	};

	public static String getMain(Integer siteID){
		String mainName=mainTypes.get(siteID);
		return mainName;
	}


	// 日志路径
	public static Map<String, String> logs = new LinkedHashMap<String, String>() {
		{
			put("default", "/web/webserver/jboss/advc.chinaacc.com_21130/logs/auth");
		}
	};
	
	//职位
	public static Map<Integer, String> isHeader = new LinkedHashMap<Integer, String>(){
		{
			put(0, "超级管理员");
			put(1, "经理");
			put(2, "组长");
			put(3, "组员");
			put(4, "督导");
		}
	};
	
}
