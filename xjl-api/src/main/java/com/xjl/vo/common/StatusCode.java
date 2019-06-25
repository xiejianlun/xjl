package com.xjl.vo.common;

/**
 * Description:状态码
 *
 * @author: yuanjianpeng
 * @date:   2018/9/13 上午10:43
 */
public enum StatusCode {

	/**
	 * 成功
	 */
	SUCCESS(1, "成功"),
	/**
	 * 失败
	 */
	FAIL(-1, "失败"),
	/**
	 * 参数错误
	 */
	BAD_ARGUMENT(401, "参数错误"),
	/**
	 * 参数值错误
	 */
	BAD_ARGUMENT_VALUE(402, "参数值错误"),
	/**
	 * 您没有权限访问！
	 */
	UN_AUTH(403, "您没有权限访问！"),
	/**
	 * 错误的请求方式
	 */
	ERROR_REQ_METHOD(405, "错误的请求方式"),
	/**
	 * 请登录
	 */
	UN_LOGIN(505, "请登录"),
	/**
	 * 系统内部错误
	 */
	SERIOUS(502, "系统内部错误"),
	/**
	 * 业务不支持
	 */
	UN_SUPPORT(503, "业务不支持"),
	/**
	 * token认证失败
	 */
	AUTH_FAIL(504, "token认证失败"),
	/**
	 * token重置失败
	 */
	TOKEN_RE_FAIL(505, "token重置失败"),
	/**
	 * token重置成功
	 */
	TOKEN_RE_OK(506, "token重置成功"),
	
	/**
	 * 订单不能支付
	 */
	ORDER_NO_PAY(507,"订单不能支付"),
	
	/**
	 * 订单找不到
	 */
	ORDER_NO_FIND(508,"没有此订单"),
	
	/**
	 * 余额不足
	 */
	BALANCE_LOW(509,"余额不足"),
	
	/**
	 *权限菜单ID重复 
	 */
	PERMISSION_REPEAT(900,"权限菜单ID重复"),
	/**
	 * 重复提交
	 */
	REPEAT_SUBMISSION(901,"重复提交"),
	/**
	 * 已绑定手机
	 */
	BINDINGS_PHONE(1000, "已绑定手机"),
	/**
	 * 未绑定手机
	 */
	UNBIND_PHONE(1001, "未绑定手机");
	
	private Integer code;

	private String msg;

	StatusCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
