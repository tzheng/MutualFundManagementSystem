package databean;

import java.util.Date;

public class TransactionBean {

	private int fundId;
	private int customerId;
	private String transactionType;
	private int shares;
	private Date executeDate;
	private int amount;
	
	public int getFundId()					{ return fundId;		}
	public int getCustomerId()			{ return customerId;	}
	public String getTransactionType()		{ return transactionType;}
	public int Shares()					    { return shares;		}
	public Date getExecuteDate()			{ return executeDate;	}
	public int getAmounte()				{ return amount;		}
	
	public void setFundId(int fundId)		{ this.fundId = fundId;	}
	public void setCustomerId(int customerId)		{ this.customerId = customerId;		}
	public void setTransactionType(String transactionType)	{ this.transactionType = transactionType;	}
	public void setShares(int shares)		{ this.shares = shares;	}
	public void setExecuteDate(Date executeDate)		{ this.executeDate = executeDate;		}
	public void setAmount(int amount)	{ this.amount = amount;	}
	
	
	
}



