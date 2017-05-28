package tieba.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginServiceInterface {
	public String isValid(HttpServletRequest request,HttpServletResponse response); 
	public String register(HttpServletRequest request,
			HttpServletResponse response);
}
