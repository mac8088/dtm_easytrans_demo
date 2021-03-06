package com.yiqiniu.easytrans.demos.wallet.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yiqiniu.easytrans.demos.wallet.api.vo.WalletPayVO.WalletPayRequestVO;
import com.yiqiniu.easytrans.protocol.saga.SagaTccMethod;

@Component
public class WalletPaySagaTccService implements SagaTccMethod<WalletPayRequestVO>{

	@Resource
	private WalletService wlletService;

	@Override
	public int getIdempotentType() {
		return IDENPOTENT_TYPE_FRAMEWORK;
	}

	@Override
	public void sagaTry(WalletPayRequestVO param) {
		System.out.println("exec WalletPaySagaTccService.sagaTry with: " + param);
		wlletService.doTryPay(param);		
	}

	@Override
	public void sagaConfirm(WalletPayRequestVO param) {
		System.out.println("exec WalletPaySagaTccService.sagaConfirm with: " + param);
		wlletService.doConfirmPay(param);		
	}

	@Override
	public void sagaCancel(WalletPayRequestVO param) {
		System.out.println("exec WalletPaySagaTccService.sagaCancel with: " + param);
		wlletService.doCancelPay(param);		
	}
}
