package in.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import in.bank.*;

import org.junit.jupiter.api.Test;

class BankTests {

	String correctPassword = "abcd";
	BankAccount testAccount = Bank.openBankAccount("Aishvary","Khandelwal", new GregorianCalendar(1999, 10, 1),correctPassword);
	BankAccount testAccount2 = Bank.openBankAccount("Aish","Gupta", new GregorianCalendar(1999, 10, 1),correctPassword);
	BankAccount testAccount3 = Bank.openBankAccount("Aish","Khandelwal", new GregorianCalendar(1999, 10, 1),correctPassword);
	
	@Test
	public void openAccountSuccessful() {
		BankAccount testAccount4 = Bank.openBankAccount("Aish","Sharma", new GregorianCalendar(1999, 10, 1),correctPassword);
		assertTrue(Bank.getOpenAccounts().contains(testAccount4));
	}
	
	@Test
	public void closeAccountSuccessful() {
		BankAccount testAccount4 = Bank.openBankAccount("Aish","Sharma", new GregorianCalendar(1999, 10, 1),correctPassword);
		Bank.closeBankAccont(testAccount4);
		assertTrue(Bank.getClosedAccounts().contains(testAccount4));
	}
	
	
	@Test
	public void negativeAmountWithdrawl() {
		int withdrawlAmount = -1;
		Response expectedResponse  = new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		assertEquals(expectedResponse.getCode() , testAccount.withdraw(withdrawlAmount, correctPassword).getCode());
	}
	
	@Test
	public void wrongPasswordWithdrawl() {
		int withdrawlAmount = 1;
		Response expectedResponse  = new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		assertEquals(expectedResponse.getCode() , testAccount.withdraw(withdrawlAmount, "1234").getCode());
	}
	
	@Test
	public void insufficientBalanceWithdrawl() {
		int withdrawlAmount = 500;
		Response expectedResponse  = new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		assertEquals(expectedResponse.getCode() , testAccount.withdraw(withdrawlAmount, correctPassword).getCode());		
	}
	
	@Test
	public void closedAccountWithdrawl() {
		int withdrawlAmount = 100;
		BankAccount testAccount4 = Bank.openBankAccount("Aish","Sharma", new GregorianCalendar(1999, 10, 1),correctPassword);
		Bank.closeBankAccont(testAccount4);
		Response expectedResponse  = new Response(ResponseStatus.ACCOUNT_NOT_EXIST, "Account is Closed");
		assertEquals(expectedResponse.getCode() , testAccount4.withdraw(withdrawlAmount, correctPassword).getCode());
	}
	
	@Test
	public void successfulWithdrawl() {
		int withdrawlAmount = 1;
		Response expectedResponse  = new Response(ResponseStatus.SUCCESS,null);
		assertEquals(expectedResponse.getCode() , testAccount.withdraw(withdrawlAmount, correctPassword).getCode());		
	}
	
	@Test
	public void invalidAmountDeposit() {
		int depositAmount = -1;
		Response expectedResponse  = new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		assertEquals(expectedResponse.getCode() , testAccount.deposit(depositAmount).getCode());
	}
	
	@Test
	public void successfulDeposit() {
		int depositAmount = 100;
		Response expectedResponse  = new Response(ResponseStatus.SUCCESS,null);
		assertEquals(expectedResponse.getCode() , testAccount.deposit(depositAmount).getCode());
	}
	
	@Test
	public void closedAccountDeposit() {
		int depositAmount = 100;
		BankAccount testAccount4 = Bank.openBankAccount("Aish","Sharma", new GregorianCalendar(1999, 10, 1),correctPassword);
		Bank.closeBankAccont(testAccount4);
		Response expectedResponse  = new Response(ResponseStatus.ACCOUNT_NOT_EXIST, "Account is Closed");
		assertEquals(expectedResponse.getCode() , testAccount4.deposit(depositAmount).getCode());
	}
	
	@Test
	public void incorrectAccountNoTransfer() {
		int transferAmount = 1;

		Response expectedResponse  = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO, "Target Accoun No does not exist");

		assertEquals(expectedResponse.getCode() , testAccount.transfer(1111,transferAmount,correctPassword).getCode());
	}
	
	@Test
	public void wrongPasswordTransfer() {
		int transferAmount = 1;
		Response expectedResponse  = new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		assertEquals(expectedResponse.getCode() , testAccount.transfer(testAccount2.getAccountNumber(), transferAmount, "acdd").getCode());
	}
	
	@Test
	public void insufficientBalanceTransfer() {
		int transferAmount = 10000;
		Response expectedResponse  = new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		assertEquals(expectedResponse.getCode() , testAccount.transfer(testAccount2.getAccountNumber(), transferAmount, correctPassword).getCode());		
	}
	
	@Test
	public void successfulTransfer() {
		int transferAmount = 1;
		Response expectedResponse  = new Response(ResponseStatus.SUCCESS,null);
		assertEquals(expectedResponse.getCode() , testAccount.transfer(testAccount2.getAccountNumber(),transferAmount,correctPassword).getCode());
	}
	
	@Test
	public void interestCreditSuccessful() {
		Response expectedResponse = new Response(ResponseStatus.CREDIT_INTEREST_SUCCESS, "Interest Credited Successfullly");
		assertEquals(expectedResponse.getCode() , Bank.creditInterest(testAccount).getCode());
	}
	
}
