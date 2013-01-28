/**
 * @author Team Snipers (Team 1)
 * Jan 26, 2013
 */

package formbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class TransitionDayForm {
	private String date;
	private String button;
	private String[] fundId;
	private String[] closingPrice;
	
	public TransitionDayForm(HttpServletRequest request) {
		date = request.getParameter("specifiedDate");
		button = request.getParameter("button");		
		fundId = request.getParameterValues("fundId");
		closingPrice = request.getParameterValues("price");
	}
	
	public String getDate() {
		return date;
	}
	public String getButton() {
		return button;
	}
	public String[] getFundId() {
		return fundId;
	}
	public String[] getClosingPrice() {
		return closingPrice;
	}	
	
	public boolean isPresent() {
		return button != null && button.equals("Submit");
	}
	
	public boolean isFundListEmpty() {
		return fundId == null || closingPrice == null;
	}
	
	public Date getSpecifiedDate() {
		try {
			return new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public int[] getFundIdsAsInteger() {
		int[] fundIdInt = new int[fundId.length];
		for (int i = 0; i < fundId.length; i++) {
			fundIdInt[i] = Integer.parseInt(fundId[i]);
		}
		return fundIdInt;
	}
	
	public double[] getClosingPricesAsDouble() {
		double[] price = new double[closingPrice.length];
		for (int i = 0; i < closingPrice.length; i++) {
			price[i] = Double.parseDouble(closingPrice[i]);
		}
		return price;
	}
	
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (date == null || date.length() == 0) errors.add("Date is required");
        if (button == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!button.equals("Submit")) errors.add("Invalid button in Transition Day");
        
        try {
			new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(date);
		} catch (ParseException e) {
			errors.add("Date input should follow the format \"MM-dd-yyyy\".");
		}
        
        if (isFundListEmpty()) return errors;
        
        for (int i = 0; i < fundId.length; i++) {
        	try {
    			Integer.parseInt(fundId[i]);
    		} catch (NumberFormatException e) {
    			errors.add("The " + i + 1 + "th Fund Id is not an integer");
    		}
        	
        	try {
    			if (Double.parseDouble(closingPrice[i]) <= 0) throw new IllegalArgumentException("The " + i + 1 + "th closing price is not an positive number.");
    		} catch (NumberFormatException e) {
    			errors.add("The " + i + "th closing price is not an valid number.");
    		} catch (IllegalArgumentException e2) {
    			errors.add(e2.getMessage());
    		}
        }
		
        return errors;
    }
}
