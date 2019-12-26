package com.yiqiniu.easytrans.demos.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiqiniu.easytrans.protocol.BusinessIdentifer;
import com.yiqiniu.easytrans.protocol.saga.SagaTccMethod;
import com.yiqiniu.easytrans.protocol.saga.SagaTccMethodRequest;

public class OrderStatusHandler {

	@BusinessIdentifer(appId = Constant.APPID, busCode = "updateOrderStatus")
	public static class UpdateOrderStatus implements SagaTccMethodRequest {

		private static final long serialVersionUID = 1L;

		private int orderId;

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		
		@Override
		public String toString() {
			return "UpdateOrderStatus [orderId=" + orderId + "]";
		}

	}

	@Component
	public static class UpdateOrderStatusMethods implements SagaTccMethod<UpdateOrderStatus> {

		@Autowired
		private OrderService orderService;

		@Override
		public int getIdempotentType() {
			return IDENPOTENT_TYPE_FRAMEWORK;
		}

		@Override
		public void sagaTry(UpdateOrderStatus param) {
			System.out.println("exec sagaTry start: " + param);
			// DO NOTHING
			System.out.println("exec sagaTry end: " + param);
		}

		@Override
		public void sagaConfirm(UpdateOrderStatus param) {
			System.out.println("exec sagaConfirm start: " + param);
			orderService.confirmOrder(param.getOrderId());
			System.out.println("exec sagaConfirm end: " + param);
		}

		@Override
		public void sagaCancel(UpdateOrderStatus param) {
			System.out.println("exec sagaCancel start: " + param);
			orderService.cancelOrder(param.getOrderId());
			System.out.println("exec sagaCancel end: " + param);
		}

	}

}
