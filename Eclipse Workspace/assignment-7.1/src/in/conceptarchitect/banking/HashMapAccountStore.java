package in.conceptarchitect.banking;

import java.util.HashMap;

import in.conceptarchitect.banking.exceptions.InvalidAccountException;

public class HashMapAccountStore {
	
	int accountCount=0;	
	int lastAccountNumber=0;
	HashMap<Integer, BankAccount> accounts=new HashMap<>();
	

	public int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		accounts.put(accountNumber,account);
		return accountNumber;
	}
	
	public BankAccount getAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts.get(accountNumber)==null)
			throw new InvalidAccountException(accountNumber);

		//it either returns a valid value or throws an exception
		return accounts.get(accountNumber);
	}

	public void removeAccount(int accountNumber) {
		accounts.replace(accountNumber,null);
		accountCount--;
	}
	
	public HashMap<Integer, BankAccount> getAllActiveAccounts() {
		
		HashMap<Integer,BankAccount>  activeAccounts=new HashMap<>();
		for(int accountNumber : accounts.keySet()) {
			if(accounts.get(accountNumber)!=null) {
				activeAccounts.put(accountNumber, accounts.get(accountNumber));
			}
		}
		
		return activeAccounts;
	
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}
	
}
