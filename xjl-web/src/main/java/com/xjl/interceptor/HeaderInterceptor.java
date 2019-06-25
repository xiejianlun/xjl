package com.xjl.interceptor;

import com.xjl.vo.common.ErrorCode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 头部内容校验拦截器
 * @author huanglian
 *
 */
public class HeaderInterceptor extends HandlerInterceptorAdapter {

	private static Map<String,Boolean> requiredHeads = new HashMap<>();

	static {
		requiredHeads.put("version",false);
		requiredHeads.put("equipType",true);
		requiredHeads.put("equipId",true);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		for(Map.Entry<String,Boolean> entry : requiredHeads.entrySet() ) {
			String key = entry.getKey();
			boolean required = entry.getValue();
			String value = request.getHeader(key);
			if(required && value == null) {
				response.setCharacterEncoding("UTF-8");
				Writer w = response.getWriter();
				w.write("{\"status\":"+ ErrorCode.PARAMETER_REQUIRED.getErrorCode()+",\"message\":\"header param["+key+"] is required \"}");
				w.close();
				return false;
			}
		}
		String lang = request.getHeader("Accept-Language");
		Locale locale = lang != null && (lang.startsWith("en_US") || lang.startsWith("en-US"))? Locale.US : Locale.CHINA;
		LocaleContextHolder.setLocale(locale);
		return true;
	}
	
}
