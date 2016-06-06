package com.fs.module.share.weixin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.HttpResponse;

import com.fs.common.HttpResultResponse;
import com.fs.common.service.controller.AbstrackController;
import com.fs.module.cstory.util.CrossDomainUtil;
import com.fs.module.share.weixin.info.ClientConfig;
import com.fs.module.share.weixin.logic.WechatShareLogic;
import com.google.inject.Inject;
/**
 * 
 * @author wangkai
 * @2016年4月7日 上午11:15:24
 * @desc:微信分享接口
 */
@Path(value = "share")
public class WechatShareController extends AbstrackController {
	
	//微信分享逻辑处理
	private @Inject WechatShareLogic wechatShareLogic;
	
	/**
	 * 获取微信分享配置信息
	 * @param request
	 * @param response
	 * @return
	 */
	@GET
	@Path(value = "weixin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWxConfig(@Context HttpRequest request,
			@Context HttpResponse response,
			@QueryParam(value = "url") final String url) {
		CrossDomainUtil.CrossDomainInsight(request, response);
		HttpResultResponse<ClientConfig> result = wechatShareLogic.share2Wechat(url);
		return Response.status(200).entity(result).build();
	}
	
}
