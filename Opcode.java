package com.example.easyorder;

public class Opcode {
	public final static String loginIn = "1000";
	public final static String loginSucceed = "1001";
	public final static String loginFailed = "1002";
	
	public final static String placeOrder = "1100";
	public final static String orderSucceed = "1101";
	public final static String orderFailed = "1102";
	
	
	public final static String changeOrder = "1200";
	public final static String changeSucceed = "1201";
	public final static String changeFailed = "1202";
	
	public final static String deleteOrder = "1300";
	public final static String deleteSucceed = "1301";
	public final static String deleteFailed = "1302";
	
	public final static String checkOrderList = "1400";
	public final static String checkOrder = "1401";
	
	public final static String mergeTable = "1500";
	public final static String mergeSucceed = "1501";
	public final static String mergeFailed = "1501";
	
	public final static String payOrder = "1600";
	public final static String paySucceed = "1601";
	public final static String payFailed = "1602";
	
}
