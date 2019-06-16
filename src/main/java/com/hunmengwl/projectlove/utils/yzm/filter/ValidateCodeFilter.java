package com.hunmengwl.projectlove.utils.yzm.filter;

import com.hunmengwl.projectlove.utils.yzm.ImageCode;
import com.hunmengwl.projectlove.utils.yzm.ImageCodeDefaultProperties;
import com.hunmengwl.projectlove.utils.yzm.controller.ValidateCodeController;
import com.hunmengwl.projectlove.utils.yzm.exception.ValidateCodeException;
import com.hunmengwl.projectlove.utils.yzm.exception.ValidateCodeFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
	
	@Autowired
	private ValidateCodeFailureHandler validateCodeFailureHandler;
	
	@Autowired
	private ImageCodeDefaultProperties imageCodeDefaultProperties;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		boolean flag = false;
		for(String url : imageCodeDefaultProperties.getUrl()) {
			if(url.equals(request.getServletPath().toString())) {
				flag = true;
			}
		}
		
		if(flag) {
			try {
				validate(request);
			} catch (ValidateCodeException e) {
				validateCodeFailureHandler.handleValidateFilureException(request,response,e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validate(HttpServletRequest request) throws ServletRequestBindingException, ValidateCodeException {
		
		ImageCode codeInSession = (ImageCode) request.getSession().getAttribute(ValidateCodeController.SESSION_KEY);
		
		String codeInRequest = ServletRequestUtils.getRequiredStringParameter(request, "imageCode");
		
		if(codeInRequest == null) {
			throw new ValidateCodeException("验证码不能为空");
		}
		
		if(codeInSession == null) {
			throw new ValidateCodeException("验证码不存在");
		}
		
		if(codeInSession.isExpried()) {
			request.getSession().removeAttribute(ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}
		
		if(!codeInRequest.equalsIgnoreCase(codeInSession.getCode())) {
			throw new ValidateCodeException("验证码不匹配");
		}
		
		request.getSession().removeAttribute(ValidateCodeController.SESSION_KEY);
	}

}
