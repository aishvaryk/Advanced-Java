package in.conceptarchitect.utils.encryption;

import java.util.ArrayList;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.*;

public class Search {

	public static ArrayList<BankAccount> searchTypeCurrentAccount(ArrayList<BankAccount> accounts) {
		ArrayList<BankAccount> result = new ArrayList<BankAccount>();
		for (BankAccount account: accounts) {
			if (account instanceof CurrentAccount) {
				result.add(account);
			}
		}
		return result;
	}
	
	
	public static ArrayList<BankAccount> searchAccountWithNegativeBalance(ArrayList<BankAccount> accounts) {
		ArrayList<BankAccount> result = new ArrayList<BankAccount>();
		for (BankAccount account: accounts) {
			if (account.getBalance()<0) {
				result.add(account);
			}
		}
		return result;
	}
	
	public static ArrayList<BankAccount> searchAccountWithLastName(ArrayList<BankAccount> accounts, String lastName) {
		ArrayList<BankAccount> result = new ArrayList<BankAccount>();
		for (BankAccount account: accounts) {
			if (account.getName().substring(account.getName().indexOf(" ")+1)==lastName) {
				result.add(account);
			}
		}
		return result;
	}
}
