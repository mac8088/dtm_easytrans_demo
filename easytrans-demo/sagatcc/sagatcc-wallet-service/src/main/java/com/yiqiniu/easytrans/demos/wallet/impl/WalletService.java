package com.yiqiniu.easytrans.demos.wallet.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yiqiniu.easytrans.core.EasyTransFacade;
import com.yiqiniu.easytrans.demos.wallet.api.vo.WalletPayVO.WalletPayRequestVO;

@Component
public class WalletService {
	
	@Resource
	private EasyTransFacade transaction;

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void doTryPay(WalletPayRequestVO param) {
		System.out.println("exec doTryPay start...");
		int update = jdbcTemplate.update(
				"update wallet set freeze_amount = freeze_amount + ? where user_id = ? and (total_amount - freeze_amount) >= ?;", 
				param.getPayAmount(), param.getUserId(), param.getPayAmount());
		
		if(update != 1){
			throw new RuntimeException("can not find specific user id or have not enought money");
		}
		System.out.println("exec doTryPay end...");
	}
	
	@Transactional
	public void doConfirmPay(WalletPayRequestVO param) {
		System.out.println("exec doConfirmPay start...");
		int update = jdbcTemplate.update(
				"update wallet set freeze_amount = freeze_amount - ?, total_amount = total_amount - ? where user_id = ?;", 
				param.getPayAmount(), param.getPayAmount(), param.getUserId());
		
		if(update != 1){
			throw new RuntimeException("thrown exception with the failed confirmPay, not match!");
		}
		
		if(param.getPayAmount()==200 && (new java.util.Random().nextBoolean())) {
			throw new RuntimeException("thrown exception with the failed confirmPay, over 200!");
		}
		
		System.out.println("exec doConfirmPay end...");
	}
	
	@Transactional
	public void doCancelPay(WalletPayRequestVO param) {
		System.out.println("exec doCancelPay start...");
		int update = jdbcTemplate.update(
				"update wallet set freeze_amount = freeze_amount - ? where user_id = ?;", 
				param.getPayAmount(),param.getUserId());
		
		if(update != 1){
			throw new RuntimeException("thrown exception with the failed cancelPay!");
		}
		System.out.println("exec doCancelPay end...");
	}
	

}
