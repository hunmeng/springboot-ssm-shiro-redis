package com.hunmengwl.projectlove.utils.yzm.controller;

import com.hunmengwl.projectlove.utils.yzm.ImageCode;
import com.hunmengwl.projectlove.utils.yzm.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @author wanghuan
 *
 */
@RestController
@RequestMapping("/validate")
public class ValidateCodeController {
	
	@Autowired
	private ValidateCodeGenerator validateCodeGenerator;
	
	public static final String SESSION_KEY = "SESSION_KEY_VALIDATE_IMAGE";
	public static final String SESSION_REG_KEY = "SESSION_REG_KEY_VALIDATE_IMAGE";


	/**
	 * 登录时的图像验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCode imageCode = validateCodeGenerator.createImageCode(request);
		request.getSession().setAttribute(SESSION_KEY, imageCode.getCode());
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
	@GetMapping("/regImage")
	public void createRegCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCode imageCode = validateCodeGenerator.createImageCode(request);
		request.getSession().setAttribute(SESSION_REG_KEY, imageCode.getCode());
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
}
