package main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Account
{
	private String name;
	private String acctNumber;
	private double balance ;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private boolean isClosed=false;
	
	

	public boolean isClosed()
	{
		return isClosed;
	}
	public void setClosed(boolean isClosed)
	{
		this.isClosed = isClosed;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAcctNumber()
	{
		return acctNumber;
	}
	public void setAcctNumber(String acctNumber)
	{
		this.acctNumber = acctNumber;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	public ArrayList<Transaction> getTransactions()
	{
		return transactions;
	}
	public double calculateBalance()
	{
		Collections.sort(transactions);
		
		for (Transaction transaction : transactions)
		{		
			transaction.setBeforeBalance(balance);
			if(transaction.isCheck())
			{
				if((balance - transaction.getAmount()) < 0)
				{
					balance = balance - transaction.getAmount() - 35;
					transaction.setOverdraft(true);
				}
				else
				{
					balance = balance - transaction.getAmount();
				}
			}
			else
			{
				balance = balance + transaction.getAmount();
			}
			transaction.setAfterBalance(balance);
		}

		return balance;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public String getFriendlyBalance()
	{
		return "$ " + String.format("%.2f",balance);
	}

	
	public void addTranscation(Transaction transaction)
	{
		transactions.add(transaction);
	}
	
	@Override
	public String toString()
	{
		String returnStr = String.format("%-80s","---------");
		 returnStr +="\n" + "name: " + name + "\n" +
				"account number :" + acctNumber + "\n";
		returnStr += String.format("%-20s%-15s%15s%15s%15s", "Transaction Type","Date","Amount","Overdraft","Balance");
		returnStr += "\n";
		returnStr += String.format("%-20s%-15s%15s%15s%15s", "----------------","----","------","---------","-------");
		returnStr += "\n";
		Collections.sort(transactions);
		for(int i =0; i < transactions.size(); i++)
		{
			returnStr += String.format("%-20s%-15s%15s%15s%15s",
					transactions.get(i).getTransactionType(),
					transactions.get(i).getFriendlyDate(),
					transactions.get(i).getFriendlyAmount(),
					transactions.get(i).getOverdraftStatus(),
					transactions.get(i).getFriendlyAfterBalance()
					);
			returnStr += "\n";
		}
		returnStr += "Balance = " + "$" + String.format("%.2f", balance);
		return returnStr;		
	}
}
