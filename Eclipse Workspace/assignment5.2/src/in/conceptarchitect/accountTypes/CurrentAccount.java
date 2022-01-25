package in.conceptarchitect.accountTypes;

import in.conceptarchitect.banking.BankAccount;

public class CurrentAccount extends BankAccount {

	private double minimumBalance = 0;
	private double interestRate = 0;
	private boolean OverDrafting = false;

	public CurrentAccount(int accountNumber, String name, String password, double amount, String accountType) {
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
