package com.xjl.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by xiejianlun .
 */
@ApiModel(value = "返回结果")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 8318578600764920752L;

	/**
	 * 接口调用成功，不需要返回对象
	 */
	public static <T> Result<T> success() {
		return success(StatusCode.SUCCESS);
	}

	public static <T> Result<T> success(StatusCode statusCode) {
		Result<T> result = new Result<>();
		result.setCode(statusCode.getCode());
		result.setMsg(statusCode.getMsg());
		return result;
	}

	/**
	 * 接口调用成功 并且有数据返回
	 *
	 * @param statusCode
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> success(StatusCode statusCode, T data) {
		Result<T> result = new Result<>();
		result.setCode(statusCode.getCode());
		result.setMsg(statusCode.getMsg());
		result.setData(data);
		return result;
	}

	public static <T> Result<T> successRefresh(T object) {
		Result<T> result = success(StatusCode.TOKEN_RE_OK);
		result.setData(object);
		return result;
	}

	public static <T> Result<T> refreshFail() {
		return success(StatusCode.TOKEN_RE_FAIL);
	}

	/**
	 * 接口调用成功，有返回对象
	 */
	public static <T> Result<T> success(T object) {
		Result<T> result = success(StatusCode.SUCCESS);
		result.setData(object);
		return result;
	}

	/**
	 * 接口调用失败，有错误码和描述，没有返回对象
	 */
	public static <T> Result<T> failure(int code, String msg) {
		Result<T> result = new Result<>();
		result.setCode(code != 0 ? code : -1);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 接口调用失败
	 * 
	 * @param  code
	 * @return
	 */
	public static <T> Result<T> failure(StatusCode code) {
		Result<T> result = new Result<>();
		result.setCode(code.getCode());
		result.setMsg(code.getMsg());
		return result;
	}

	/**
	 * 接口调用失败，有错误字符串码和描述，没有返回对象
	 */
	public static <T> Result<T> httpFailure(Integer httpStatusCode, int code, String msg, String detail) {
		Result<T> result = new Result<>();
		result.setHttpStatusCode(httpStatusCode);
		result.setCode(code);
		result.setDetail(detail);
		result.setMsg(msg);
		return result;
	}

	@ApiModelProperty(value = "返回code")
	private Integer httpStatusCode = 200;
	@ApiModelProperty(value = "业务返回码，1.正确")
	private int code;
	@ApiModelProperty(value = "返回的数据")
	private T data;
	@ApiModelProperty(value = "返回的错误消息")
	private String msg;
	@ApiModelProperty(value = "消息的细节")
	private String detail;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}
