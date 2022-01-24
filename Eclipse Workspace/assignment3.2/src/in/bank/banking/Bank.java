package in.bank.banking;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Bank {
	public static double interestRate = 4.0;
	public static int lastAccountNumber = 10000000;
	public static double initialBalance = 100;
	private static List<BankAccount> openAccounts = new ArrayList<BankAccount>(); 
	private static List<BankAccount> closedAccounts = new ArrayList<BankAccount>(); 
	
	public static Response creditInterest(BankAccount acc) {
		acc.creditInterest(interestRate);
		return new Response(ResponseStatus.CREDIT_INTEREST_SUCCESS, "Interest Credited Successfullly");
	}
	
	public static List<BankAccount> getOpenAccounts() {
		return openAccounts;
	}

	public static void setOpenAccounts(List<BankAccount> openAccounts) {
		Bank.openAccounts = openAccounts;
	}

	public static List<BankAccount> getClosedAccounts() {
		return closedAccounts;
	}

	public static void setClosedAccounts(List<BankAccount> closedAccounts) {
		Bank.closedAccounts = closedAccounts;
	}

	public static BankAccount openBankAccount(String firstName, String lastName, GregorianCalendar dateOfBirth, String password ) {
		Bank.lastAccountNumber +=1;
		BankAccount newAccount =  new BankAccount(Bank.lastAccountNumber,firstName,lastName, dateOfBirth, password, Bank.initialBalance);
		Bank.openAccounts.add(newAccount);
		return newAccount;
	}
	
	public static Response closeBankAccont(BankAccount account) {
   
        if (Bank.openAccounts.contains(account)) {
    		Bank.closedAccounts.add(account);
    		Bank.openAccounts.remove(account);
            return new Response(ResponseStatus.ACCOUNT_CLOSED_SUCCESS, "Account Closed Successfullly");
        }
        return new Response(ResponseStatus.ACCOUNT_NOT_EXIST, "Requested Account is not opened");
	}
	
	
	
	public static Response transfer(BankAccount fromAccount, int toAccountNo, double amount) {
		
		boolean isToAccountExists = false;
		for (BankAccount account : Bank.openAccounts) {
	        if (account.getAccountNumber()==toAccountNo) {
	            isToAccountExists = true;
	            break;
	        }
	    }
		if (!isToAccountExists) {
			return new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Target Accoun No does not exist");
		}
		return new Response(ResponseStatus.SUCCESS,null);
	}
	
	
}
