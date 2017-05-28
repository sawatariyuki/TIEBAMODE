package tieba.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tieba.jsontip.JsonTip;
import tieba.utils.SendMessage;

import com.google.gson.Gson;

public class LoginedInterceptor implements HandlerInterceptor {
	public final String CALL_BACK_VALUE="jsonResult";
	public final String CALL_BACK_NAME="callback";
	private JsonTip errorTip=new JsonTip();
	private Gson gson=new Gson();
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2, ModelAndView arg3)
			throws Exception {
	    if(req.getAttribute("json")==null)
	    {
//	    	errorTip.setMessage("none data return");
//	    	resp.getWriter().write(gson.toJson(errorTip));
	    }else{	
	    	resp.getWriter().write(req.getAttribute("json")+"");
	    }
	}
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {		
		if(req.getSession().getAttribute("USERID") == null){
			resp.sendRedirect("/TIEBAMODE/login/loginPage");
			return false;
		}else if(!SendMessage.isUserHasLoggedIn((int)req.getSession().getAttribute("USERID"))){
			resp.sendRedirect("/TIEBAMODE/login/loginPage");
			return false;
		}
		return true;
	}
}
