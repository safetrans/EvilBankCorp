package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
/*
 * You have secured a job as a developer for Evil Corp. 
 * Your assignment is to write an application that will take a list of checks, 
 * debit transactions and withdrawals and credit them against an account.
 * If the account has insufficient funds to process the transaction, 
 * allow them to overdraft their account and then charge the owner a $35 insufficient funds fee.
 * 
 * My application saves the account information after exiting in a file in source folder, and opens it up
 */

public class DevilCorpApp
{
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Evil Corp Savings and Loan");
		
		//get hashmap of account by opening the file
		HashMap<String,Account> acctMap = AccountDB.getAllAccounts();
		
		//promp user for entering an account using while loop
		
		String accountNumber = "";
		Account account;
		
		System.out.print("Please enter account number (enter -1 to stop entering account)");
		accountNumber = sc.next();
		sc.nextLine();
		
		while(!accountNumber.equals("-1"))
		{
			
			
			//if account has been existed, get the current balance from hashmap
			if (acctMap.containsKey(accountNumber))
			{
				//show it to console
				account = acctMap.get(accountNumber);
				System.out.println("Account existed, balance = " + account.getBalance() );
				
			}
			//if account has not created, add to hashmap
			else
			{
				
				account = new Account();
				
				//set account number
				account.setAcctNumber(accountNumber);
				
				//prompt for name
				System.out.print("Enter the name for acct # " + accountNumber + ": " );
				account.setName(sc.nextLine());
				
				// promp user for initial balance
				System.out.print("Enter current balance: ");
				account.setBalance(sc.nextDouble());
				sc.nextLine();
			}
			
			//add account to acctMap
			acctMap.put(account.getAcctNumber(), account);
			
			System.out.print("Please enter account number (enter -1 to stop entering account): ");
			accountNumber = sc.next();
			sc.nextLine();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//after while loop, prompt user to enter account number
		
		String hasMoreAccount = "y";
		

		while(hasMoreAccount.equalsIgnoreCase("y"))
		{
			//save and refresh
			AccountDB.saveAccounts(acctMap);
			acctMap = AccountDB.getAllAccounts();
			
			System.out.println("Enter the account # of the transactions: ");
			accountNumber = sc.next();
			sc.nextLine();
			
			while(!acctMap.containsKey(accountNumber))
			{
				System.out.println("Account not found, please reenter");
				accountNumber = sc.next();
				sc.nextLine();
			}
			
			Account currentAccount = acctMap.get(accountNumber);
			System.out.println("Current Balance = " + currentAccount.getFriendlyBalance());
			System.out.print("Please Enter a transaction type (c for check or d for deposit) or -1 to finish ");
			String tranType = sc.nextLine();
			
			while(!tranType.equals("-1"))
			{
				Transaction transaction = new Transaction();
				
				//if a check
				if (tranType.equalsIgnoreCase("c"))
				{
					transaction.setCheck(true);
				}
				//deposit
				else if (tranType.equalsIgnoreCase("d"))
				{
					transaction.setCheck(false);
				}
				//set amount
				System.out.print("Please enter transaction amount: ");
				transaction.setAmount(sc.nextDouble());
				sc.nextLine();
				
				//set date
				System.out.print("Please enter transaction date: ");
				try
				{
					transaction.setDate(sdf.parse(sc.nextLine()));
				} catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//add transaction to current account
				currentAccount.addTranscation(transaction);
				
				//prompt for next transaction
				System.out.print("Enter a transaction type (c for check or d for deposit) or -1 to finish ");
				tranType = sc.nextLine();
			}
			
			//calculate balance after all transactions
			currentAccount.calculateBalance();
			
			//print out balance
			System.out.println("The balance for account " + currentAccount.getAcctNumber() + " is " + currentAccount.getBalance());
			System.out.println(currentAccount.toString());
			//put account to the accounts map
			acctMap.put(currentAccount.getAcctNumber(), currentAccount);
			
			System.out.print("Is there any other account? y/n");
			hasMoreAccount = sc.next();
			sc.nextLine();
		}
		
		
		//save all account to file finally
		AccountDB.saveAccounts(acctMap);
	}
	

}
