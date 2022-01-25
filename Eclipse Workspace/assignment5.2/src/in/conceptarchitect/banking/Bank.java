package in.conceptarchitect.banking;

public class Bank {
	
	private String name;
	public static double rate=12;

	
	public Bank(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public double getRate() {
		return rate;
	}



	int accountCount=0;	
	int accountSerial = 0;
	BankAccount [] accounts=new BankAccount[100];


	public int openAccount(String name, String password, double amount,String accountType) {
		// TODO Auto-generated method stub
		int accountNumber= ++accountSerial;
		accountCount++;
		
		
		var account=new BankAccount(accountNumber, name,password,amount);
		accounts[accountNumber]=account;
		return accountNumber;
	}
	
	
	public double closeAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		if(accountNumber<1 || accountNumber>accountCount)
			return -1;
		
		var account=accounts[accountNumber];
		if (account==null) {
			return -1;
		}
		accounts[accountNumber] = null;
		if(!account.authenticate(password))
			return -1;
		accountCount--;
		return account.getBalance();
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}

	public BankAccount getAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		if (accountNumber<0 || accountNumber>accountSerial) return null;
		BankAccount account = accounts[accountNumber];
		if (account==null) return null;
		if(!account.authenticate(password))
			return null;
		return account;
	}
	
	public double getAccountBalance(int accountNumber, String password) {
		// TODO Auto-generated method stub
		if (accountNumber<0 || accountNumber>accountSerial) return -1;
		BankAccount account = accounts[accountNumber];
		if (account==null) return -1;
		if(!account.authenticate(password))
			return -1;
		return account.getBalance();
	}

	public void creditInterest() {
		for (BankAccount account:accounts) {
			if (account!=null) account.creditInterest();
		}
	}
	
	public Response deposit(int accountNumber, double amount) {
		Response invalidResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Account no is incorrect");
		if (accountNumber<0 || accountNumber>accountSerial) return invalidResponse;
		BankAccount account = accounts[accountNumber];
		if (account==null) return invalidResponse;
		
		return account.deposit(amount);
	}
	
	public Response withdraw(int accountNumber, String password, double amount) {
		Response invalidResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Account no is incorrect");
		if (accountNumber<0 || accountNumber>accountSerial) return invalidResponse;
		BankAccount account = accounts[accountNumber];
		if (account==null) return invalidResponse;
			
		if(!account.authenticate(password))
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		return account.withdraw(amount);
	}

	public Response transfer(int fromAccountNumber, String password, int toAccountNumber, double amount) {
		Response invalidResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Account no is incorrect");
		if (fromAccountNumber<0 || fromAccountNumber>accountSerial 
				|| toAccountNumber<0 || toAccountNumber>accountSerial) return invalidResponse;
		BankAccount fromAccount = accounts[fromAccountNumber];
		BankAccount toAccount = accounts[fromAccountNumber];
		if (fromAccount==null || toAccount==null) return invalidResponse;
			
		if(!fromAccount.authenticate(password) || !toAccount.authenticate(password))
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		Response response1 = fromAccount.withdraw(amount);
		if (response1.getCode()!=ResponseStatus.SUCCESS) {
			return response1;
		}
		Response response2 = toAccount.deposit(amount);
		if (response2.getCode()!=ResponseStatus.SUCCESS) {
			return response2;
		}
		return new Response(ResponseStatus.SUCCESS,"");	
	}
}















