package in.conceptarchitect.accountTypes;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;

public class SavingsAccount extends BankAccount {

	private  double minimumBalance = 5000;
	private  double interestRate = Bank.rate;
	private  boolean OverDrafting = false;
	public SavingsAccount(int accountNumber, String name, String password, double amount, String accountType) {
		super(accountNumber, name, password, amount);
		// TODO Auto-generated constructor stub
	}
	public double getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public boolean isOverDrafting() {
		return OverDrafting;
	}
	public void setOverDrafting(boolean overDrafting) {
		OverDrafting = overDrafting;
	}


}
