package com.tooklili.app.web.controller.taobao;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tooklili.service.biz.intf.taobao.TaobaoService;
import com.tooklili.util.result.ListResult;
import com.tooklili.util.result.PlainResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author shuai.ding
 * @date 2017年9月25日下午8:35:54
 */
@Controller
@RequestMapping("/taobao")
public class TaobaoController {
	
	@Resource
	private TaobaoService taobaoService;
	
	/**
	 * 通过商品id，查询商品详情页的图片列表
	 * @author shuai.ding
	 * @param numIid
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ApiOperation(value = "通过商品id，查询商品详情页的图片列表",notes = "通过商品id，查询商品详情页的图片列表")
	@ApiImplicitParam(name = "numIid", value = "商品id", required = true, dataType = "String",paramType="query")
	@RequestMapping(value= "/getItemImages",method = RequestMethod.POST)
	@ResponseBody
	public ListResult<String> getItemImages(String numIid) throws UnsupportedEncodingException{
		ListResult<String> result = taobaoService.getItemImages(numIid);		
		return result;
	}
	
	/**
	 * 通过商品id，获取商品的子标题
	 * @author shuai.ding
	 * @param itemId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ApiOperation(value = "通过商品id，获取商品的子标题",notes = "通过商品id，获取商品的子标题")
	@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String",paramType="query")
	@RequestMapping(value = "/getItemSubTitleByItemId" ,method = RequestMethod.POST)
	@ResponseBody
	public PlainResult<String> getItemSubTitleByItemId(String itemId) throws UnsupportedEncodingException{
		return taobaoService.getItemSubTitleByItemId(itemId);
	}

}
