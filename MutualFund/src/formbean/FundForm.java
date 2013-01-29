package formbean;

import java.util.ArrayList;
import java.util.List;



import org.mybeans.form.FormBean;

public class FundForm extends FormBean {

	private String fundName;
	private String symbol;

	
	public String getFundName()    { return fundName; }
	public String getSymbol() { return symbol;}
	public void setFundName(String name) {
		this.fundName = trimAndConvert(name,"<>\"");
	}
	public void setSymbol(String symbol) {
		this.symbol = trimAndConvert(symbol,"<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.trim().length() == 0) {
			errors.add("Fund Name is required");
		}
		if (symbol == null || symbol.trim().length() == 0) {
			errors.add("Ticker Symbol is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (symbol.trim().length() < 1 || symbol.trim().length() > 5) {
			errors.add("Symbol should be one to five characters");
		}
		if (fundName.trim().length() > 30) {
			errors.add("Fund Name should be less then 30 characters");
		}
		
		if (symbol.matches(".*\\W.*")) errors.add("Symbol should not contain non-word characters");
		if (fundName.matches(".*\\W.*")) errors.add("Fund Name should not contain non-word characters");
		return errors;
	}

}
