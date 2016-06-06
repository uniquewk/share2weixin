package com.fs.module.share.weixin.info;
/**
 * 
 * @author wangkai
 * @2016年4月9日 下午12:32:58
 * @desc:客户端验证必填凭据
 */
public class ClientConfig {

	//private boolean debug; // true, //
							// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	private String appId; // 必填，公众号的唯一标识
	private long timestamp;// 必填，生成签名的时间戳
	private String nonceStr;// 必填，生成签名的随机串
	private String signature;// 必填，签名，

	public ClientConfig(WechatPublicConfig config, String nonceStr,
			long timestamp, String signature) {
		this.appId = config.getAppId();
		this.timestamp = timestamp;
		this.signature = signature;
		this.nonceStr = nonceStr;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
