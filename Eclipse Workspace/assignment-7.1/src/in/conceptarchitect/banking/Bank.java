package in.conceptarchitect.banking;


import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidAccountTypeException;

public class Bank {

	
	private String name;
	private double rate;
	ArrayAccountStore store;
	

	public Bank(String name, double rate) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.rate=rate;
		store=new ArrayAccountStore();
	}

	public String getName() { 
		// TODO Auto-generated method stub
		return name;
	}

	public double getRate() {
		// TODO Auto-generated method stub
		return rate;
	}
	
	
	

	public int openAccount(String accountType,String name, String password,  double amount) {
		// TODO Auto-generated method stub
		
		BankAccount account=null;
		if(accountType.equals("savings"))
				account= new SavingsAccount(0, name,password,amount);
		else if(accountType.equals("current"))
				account=new CurrentAccount(0,name,password,amount);
		else if(accountType.equals("overdraft"))
				account=new OverdraftAccount(0, name, password, amount);
		
		if(account==null)
				throw new InvalidAccountTypeException(0,"Invalid Account Type :"+accountType);
		
		return store.addAccount(account);
	}

		
	public BankAccount getAccount(int accountNumber, String password) {
		var account=store.getAccount(accountNumber);
		account.authenticate(password);
		return account;
	}
	
	
	public double closeAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber,password);
		
		if(account.getBalance()<0)
			throw new InsufficientBalanceException(accountNumber,-account.getBalance());
		
		store.removeAccount(accountNumber);
		return account.getBalance();
	}

	

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return store.getAccountCount();
	}

	

	public void deposit(int accountNumber, double amount) {
		// TODO Auto-generated method stub
		var account=store.getAccount(accountNumber);		
		account.deposit(amount);
		
	}

	public void withdraw(int accountNumber, double amount, String password) {
		// TODO Auto-generated method stub
		var account=store.getAccount(accountNumber);
		account.withdraw(amount, password);
		
	}

	public void transfer(int accountNumber, double amount, String password, int targetAccount) {
		// TODO Auto-generated method stub
		var source= store.getAccount(accountNumber);
		var target=store.getAccount(targetAccount);
			
		source.withdraw(amount, password);
		target.deposit(amount);
	}

	public void creditInterest() {
		// TODO Auto-generated method stub
		for(var account :store. getAllActiveAdccounts()) {
				account.creditInterest(rate);
		}
	}

	public double getBalance(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=store.getAccount(accountNumber);
		account.authenticate(password);
		return account.getBalance();
		
	}

	
	public void changePassword(int accountNumber, String currentPassword, String newPassword) {
		var account=store.getAccount(accountNumber);
		account.changePassword(currentPassword, newPassword);
		
	}

	public String[] getAllAccountsInfo() {
		// TODO Auto-generated method stub
		String [] info= new String [ store.getAccountCount()];
		var x=0;
		
		for(var account : store.getAllActiveAdccounts()) {
			info[x]=account.toString();
			x++;		
		}
		
		return info;
		
	}
	 

}















