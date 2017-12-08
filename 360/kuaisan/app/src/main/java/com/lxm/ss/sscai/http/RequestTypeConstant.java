package com.lxm.ss.sscai.http;

/**
 * 网络请求返回类型常量类
 * 
 * @author lxm
 * 
 */
public class RequestTypeConstant {

	public static final int SERVER_RETURN_OK = 0;// 判断数据返回的是true
	public static final int SERVER_RETURN_NOT_LOGIN = 4001;// 判断是否
	// false
	public static final int RETURN_INITJSON_DATA = 1000;
	public static final int RETURN_JSON_MESSAGE = 1001;
	//新的规则解析
	public static final int RETURN_JSON_MESSAGE_NEW = 1002; //解析外一层 ，不进一步解析body
	public static final int RETURN_INITJSON_DATA_NEW = 1003;//进一步解析body


	public static final int REQUEST_STATUE_SWITCH = 10001;//
	public static final int REQUEST_MY_SWITCH = 10002;//
	public static final int REQUEST_GET_HTML_STRING= 10003;//进一




}
