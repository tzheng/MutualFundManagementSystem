package controller;

import javax.servlet.http.HttpServletRequest;

public class CustomerChangePwdAction extends Action{

	@Override
	public String getName() {
		return "" +
				"customer-change-pwd.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return null;
	}

}
