package com.tooklili.app.web.controller.common;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tooklili.service.biz.intf.common.QuickResponseCodeService;
import com.tooklili.util.result.PlainResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
public class QrCodeController {

	@Resource
	private QuickResponseCodeService quickResponseCodeService;
	
	/**
	 * 生成二维码base64字符串
	 * @author shuai.ding
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ApiOperation(value = "生成二维码base64字符串",notes = "生成二维码base64字符串")
	@ApiImplicitParam(name = "url", value = "url地址", required = true, dataType = "String",paramType="query")
	@RequestMapping(value = "/getQrCodeBase64",method = RequestMethod.POST)
	@ResponseBody
	public PlainResult<String> getQrCodeBase64(String url) throws UnsupportedEncodingException{		
		PlainResult<String> result =  quickResponseCodeService.getQrCodeBase64(url);		
		return result;
	}
}
