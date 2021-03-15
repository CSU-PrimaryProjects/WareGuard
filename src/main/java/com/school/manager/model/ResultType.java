package com.school.manager.model;

public enum ResultType {

	SUCCESS(1000, "成功"), //
	WRONG(3000, "未知错误"), //
	
	USER_NOT_EXIST(2000,"用户不存在"),
	PWSSWORD_ERROR(2001,"密码错误"),
	USER_EXIST(2005,"用户已存在"),
	EMP_EXIST(2002,"员工已存在"),
	VCODE_ERROR(2006,"验证码错误"),
	NOT_LOGIN(2007,"请登录"),
	NO_MORE_THAN(2008,"No more than one book per book"),
	NO_MORE_THAN_THREE(2009,"A single person can borrow up to three books"),

	NUMBER_REPEAT(2012,"工号重复！"),
	STUDENT_EXIST(2003,"该人脸信息已存在"),
	FACE_NOT_EXIST(2004,"该人脸信息不存在"),
	PERMISSION_DENIED(3001,"权限不足"),
	BORROW_IN_NOT_EXIST(3002,"借出方不存在"),
	BORROW_IN_NOT_HAVE(3003,"借出方没有该设备"),
	EQUPMENT_NOT_EXIST(3004,"设备不存在"),
	LEADER_NOT_EXIST(3005,"上级领导不存在"),
	LEADER_NOT_EXIST1(3006,"输入的上级领导不正确"),
	E_NUM_FALSE(3007,"盘点设备数量不一致"),


	;

	private int code;
	private String info;

	private ResultType(int code, String info) {
		this.code = code;
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

}
