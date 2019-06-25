package com.xjl.vo.common;

public enum ErrorCode 
{
	SUCCESS(0, "成功", "success"),
	//调试错误码
	PARAMETER_REQUIRED(1, "请求参数缺失", ""),
	FORMAT_INVALID(2,"请求参数格式错误",""),
	REPEAT_SUBMISSION(997, "重复提交"),
	INVALID_RESOURCE(998, "无效的资源"),
	//系统内部错误码
	SYSTEM_ERROR(999,"系统内部错误",""),

	;


	/** 错误码. */
	private int errorCode;
	/** 中文描述. */
	private String zhMessage;
	/** 英文描述. */
	private String enMessage;

	private ErrorCode(int errorCode, String zhMessage, String enMessage)
	{
		this.errorCode = errorCode;
		this.zhMessage = zhMessage;
		this.enMessage = enMessage;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param errorCode the error code
	 * @param zhMessage the zh message
	 */
	private ErrorCode(int errorCode, String zhMessage)
	{
		this.errorCode = errorCode;
		this.zhMessage = zhMessage;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param errorCode the error code
	 */
	private ErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	
	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode()
	{
		return errorCode;
	}
	
	/**
	 * Sets the error code.
	 *
	 * @param errorCode the error code
	 */
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	
	/**
	 * Gets the zh message.
	 *
	 * @return the zh message
	 */
	public String getZhMessage()
	{
		return zhMessage;
	}
	
	/**
	 * Sets the zh message.
	 *
	 * @param zhMessage the zh message
	 */
	public void setZhMessage(String zhMessage)
	{
		this.zhMessage = zhMessage;
	}
	
	/**
	 * Gets the en message.
	 *
	 * @return the en message
	 */
	public String getEnMessage()
	{
		return enMessage;
	}
	
	/**
	 * Sets the en message.
	 *
	 * @param enMessage the en message
	 */
	public void setEnMessage(String enMessage)
	{
		this.enMessage = enMessage;
	}
}
