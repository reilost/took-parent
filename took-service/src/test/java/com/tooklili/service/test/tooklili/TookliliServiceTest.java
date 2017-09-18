package com.tooklili.service.test.tooklili;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.tooklili.model.tooklili.Item;
import com.tooklili.service.biz.api.tooklili.TookliliService;
import com.tooklili.service.test.BaseTest;
import com.tooklili.util.JsonFormatTool;
import com.tooklili.util.result.PageResult;

/**
 * 
 * @author ding.shuai
 * @date 2017年9月16日上午11:23:51
 */
public class TookliliServiceTest extends BaseTest{

	@Resource
	private TookliliService tookliliService;
	
	@Test
	public void queryCouponItemsByCateId(){
		PageResult<Item> result =  tookliliService.queryCouponItemsByCateId(35, 2L, 1069L);		
		logger.info(JsonFormatTool.formatJson(JSON.toJSONString(result)));
	}
}