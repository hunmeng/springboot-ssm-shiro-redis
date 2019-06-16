package com.hunmengwl.projectlove.utils.yzm.config;

import com.hunmengwl.projectlove.utils.yzm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author wanghuan
 *
 */
@Configuration
public class ValidateCodeConfig {

	@Autowired
	private ImageCodeDefaultProperties imageCodeDefaultProperties;
	
	@Autowired
	private ImageCodePropertiesConfigurerAdapter imageCodePropertiesConfigurerAdapter;
	
	@Bean
	@ConditionalOnMissingBean(value=ImageCodePropertiesConfigurerAdapter.class)
	public ImageCodePropertiesConfigurerAdapter imageCodePropertiesConfigurer(){
		return new ImageCodePropertiesConfigurer();
	}
	
	@Bean
	@ConditionalOnMissingBean(value=ValidateCodeGenerator.class)
	public ValidateCodeGenerator imageCodeGenertor() {
		ImageCodeGenertor imageCodeGenertor = new ImageCodeGenertor();
		imageCodePropertiesConfigurerAdapter.imageCodePropertiesConfigure(imageCodeDefaultProperties);
		imageCodeGenertor.setImageCodeDefaultProperties(imageCodeDefaultProperties);
		return imageCodeGenertor;
	}
	
}
