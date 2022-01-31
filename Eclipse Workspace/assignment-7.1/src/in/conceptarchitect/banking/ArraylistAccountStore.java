package in.conceptarchitect.banking;

import java.util.ArrayList;

import in.conceptarchitect.banking.exceptions.InvalidAccountException;

public class ArraylistAccountStore {
	
	int accountCount=0;	
	int lastAccountNumber=0;
	ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();
	

	public int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		accounts.add(account);
		return accountNumber;
	}
	
	public BankAccount getAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts.get(accountNumber)==null)
			throw new InvalidAccountException(accountNumber);

		//it either returns a valid value or throws an exception
		return accounts.get(accountNumber);
	}

	public void removeAccount(int accountNumber) {
		accounts.set(accountNumber,null);
		accountCount--;
	}
	
	public ArrayList<BankAccount> getAllActiveAccounts() {
		
		ArrayList<BankAccount>  activeAccounts=new ArrayList<BankAccount>();
		for(BankAccount account : accounts) {
			if(account!=null) {
				activeAccounts.add(account);
			}
		}
		
		return activeAccounts;
	
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}
	
}
