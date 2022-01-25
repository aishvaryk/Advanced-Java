package in.conceptarchitect.accountTypes;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.Response;
import in.conceptarchitect.banking.ResponseStatus;

public class OverDraftAccount extends BankAccount {

	private double minimumBalance = 0;
	private  double interestRate = Bank.rate;
	private boolean OverDrafting = true;
	private double overDraftingFees = 1;
	private double maxBalance;

	public OverDraftAccount(int accountNumber, String name, String password, double amount, String accountType) {
		super(accountNumber, name, password, amount);
		// TODO Auto-generated constructor stub
		this.maxBalance = this.getBalance();
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

	public double getOverDraftingFees() {
		return overDraftingFees;
	}

	public void setOverDraftingFees(double overDraftingFees) {
		this.overDraftingFees = overDraftingFees;
	}
	
	public Response deposit(double amount) {
		if (amount<0) {
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		}
			double newBalance = this.getBalance() +amount;
			this.setBalance(newBalance);
			if (newBalance>this.maxBalance) {
				this.maxBalance=newBalance;
				this.minimumBalance = this.maxBalance*(0.1);
			}
			
			return new Response(ResponseStatus.SUCCESS,null);
	}
	

	public Response withdraw(double amount) {
		if(amount<0)
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		if(amount>this.getBalance()-minimumBalance)
			return new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		double newBalance = this.getBalance() - amount;
		this.setBalance(newBalance);
		return new Response(ResponseStatus.SUCCESS,null);
		
	}
	

}
