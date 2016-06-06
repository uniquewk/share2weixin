package com.fs.module.share.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fs.module.share.weixin.info.WechatPublicConfig;
/**
 * <p>
 * 学习：https://github.com/keaplogik/Java-Design-Concepts
 * </p>
 * 枚举除了线程安全和防止反射强行调用构造器之外，还提供了自动序列化机制
 * 防止反序列化的时候创建新的对象。
 * 因此，Effective Java推荐尽可能地使用枚举来实现单例。
 */
/**
 * 
 * @author wangkai
 * @2016年3月28日 上午12:28:50
 * @desc:单例终极写法－枚举方式
 */
public enum WechatDevlop {
	
	
	INSTANCE;
	
	private WechatDevlop() {

	}
	
	public WechatPublicConfig buildWxInstance(){
		WechatPublicConfig weconfig = new WechatPublicConfig();
    	Properties prop = new Properties();
		try {
			InputStream is = WechatDevlop.class.getClassLoader().getResourceAsStream("app.properties");
			prop.load(is);
			weconfig.setAppId(prop.getProperty("AppID").trim());
			weconfig.setAppSecret(prop.getProperty("AppSecret").trim());
			is.close();
			return weconfig;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		WechatPublicConfig config= WechatDevlop.INSTANCE.buildWxInstance();
		System.out.println(config);
	}
}
