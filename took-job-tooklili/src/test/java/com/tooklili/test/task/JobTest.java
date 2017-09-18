package com.tooklili.test.task;

import javax.annotation.Resource;

import org.junit.Test;

import com.tooklili.task.ClearExpiredItemsJob;
import com.tooklili.task.SyncCouponsToReidsJob;
import com.tooklili.test.BaseTest;

/**
 * @author shuai.ding
 * @date 2017年9月15日下午2:26:33
 */
public class JobTest extends BaseTest{

	@Resource
	private SyncCouponsToReidsJob syncCouponsToReidsJob;
	
	@Resource
	private ClearExpiredItemsJob clearExpiredItemsJob;
	
	@Test
	public void syncCouponsToReidsJobTest(){
		syncCouponsToReidsJob.execute();
	}
	
	@Test
	public void ClearExpiredItemsJobTest(){
		clearExpiredItemsJob.execute();
	}
}