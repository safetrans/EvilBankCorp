package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class AccountDB
{
	private static final String filename = (System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "accounts.txt");
	private static final String delimiter = ",";
			
	@SuppressWarnings("finally")
	
	public static HashMap<String,Account> getAllAccounts()
	{
		
		File acctFile = new File(filename);
		if(acctFile.exists())
		{
			
			//open and read the file
			HashMap<String,Account> acctMap = new HashMap<String,Account>();
			
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(acctFile));
				String line = reader.readLine();
				
				//while there's still a line, continue reading
				while(line != null)
				{
					
					//split each line using ',' as a delimniter
					String parts[] = line.split(",");
					
					//add the key and value to the hashmap
					
					Account currentAcct = new Account();
					currentAcct.setAcctNumber(parts[0]);
					currentAcct.setName(parts[1]);
					currentAcct.setBalance(Double.parseDouble(parts[2]));
					currentAcct.setClosed(Boolean.parseBoolean(parts[3]));
					
					acctMap.put(currentAcct.getAcctNumber(), currentAcct);
					
					line = reader.readLine();
				}
			} 
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				//return hashMap
				return acctMap;
			}
			
		}
		else
		{
			//return new acctMap
			return new HashMap<String,Account>();
		}
	}
	
	public static void saveAccounts(HashMap<String,Account> acctMap)
	{
		//write hashmap to file
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(new File(filename));
			
			//initialize iterator for the hashmap
			Iterator it = acctMap.entrySet().iterator();
			
			while(it.hasNext())
			{
				Map.Entry pair = (Map.Entry)it.next();
				
				//write key, value pair
				Account currentAcct = (Account)pair.getValue();
				
				writer.println(currentAcct.getAcctNumber() + delimiter + currentAcct.getName() + delimiter + currentAcct.getBalance() + delimiter + currentAcct.isClosed());
				it.remove();
			}
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
