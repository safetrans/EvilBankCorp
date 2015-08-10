package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import main.Account;
import main.AccountDB;

import org.junit.Test;

public class AccountDBTest
{

	@Test
	public void testGetNewAccount()
	{
		deleteExistingFile();
		HashMap<String,Account> newAccounts = AccountDB.getAllAccounts();
		assertTrue(newAccounts.size()==0);
	}
	
	@Test
	public void testGetExistingAccount()
	{
		deleteExistingFile();
		Account account = new Account();
		account.setAcctNumber("test1");
		HashMap<String,Account> accounts = new HashMap<String,Account>();
		accounts.put(account.getAcctNumber(), account);
		
		AccountDB.saveAccounts(accounts);
		
		accounts = AccountDB.getAllAccounts();
		assertTrue(accounts.get("test1").getAcctNumber().equals("test1"));
	}


	public void deleteExistingFile()
	{
		String filename = (System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "accounts.txt");
		Path filePath = Paths.get(filename);
		File f = new File(filename);
		if(f.exists())
		{
			try
			{
				Files.delete(filePath);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
