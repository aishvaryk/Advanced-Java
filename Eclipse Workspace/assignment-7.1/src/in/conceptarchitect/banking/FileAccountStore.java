package in.conceptarchitect.banking;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;  
 

import in.conceptarchitect.banking.exceptions.InvalidAccountException;

public class FileAccountStore {
	
	int accountCount=0;	
	int lastAccountNumber=0;
	
	public static void main(String args[]){    
        try{
        	BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\AccountStore.txt"));    
          fw.write("AccountNumber, AccountType, Name, Password, Balance");   
          fw.newLine();
          fw.close();    
        }
        catch(Exception e){
        	System.out.println(e);
        }     
         
         
    } 
	
//	public FileAccountStore() {
//		 try{    
//	           FileWriter fw=new FileWriter("C:\\testout.txt");    
//	           fw.write("Welcome to javaTpoint.");    
//	           fw.close();    
//	          }
//		 catch(Exception e){
//			 System.out.println(e);
//			 }    
//	          System.out.println("Success...");    
//	}    



	public int addAccount(BankAccount account, String password) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		 try{
	        	BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\AccountStore.txt"));    
	          fw.write(accountNumber +"," +account.getClass()+"," +account.getName()+"," +password+"," +account.getBalance());   
	          fw.newLine();
	          fw.close();    
	        }
	        catch(Exception e){
	        	System.out.println(e);
	        }    

         System.out.println("Account Added");   
		
		return accountNumber;
	}
	
//	public BankAccount getAccount(int accountNumber) {
//		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts.get(accountNumber)==null)
//			throw new InvalidAccountException(accountNumber);
//
//		//it either returns a valid value or throws an exception
//		return accounts.get(accountNumber);
//	}
//
//	public void removeAccount(int accountNumber) {
//		accounts.replace(accountNumber,null);
//		accountCount--;
//	}
//	
//	public HashMap<Integer, BankAccount> getAllActiveAccounts() {
//		
//		HashMap<Integer,BankAccount>  activeAccounts=new HashMap<>();
//		for(int accountNumber : accounts.keySet()) {
//			if(accounts.get(accountNumber)!=null) {
//				activeAccounts.put(accountNumber, accounts.get(accountNumber));
//			}
//		}
//		
//		return activeAccounts;
//	
//	}
//
//	public int getAccountCount() {
//		// TODO Auto-generated method stub
//		return accountCount;
//	}
	
}
