package com.fs.module.share.weixin.info;

/**
 * 
 * @author wangkai
 * @2016年4月7日 下午1:16:20
 * @desc:鱼说微信公众号开发配置信息pojo类
 */
public class WechatPublicConfig {

	private String AppId;//
	private String AppSecret;//

	public String getAppId() {
		return AppId;
	}

	public void setAppId(String appId) {
		AppId = appId;
	}

	public String getAppSecret() {
		return AppSecret;
	}

	public void setAppSecret(String appSecret) {
		AppSecret = appSecret;
	}


	@Override
	public String toString() {
		return "WechatPublicConfig [AppId=" + AppId + ", AppSecret="
				+ AppSecret + "]";
	}
	
	

}
