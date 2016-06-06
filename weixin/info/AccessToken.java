package com.fs.module.share.weixin.info;
/**
 * 
 * @author wangkai
 * @2016年4月7日 下午12:54:40
 * @desc:access_token POJO类
 */
public class AccessToken {
	
	//授权访问token
	private String access_token = "";
	//token 过期时间 秒
	private int expire_in = 0;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpire_in() {
		return expire_in;
	}

	public void setExpire_in(int expire_in) {
		this.expire_in = expire_in;
	}

}
