package in.conceptarchitect.banking;

public class BankAccountTypes {

	static class SavingsAccount{
		private static double minimumBalance = 5000;
		private static double interestRate = 12;
		private static boolean OverDrafting = false;
		public static double getMinimumBalance() {
			return minimumBalance;
		}
		public static void setMinimumBalance(double minimumBalance) {
			SavingsAccount.minimumBalance = minimumBalance;
		}
		public static double getInterestRate() {
			return interestRate;
		}
		public static void setInterestRate(double interestRate) {
			SavingsAccount.interestRate = interestRate;
		}
		public static boolean isOverDrafting() {
			return OverDrafting;
		}
		public static void setOverDrafting(boolean overDrafting) {
			OverDrafting = overDrafting;
		}
	}
	static class CurrentAccount{
		private static double minimumBalance = 0;
		private static double interestRate = 0;
		private static boolean OverDrafting = false;
		
		public static double getMinimumBalance() {
			return minimumBalance;
		}
		public static void setMinimumBalance(double minimumBalance) {
			SavingsAccount.minimumBalance = minimumBalance;
		}
		public static double getInterestRate() {
			return interestRate;
		}
		public static void setInterestRate(double interestRate) {
			SavingsAccount.interestRate = interestRate;
		}
		public static boolean isOverDrafting() {
			return OverDrafting;
		}
		public static void setOverDrafting(boolean overDrafting) {
			OverDrafting = overDrafting;
		}
	}
	
	static class ODAccount{
		private static double minimumBalance = 0;
		private static double interestRate = 12;
		private static boolean OverDrafting = true;
		private static double overDraftingFees = 1;
		
		
		public static double getOverDraftingFees() {
			return overDraftingFees;
		}
		public static void setOverDraftingFees(double overDraftingFees) {
			ODAccount.overDraftingFees = overDraftingFees;
		}
		public static double getMinimumBalance() {
			return minimumBalance;
		}
		public static void setMinimumBalance(double minimumBalance) {
			SavingsAccount.minimumBalance = minimumBalance;
		}
		public static double getInterestRate() {
			return interestRate;
		}
		public static void setInterestRate(double interestRate) {
			SavingsAccount.interestRate = interestRate;
		}
		public static boolean isOverDrafting() {
			return OverDrafting;
		}
		public static void setOverDrafting(boolean overDrafting) {
			OverDrafting = overDrafting;
		}
	}
	
}
