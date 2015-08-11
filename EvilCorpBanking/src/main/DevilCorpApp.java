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
		acctMap = inputAccounts(acctMap);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//after while loop, prompt user to enter account number
		
		String hasMoreAccount = "y";
		
		//start transactions
		while(hasMoreAccount.equalsIgnoreCase("y"))
		{
			//save and refresh
			AccountDB.saveAccounts(acctMap);
			acctMap = AccountDB.getAllAccounts();
			
			System.out.println("Enter the account # of the transactions: ");
			String accountNumber = sc.next();
			sc.nextLine();

			acctMap = processTransactions(accountNumber, acctMap);
			
			System.out.print("Is there any other account? y/n");
			hasMoreAccount = sc.next();
			sc.nextLine();
		}
		
		
		//save all account to file finally
		AccountDB.saveAccounts(acctMap);
	
	}
	
	// method to close account and save to file
	private static HashMap<String,Account> closeAccount(Account account,HashMap<String,Account> acctMap)
	{
		account.setClosed(true);
		
		acctMap.put(account.getAcctNumber(), account);
		AccountDB.saveAccounts(acctMap);
		acctMap = AccountDB.getAllAccounts();
		
		return acctMap;
	}
	
	//method to input accounts
	private static HashMap<String,Account> inputAccounts(HashMap<String,Account> acctMap)
	{
		Scanner sc = new Scanner(System.in);
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
				
				boolean isValidAmount = false;
				String amountStr = "";
				while(!isValidAmount)
				{
					System.out.print("Please enter balance amount: ");
					amountStr = sc.next();
					isValidAmount = Validator.validateDoubleWithRange(amountStr, 0, 1000000000);
					
					if(!isValidAmount)
					{
						System.out.println("Invalid amount, please try again!");
					}
				}
				account.setBalance(Double.parseDouble(amountStr));	
				acctMap.put(account.getAcctNumber(), account);
			}	
			
			System.out.print("Please enter account number (enter -1 to stop entering account): ");
			accountNumber = sc.next();
			sc.nextLine();
			
		}
		return acctMap;
	}
	
	//method to process multiple transactions
	private static HashMap<String,Account> processTransactions(String accountNumber,HashMap<String,Account> acctMap)
	{
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		while(!acctMap.containsKey(accountNumber))
		{
			System.out.println("Account not found, please reenter");
			accountNumber = sc.next();
			sc.nextLine();
		}
		
		Account currentAccount = acctMap.get(accountNumber);
		
		if(currentAccount.isClosed())
		{
			System.out.println("Account has been closed");
		}
		else
		{
			System.out.println("Current Balance = " + currentAccount.getFriendlyBalance());
			System.out.print("Please Enter a transaction type (check, deposit, or close) or -1 to finish ");
			String tranType = sc.nextLine();
			
			//process one transaction
			acctMap = processOneTransaction(acctMap,tranType,currentAccount);
			
			
			if(!currentAccount.isClosed())
			{
				//calculate balance after all transactions
				currentAccount.calculateBalance();
				
				//print out balance
				System.out.println("The balance for account " + currentAccount.getAcctNumber() + " is " + currentAccount.getBalance());
				System.out.println(currentAccount.toString());
				//put account to the accounts map
				acctMap.put(currentAccount.getAcctNumber(), currentAccount);
			}
			
			

		}
		
		return acctMap;
	}
	
	//method to process one of the transaction
	private static HashMap<String,Account> processOneTransaction(HashMap<String,Account> acctMap, String tranType, Account currentAccount)
	{
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		while(!tranType.equals("-1"))
		{
			//if close
			if (tranType.equalsIgnoreCase("close"))
			{
				if(currentAccount.getBalance()==0)
				{
					acctMap = closeAccount(currentAccount,acctMap);
					System.out.println("Account number " + currentAccount.getAcctNumber() + " has been closed.");
					
				}
				else
				{
					System.out.println("Account number" + currentAccount.getAcctNumber() + " cannot be closed.");
					
				}
				return acctMap;
			}
			else
			{
				Transaction transaction = new Transaction();
				//if a check
				if (tranType.equalsIgnoreCase("check"))
				{
					transaction.setCheck(true);
				}
				//deposit
				else if (tranType.equalsIgnoreCase("deposit"))
				{
					transaction.setCheck(false);
				}
				else
				{
					System.out.print("Invalid input. Please Enter a transaction type (check, deposit, or close) or -1 to finish ");
					tranType = sc.nextLine();
					continue;
				}
				

				//set amount
				boolean isValidAmount = false;
				String amountStr = "";
				while(!isValidAmount)
				{
					System.out.print("Please enter transaction amount: ");
					amountStr = sc.next();
					isValidAmount = Validator.validateDoubleWithRange(amountStr, 0, 1000000000);
					
					if(!isValidAmount)
					{
						System.out.println("Invalid amount, please try again!");
					}
				}
				
				transaction.setAmount(Double.parseDouble(amountStr));
				sc.nextLine();
				
				//set date
				
				String dateStr = "";
				boolean isValidDate = false;
				while(!isValidDate)
				{
					System.out.print("Please enter transaction date: (format: mm/dd/yyyy)");
					dateStr = sc.next();
					isValidDate = Validator.validateDateWithFormat(dateStr);
					if(!isValidDate)
					{
						System.out.println("Invalid date format, please try again!");
					}
				}

				try
				{
					transaction.setDate(sdf.parse(dateStr));
				} catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sc.nextLine();
				
				//add transaction to current account
				currentAccount.addTranscation(transaction);
				
				//prompt for next transaction
				System.out.print("Please Enter a transaction type (check, deposit, or close) or -1 to finish ");
				tranType = sc.nextLine();
			}

			
		}
		return acctMap;
	}
}
