package yalongz.common;

import java.util.Locale;

public class Constants {
	public final static Locale	DEFAULT_LOCALE				= Locale.CHINA;
	public static final String	SESSION_LOGIN_KEY			= "loginedUser";
	public static final int		PAGE_SIZE					= 20;
	public static final int		INSERT_SIZE					= 1000;
	public static final String		USER_ID					= "admin";

	/** 获取令牌信息 */
	public final static String	YKT_LOGIN_GETUSERBYTOKEN	= "/login/getuserbytoken";
	/** 获取/更新令牌接口 */
	public final static String	YKT_LOGIN_USERLOGIN			= "/login/userlogin";

}
