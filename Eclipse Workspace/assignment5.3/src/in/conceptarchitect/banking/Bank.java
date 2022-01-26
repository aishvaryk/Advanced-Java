package in.conceptarchitect.banking;

public class Bank {

	
	private String name;
	private double interestRate; 
	

	public void setName(String name) {
		this.name = name;
	}

	public Bank(String name, double interestRate) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setInterestRate(interestRate);
	
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	
	int accountCount=0;	
	int lastAccountNumber=0;
	BankAccount [] accounts=new BankAccount[100];
	

	public BankAccount[] getAccounts() {
		return accounts;
	}

	public int openAccount(String name, String password, double amount, String accountType) {
		// TODO Auto-generated method stub
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		BankAccount account;
		if (accountType=="savings") {
			account=new SavingsAccount(accountNumber, name,password,amount);
		}
		else if (accountType=="current") {
			account=new CurrentAccount(accountNumber, name,password,amount);
		}
		else {
			account=new OverdraftAccount(accountNumber, name,password,amount);
		}
		accounts[accountNumber]=account;
		return accountNumber;
	}
	
	
	public double closeAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber,password);
		if(account==null)
			return -1;
		
		accounts[accountNumber]=null;
		accountCount--;
		return account.getBalance();
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}

	public BankAccount getAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		if(accountNumber<1 || accountNumber>lastAccountNumber )
				return null;
		
		var account=accounts[accountNumber];
		if(account==null)
			return null;
		
		if(!account.authenticate(password))
			return null;
		
		return account;
	}
	
	public double getAccountBalance(int accountNumber, String password) {
		// TODO Auto-generated method stub
		if (accountNumber<0 || accountNumber>lastAccountNumber) return -1;
		BankAccount account = accounts[accountNumber];
		if (account==null) return -1;
		if(!account.authenticate(password))
			return -1;
		return account.getBalance();
	}

	public Response deposit(int accountNumber, double amount) {
		Response invalidResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Account no is incorrect");
		if (accountNumber<0 || accountNumber>lastAccountNumber) return invalidResponse;
		BankAccount account = accounts[accountNumber];
		if (account==null) return invalidResponse;
		
		return account.deposit(amount);
	}
	
	public Response withdraw(int accountNumber, String password, double amount) {
		Response invalidResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Account no is incorrect");
		if (accountNumber<0 || accountNumber>lastAccountNumber) return invalidResponse;
		BankAccount account = accounts[accountNumber];
		if (account==null) return invalidResponse;
			
		if(!account.authenticate(password))
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		return account.withdraw(amount,password);
	}

	public Response transfer(int fromAccountNumber, String password, int toAccountNumber, double amount) {
		Response invalidResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Account no is incorrect");
		if (fromAccountNumber<0 || fromAccountNumber>lastAccountNumber
				|| toAccountNumber<0 || toAccountNumber>lastAccountNumber) return invalidResponse;
		BankAccount fromAccount = accounts[fromAccountNumber];
		BankAccount toAccount = accounts[fromAccountNumber];
		if (fromAccount==null || toAccount==null) return invalidResponse;
			
		if(!fromAccount.authenticate(password) || !toAccount.authenticate(password))
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		Response response1 = fromAccount.withdraw(amount,password);
		if (response1.getCode()!=ResponseStatus.SUCCESS) {
			return response1;
		}
		Response response2 = toAccount.deposit(amount);
		if (response2.getCode()!=ResponseStatus.SUCCESS) {
			return response2;
		}
		return new Response(ResponseStatus.SUCCESS,"");		
	}

	public void creditInterest() {
		for (BankAccount account:accounts) {
			if (account!=null) account.creditInterest(this.getInterestRate());
		}
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	

}















