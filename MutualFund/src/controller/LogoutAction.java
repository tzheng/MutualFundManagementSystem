package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

public class LogoutAction extends Action {
	
	public LogoutAction(Model model) {
		
	}
	
	public String getName() { return "logout.do"; }
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("customer") != null)
			session.setAttribute("customer", null);
		
		if (session.getAttribute("employee") != null) 
			session.setAttribute("employee", null);
		
		return "index.jsp";
	}
}
