package com.fs.module.share.weixin.util;

import java.util.Date;

/**
 * 
 * @author wangkai
 * @2016年4月7日 下午12:53:55
 * @desc:日期工具
 */
public class DateUtils {
	
	/**
	 * 获取当前系统的时间戳
	 * @return
	 */
	public static long getCurrentTimestamp(){
		
		long timeStamp = new Date().getTime();
		return timeStamp;
	}

}
