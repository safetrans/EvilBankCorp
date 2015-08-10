package main;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Transaction implements Comparable<Transaction>
{
	private double amount;
	private Date date;
	private boolean isCheck;
	private boolean isOverdraft = false;
	private double beforeBalance;
	private double afterBalance = 0;
	
	public String getOverdraftStatus()
	{
		if(isOverdraft)
		{
			return "$ -35.00";
		}
		else
		{
			return "no";
		}
	}
	
	public String getFriendlyAfterBalance()
	{
		return "$ " + String.format("%.2f",afterBalance);
	}
	
	public boolean isOverdraft()
	{
		return isOverdraft;
	}
	public void setOverdraft(boolean isOverdraft)
	{
		this.isOverdraft = isOverdraft;
	}
	public double getBeforeBalance()
	{
		return beforeBalance;
	}
	public void setBeforeBalance(double beforeBalance)
	{
		this.beforeBalance = beforeBalance;
	}
	public double getAfterBalance()
	{
		return afterBalance;
	}
	public void setAfterBalance(double afterBalance)
	{
		this.afterBalance = afterBalance;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public boolean isCheck()
	{
		return isCheck;
	}
	public void setCheck(boolean isCheck)
	{
		this.isCheck = isCheck;
	}
	
	public String getFriendlyAmount()
	{
		
		String returnStr = "";
		if(isCheck)
		{
			returnStr += "$ -" + String.format("%.2f",amount); 
		}
		else
		{
			returnStr += "$ " + String.format("%.2f",amount); 
		}
		return returnStr;
	}
	
	public String getFriendlyDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}
	
	public String getTransactionType()
	{
		if(isCheck)
		{
			return "Check";
		}
		else
		{
			return "Deposit";
		}
	}
	
	@Override
	public int compareTo(Transaction other)
	{
		return getDate().compareTo(other.getDate());
	}
	
	
}
