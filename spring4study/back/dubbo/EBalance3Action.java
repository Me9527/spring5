package com.cpst.postal.settlement.epacket.action;

import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.demo.StudyDubboService;
import com.alibaba.dubbo.demo.StudyVO;
import com.cpst.framework.base.BaseAction;
import com.cpst.framework.base.exception.BizLayerException;
import com.cpst.framework.base.util.JsonUtil;
import com.cpst.postal.settlement.epacket.services.IEBalanceService;
import com.cpst.postal.settlement.epacket.services.IEYBConfigService;
import com.cpst.postal.settlement.epacket.task02.EybContext;
import com.cpst.postal.settlement.epacket.task02.EybContextHolder;
import com.cpst.postal.settlement.epacket.util.IResourceSyner;

public class EBalance3Action extends BaseAction {
	private IEBalanceService eBalance03Service;
	private IEYBConfigService eYBConfigService;
	private int dataSize;
	private String balancePeriond;
	private String isFromXml;
	private IResourceSyner resourceSyner ;
	private DemoService demoService;
	private StudyDubboService studyDubboService;
	

	
	public void reloadConfig() throws Exception {
//		String s = demoService.sayHello("行EYB分省结");	//How do i handle Exceptions, biz Exception, sys Exception, dubbo exception etc.
//		System.out.println(s);
//		
//		StudyVO svo = new StudyVO();	svo.setTermFeeCount(dataSize);
//		StudyVO rs = studyDubboService.learn(dataSize);
//		System.out.println(rs);
//		
//		rs = studyDubboService.learn(svo);
//		System.out.println(rs);
		
		int t = studyDubboService.save(888);
		System.out.println(t);

	}


	public void makeOracleEybFeeArrayType() throws Exception {
		eYBConfigService.makeOracleEybFeeArrayType();
		JsonUtil.toStringShortDateFormat("MakeOracleEybFeeArrayType success.");
	}
	
	public void test() throws Exception {
		JsonUtil.toStringShortDateFormat("server is start...");
	}
	
	public IEBalanceService geteBalance03Service() {
		return eBalance03Service;
	}

	public void seteBalance03Service(IEBalanceService eBalance03Service) {
		this.eBalance03Service = eBalance03Service;
	}

	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	public String getBalancePeriond() {
		return balancePeriond;
	}

	public void setBalancePeriond(String balancePeriond) {
		this.balancePeriond = balancePeriond;
	}

	public IEYBConfigService geteYBConfigService() {
		return eYBConfigService;
	}

	public void seteYBConfigService(IEYBConfigService eYBConfigService) {
		this.eYBConfigService = eYBConfigService;
	}

	public IResourceSyner getResourceSyner() {
		return resourceSyner;
	}

	public void setResourceSyner(IResourceSyner resourceSyner) {
		this.resourceSyner = resourceSyner;
	}

	public String getIsFromXml() {
		return isFromXml;
	}

	public void setIsFromXml(String isFromXml) {
		this.isFromXml = isFromXml;
	}
	
	public DemoService getDemoService() {
		return demoService;
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	public StudyDubboService getStudyDubboService() {
		return studyDubboService;
	}

	public void setStudyDubboService(StudyDubboService studyDubboService) {
		this.studyDubboService = studyDubboService;
	}
	
	
}