package formbean;

import java.util.ArrayList;
import java.util.List;



import org.mybeans.form.FormBean;

public class FundForm extends FormBean {

	private String fundName;
	private String symbol;

	
	public String getName()    { return fundName; }
	public String getSymbol() { return symbol;}
	public void setName(String name) {
		fundName = trimAndConvert(name,"<>\"");
	}
	public void setSymbol(String symbol) {
		symbol = trimAndConvert(symbol,"<>\"");
	}

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

        if (fundName.matches(".*[<>\"].*")) errors.add("Fund Name may not contain angle brackets or quotes");
        if (symbol.matches(".*[<>\"].*")) errors.add("Symbol may not contain angle brackets or quotes");
		return errors;
	}

}
