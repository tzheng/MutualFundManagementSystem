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
	private int fundListLength;
	private String button;
	private String[] fundId;
	private String[] closingPrice;
	
	public String getDate() {
		return date;
	}

	public int getFundListLength() {
		return fundListLength;
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

	public TransitionDayForm(HttpServletRequest request) {
		date = request.getParameter("specifiedDate");
		fundListLength = Integer.parseInt(request.getParameter("fundListLength"));
		button = request.getParameter("button");
		
		for (int i = 0; i < fundListLength; i++) {
			fundId[i] = request.getParameter("fundId" + i);
			closingPrice[i] = request.getParameter("price" + i);
		}
	}
	
	public boolean isPresent() {
		return button != null && button.equals("Submit");
	}
	
	public Date getSpecifiedDate() {
		try {
			return new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public int[] getFundIdsAsInteger() {
		int[] fundIdInt = new int[fundListLength];
		for (int i = 0; i < fundListLength; i++) {
			fundIdInt[i] = Integer.parseInt(fundId[i]);
		}
		return fundIdInt;
	}
	
	public double[] getClosingPricesAsDouble() {
		double[] price = new double[fundListLength];
		for (int i = 0; i < fundListLength; i++) {
			price[i] = Double.parseDouble(closingPrice[i]);
		}
		return price;
	}
	
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (date == null || date.length() == 0) errors.add("Date is required");
        if (fundListLength < 0) errors.add("Fund List Length cannot be negative");
        if (button == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!button.equals("Submit")) errors.add("Invalid button in Transition Day");
        
        try {
			new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(date);
		} catch (ParseException e) {
			errors.add("Date input should follow the format \"MM-dd-yyyy\".");
		}
        
        for (int i = 0; i < fundListLength; i++) {
        	try {
    			Integer.parseInt(fundId[i]);
    		} catch (NumberFormatException e) {
    			errors.add("The " + i + "th Fund Id is not an integer");
    		}
        	
        	try {
    			if (Double.parseDouble(closingPrice[i]) <= 0) throw new IllegalArgumentException("The " + i + "th closing price is not an positive number.");
    		} catch (NumberFormatException e) {
    			errors.add("The " + i + "th closing price is not an valid number.");
    		} catch (IllegalArgumentException e2) {
    			errors.add(e2.getMessage());
    		}
        }
		
        return errors;
    }
}
