package in.conceptarchitect.banking;

import in.conceptarchitect.exceptions.*;
import in.conceptarchitect.utils.encryption.Encrypt;

public abstract class BankAccount {
	
	
	protected abstract double getMaxWithdrawableAmount();
	
	private String name; //account holder name
	private int accountNumber;
	
	protected double balance;
	private String password;
	
	
	
	public BankAccount(int accountNumber, String name,String password, double amount) {
		if (amount<0) {
			throw new InvalidAmountException("Enter Valid Amount");
		}
		this.accountNumber=accountNumber;
		this.name=name;
		//this.password=password;
		setPassword(password);
		balance=amount;  //this keyword is option here
		
				
	}
	
	public boolean deposit(double deposit) {
		
		if(deposit<0) {
			
		throw new InvalidAmountException("Invalid Amount Entered");	
			
		}
			balance+=deposit;
			//System.out.println("Amount is deposited");
			return true;
				
	}
	
	
	
	public Response withdraw(double amount,String password) {
		
		this.authenticate(password);
		if(amount<0)
			throw new InvalidAmountException("Invalid Amount Entered");	
		if(amount>getMaxWithdrawableAmount())
			throw new InsufficientFundsException("Insufficent Funds");
		
		balance-=amount;
		return new Response(ResponseStatus.SUCCESS,null);
	}
	
		
	

	public void creditInterest(double interestRate) {
		//credits one month interest to the account
		balance+= (balance*interestRate/1200);
	}
	
	
	public String getName() {return name;}	
	public void setName(String name) {this.name=name;}
	
	public int getAccountNumber() {return accountNumber;}

	// can't change the bank account
	//public void setAccountNumber(accountNumber) {this.accountNumber=accountNumber; }
	
	

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
	
	public void authenticate(String password) {
		
		if(!Encrypt.instance.match(this.password, password, 10)) {
			throw new InvalidCredentialsException("Invalid Credentials");
		};
	}
	
	
	public boolean changePassword(String oldPassword, String newPassword) {
		this.authenticate(oldPassword);
			setPassword(newPassword);
			return true;
	}
	
	
	

	public String info() {
		//System.out.println(accountNumber+"\t"+name+"\t"+balance);
		return String.format("%d\t%s\t%f\n",accountNumber,name,balance);
	}

	//package scope
	//Bank can change it. But client can't
	void setAccountNumber(int accountNumber) {
		// TODO Auto-generated method stub
		this.accountNumber=accountNumber;
		
	}
	
	

}
