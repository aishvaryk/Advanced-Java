package in.bank.banking;

import java.util.GregorianCalendar;

public class BankAccount {
	
	private int accountNumber;
	private double balance;
	private Customer owner;
	
	BankAccount(int accountNumber, String firstName, String lastName, GregorianCalendar dateOfBirth, String password,double initialBalance) {
		this.setAccountNumber(accountNumber);
		this.setBalance(0);
		this.setOwner(new Customer(firstName, lastName, dateOfBirth, password));
		this.setBalance(initialBalance);
	}
	
	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public Response deposit(double amount) {
		if (amount<0) {
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		}
		if(!Bank.getOpenAccounts().contains(this)) {
			return new Response(ResponseStatus.ACCOUNT_NOT_EXIST, "This account is not opened");
		}
			double newBalance = this.getBalance() +amount;
			this.setBalance(newBalance);
			return new Response(ResponseStatus.SUCCESS,null);
		
	}
	
	public Response withdraw(int amount,String password) {
		if(!this.authenticate(password))
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		if(amount<0)
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		if(amount>balance)
			return new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		if(!Bank.getOpenAccounts().contains(this)) {
			return new Response(ResponseStatus.ACCOUNT_NOT_EXIST, "This account is not opened");
		}
		double newBalance = this.getBalance() - amount;
		this.setBalance(newBalance);
		return new Response(ResponseStatus.SUCCESS,null);
	}
	
	public Response transfer(int accountNumber, double amount,String password) {

		if(!this.authenticate(password))
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		if(amount<0)
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		if(amount>balance)
			return new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		
		return Bank.transfer(this, accountNumber, amount);
	}
	
	public boolean authenticate(String password) {
		return this.owner.getPassword()==password;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void creditInterest(double interestRate) {
		this.setBalance(balance+=(balance*interestRate));
	}
	
	
}
