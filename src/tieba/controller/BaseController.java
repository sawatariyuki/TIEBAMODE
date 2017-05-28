package tieba.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	protected String json;
	public void setData(HttpServletRequest req) {
		req.setAttribute("json", json);
	}
}
