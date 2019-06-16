package com.hunmengwl.projectlove.utils.yzm;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 
 * @author wanghuan
 *
 */
public interface ValidateCodeGenerator {
	ImageCode createImageCode(HttpServletRequest req) throws IOException;
}
