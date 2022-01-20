package in.bankAccount;

public class BankAccount {

	private String name;
	private int accNo;
	private float interestRate;
	private String password;
	private double balance;
	
	public BankAccount(String name,int accNo, float interestRate, String password) {
		this.setName(name);
		this.setAccNo(accNo);
		this.setInterestRate(interestRate);
		this.setBalance(1);
		this.setPassword(password);
	}
	
	
	public void deposit(float money) {
		if (this.getBalance()>0) this.setBalance(this.getBalance()+money);
	}
	
	public void withdraw(float money, String passsword) {
		if (this.balance>0 && password==this.getPassword() && money<=this.getBalance()) this.setBalance(this.getBalance()-money);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
