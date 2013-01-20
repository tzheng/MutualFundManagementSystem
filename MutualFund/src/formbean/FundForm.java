package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class FundForm {

	private String fundName;
	private String symbol;
	
	
	public FundForm(HttpServletRequest request) {
		fundName = request.getParameter("inputFundid");
		symbol = request.getParameter("inputTickerid");
		
		
	}
	
	public String getName()    { return fundName; }
	public String getSymbol() { return symbol;}
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0) {
			errors.add("Fund Name is required");
		}
		if (symbol == null || symbol.length() == 0) {
			errors.add("Ticker Symbol is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}

        if (fundName.matches(".*[<>\"].*")) errors.add("Comment may not contain angle brackets or quotes");
        if (symbol.matches(".*[<>\"].*")) errors.add("URL may not contain angle brackets or quotes");
		return errors;
	}

}
