package in.conceptarchitect.banking;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.Response;
import in.conceptarchitect.banking.ResponseStatus;
import in.conceptarchitect.utils.encryption.Encrypt;

public class BankAccount {
	
	
	private String name; //account holder name
	private int accountNumber;
	private static double interestRate;
	private double balance;
	private String password;
	private double minimumBalance;
	boolean isOverDrafting;
	double overDraftingFees;
	
	
	public BankAccount(int accountNumber, String name,String password, double amount) {
		this.accountNumber=accountNumber;
		this.name=name;
		//this.password=password;
		setPassword(password);
		balance=amount;  //this keyword is option here
	}
	
	public Response deposit(double amount) {
		if (amount<0) {
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		}
			double newBalance = this.getBalance() +amount;
			this.setBalance(newBalance);
			return new Response(ResponseStatus.SUCCESS,null);
		
	}
	
	
	
	public static void setInterestRate(double interestRate) {
		BankAccount.interestRate = interestRate;
	}

	public String getPassword() {
		return password;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Response withdraw(double amount) {
		if(amount<0)
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		if(amount>balance-minimumBalance)
			return new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		

		double newBalance = this.getBalance() - amount;
		this.setBalance(newBalance);
		return new Response(ResponseStatus.SUCCESS,null);
	}
	
	
	public void creditInterest() {
		//credits one month interest to the account
		balance+= (balance*interestRate/100);
	}
	
	
	public String getName() {return name;}	
	public void setName(String name) {this.name=name;}
	
	public int getAccountNumber() {return accountNumber;}

	// can't change the bank account
	//public void setAccountNumber(int accountNumber) {this.accountNumber=accountNumber; }
	
	
	public double getInterestRate() {
		return interestRate;
	}

	

	public double getBalance() {
		return balance;
	}

//  You can't change the balance directly
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}


//  You shouldn't allow get password
//  Perhaps get password may not make any sense	
//	public String getPassword() {
//		return password;
//	}
//
	private void setPassword(String password) {
		this.password =Encrypt.instance.encrypt( password,10);
		
	}
	
	public boolean authenticate(String password) {
		return Encrypt.instance.match(this.password, password, 10);
	}
	
	
	public boolean changePassword(String oldPassword, String newPassword) {
		if(this.authenticate(oldPassword)) {
			setPassword(newPassword);
			return true;
		} else {
			return false;
		}
	}
	
	
	

	public String info() {
		//System.out.println(accountNumber+"\t"+name+"\t"+balance);
		return String.format("%d\t%s\t%f\t%f\n",accountNumber,name,balance,interestRate);
	}
	
	

}
