package com.fs.module.share.weixin.logic;

import com.fs.common.FsErrorCodeMessage;
import com.fs.common.HttpResultResponse;
import com.fs.common.service.annotation.AddGuice;
import com.fs.common.utils.RestUtil;
import com.fs.db.data.MemCacheProxy;
import com.fs.module.share.weixin.info.AccessToken;
import com.fs.module.share.weixin.info.ClientConfig;
import com.fs.module.share.weixin.info.JsapiTicket;
import com.fs.module.share.weixin.info.WechatPublicConfig;
import com.fs.module.share.weixin.util.ConceStrUtils;
import com.fs.module.share.weixin.util.DateUtils;
import com.fs.module.share.weixin.util.SHA1Utils;
import com.fs.module.share.weixin.util.WechatDevlop;
/**
微信公众号开发－分享给微信好友和朋友圈

第一步 获取access_token

https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf61eb1870dc15b30&secret=5307433c787d335be5f59c4cd7c7227c

结果：
{
  "access_token": "O-HGeE3QEqxy5jeS5YhpjGJ4QEAoCPSD2MM5NJ5_Iev_dcvHUygDdeMZ2GAu1yPF6qnB8V5yHCxZ5ZOrmqzP8hQfNzb3m2JMWLGOZ4IkUtQ1eEtN-auXbAPt_F9E6M1OWLDeAEACRJ",
  "expires_in": 7200
}

第二步 获取ticket

https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=wov-yYB7GLkaghUjQQayWVHR6vcdim86FSf9TdvHklMUfruo5k-MxeXgU1xnoDfiHk8m794YVUjBN4mkgShOPP-Q7rcssDyNEZJGc3ZnYlNqmFeqGCafVk9H7cg8BmmVPFUhAIAWMB&type=jsapi

结果：
{
  "errcode": 0,
  "errmsg": "ok",
  "ticket": "sM4AOVdWfPE4DxkXGEs8VH5bGDJtWjsIzLJK38VUVFVygH-4-MvRIXpxbcVmEYHVTxQQq8YOfDtavy6zzlvWkg",
  "expires_in": 7200
}

第三步 签名算法实现
debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
appId: '', // 必填，公众号的唯一标识
timestamp: , // 必填，生成签名的时间戳
nonceStr: '', // 必填，生成签名的随机串
signature: '',// 必填，签名
文档地址
<p>http://mp.weixin.qq.com/wiki/home/</p>
 */
/**
 * 
 * @author wangkai
 * @2016年4月7日 上午11:16:49
 * @desc:微信分享逻辑
 */
@AddGuice
public class WechatShareLogic {
	
	private final static String tokenKey = "token";
	private final static String ticketKey = "ticket";
    private final static String field = "wechat2016";
    private final int expire = 6600;//小于官方提供的过期时间 7200秒 unit: second
	
     /**
      * 微信分享处理逻辑
      * @return
      */
	public HttpResultResponse<ClientConfig> share2Wechat(String url) {
		WechatPublicConfig config = WechatDevlop.INSTANCE.buildWxInstance();
		//从全局缓存提取access_token
		AccessToken token = MemCacheProxy.OBJ.getMemInfo(tokenKey+field,AccessToken.class);
		if(token == null){
			//1获取access_token 
			String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?"
					+ "grant_type=client_credential&appid="+config.getAppId()+""
							+ "&secret="+config.getAppSecret();
			token = RestUtil.getJson(tokenUrl, AccessToken.class);
			if(token == null){
				return HttpResultResponse.error(FsErrorCodeMessage.TOKEN_FAIL.getIndex(),
						FsErrorCodeMessage.TOKEN_FAIL.getName());
			}
			//token不为空则放入缓存
			//MemCacheProxy.OBJ.setMemInfo(tokenKey+field, expire, token);
			MemCacheProxy.OBJ.setexMemInfo(tokenKey+field, expire, token);
			
		}
	
		//从全局缓存获取微信接口临时票据ticket
		JsapiTicket ticket = MemCacheProxy.OBJ.getMemInfo(ticketKey+field,JsapiTicket.class);
		if(ticket == null){
			//2获取jsapi_ticket
			String ticketUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
					+ "access_token="+token.getAccess_token()+"&type=jsapi";
			ticket = RestUtil.getJson(ticketUrl, JsapiTicket.class);
			if(ticket == null){
				return HttpResultResponse.error(FsErrorCodeMessage.TICKET_FAIL.getIndex(),
						FsErrorCodeMessage.TICKET_FAIL.getName());
			}
			//不为空则放入缓存
			MemCacheProxy.OBJ.setexMemInfo(ticketKey+field, expire, ticket);
		}
		//3生成数字签名signature串
		long timestamp = DateUtils.getCurrentTimestamp();//获取当前时间戳
		String nonceStr = ConceStrUtils.createConceStr();//生成随机字符串
		String tempStr = "jsapi_ticket="+ticket.getTicket()+
				"&noncestr="+nonceStr+"&timestamp="+
				timestamp+"&url="+url;
		String signature = SHA1Utils.hex_sha1(tempStr);
		//4封装数据to client
		ClientConfig wxconfig = new ClientConfig(config,nonceStr,timestamp,signature);
		
		return HttpResultResponse.success(0, wxconfig);
	}

}
