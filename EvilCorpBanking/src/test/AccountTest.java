package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.Account;
import main.Transaction;

import org.junit.Test;

public class AccountTest
{

	@Test
	public void testCalculateBalance()
	{
		Account account = new Account();
		account.setAcctNumber("7779311");
		account.setName("Homer Simpson");
		account.setBalance(1000);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date1=null,date2=null,date3=null;
		try
		{
			date1 = sdf.parse("08/02/2015");
			date2 = sdf.parse("08/01/2015");
			date3 = sdf.parse("08/01/2015");
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Transaction transaction1 = new Transaction();
		transaction1.setAmount(900);
		transaction1.setCheck(true);
		transaction1.setDate(date1);
		account.addTranscation(transaction1);
		
		Transaction transaction2 = new Transaction();
		transaction2.setAmount(300);
		transaction2.setCheck(true);
		transaction2.setDate(date2);
		account.addTranscation(transaction2);
		
		Transaction transaction3 = new Transaction();
		transaction3.setAmount(200);
		transaction3.setCheck(true);
		transaction3.setDate(date3);
		account.addTranscation(transaction3);
		
		ArrayList<Transaction> transactions = account.getTransactions();
		for (Transaction transaction : transactions)
		{
			System.out.println(transaction.getDate());
		}
		assertTrue(account.calculateBalance() == -435);
		
	}

}
