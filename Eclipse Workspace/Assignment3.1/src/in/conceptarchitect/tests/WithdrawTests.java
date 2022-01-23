package in.conceptarchitect.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import in.conceptarchitect.banking.*;

public class WithdrawTests {

	BankAccount testAccount = new BankAccount(123,"Aishvary", "123", 1, 4.1);
	String correctPassword = "123";
	String wrongPassword= "1234";
	
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
		assertEquals(expectedResponse.getCode() , testAccount.withdraw(withdrawlAmount, wrongPassword).getCode());
		
	}
	
	@Test
	public void successfulWithdrawl() {
		int withdrawlAmount = 1;
		Response expectedResponse  = new Response(ResponseStatus.SUCCESS,null);
		assertEquals(expectedResponse.getCode() , testAccount.withdraw(withdrawlAmount, correctPassword).getCode());		
	}

}
