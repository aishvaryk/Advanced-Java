package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.Response;
import in.conceptarchitect.banking.ResponseStatus;

public class BankSpecs {
	
	final String bankName="ICICI";
	final double rate=12;
	final String correctPassword="p@ss";
	final double initialBalance=50000;
	String savingsAccount = "savings";
	String currentAccount = "current";
	String overdraftAccount = "overdraft";
	int existingAccount1, existingAccount2;
	int initialTotalAccounts;
	Bank bank;
	
	@Before
	public void arrange() {
		//ARRANGE
		bank=new Bank(bankName,rate);
		existingAccount1=bank.openAccount("Name1", correctPassword, initialBalance, savingsAccount);
		existingAccount2=bank.openAccount("Name", correctPassword, initialBalance, savingsAccount);
		initialTotalAccounts=bank.getAccountCount();
	}
	
	
	@Test
	public void bankShouldHaveAName() {
				
		//ACT
		Bank bank=new Bank(bankName, rate);
		
		//ASSERT
		assertEquals(bankName, bank.getName());
		
	}
	

	@Test
	public void bankShouldHaveAInterestRAte() {
		
		//ACT
		Bank bank=new Bank("Some Name", rate);
		
		//ASSERT
		assertEquals(rate, bank.getInterestRate(),0);
				
		
	}
	
	
	@Test
	public void openAccountShouldTakeNamePasswordAndBalanceAndReturnAccountNumber() {
		
		//ACT
		int accountNumber1 = bank.openAccount("Aman", "mypassword", 1000.0, savingsAccount);
		
		// ASSERT
		assertTrue(accountNumber1 > 0);
	}
	
	
	@Test		
	public void openAccountShouldReturnUniqueAccountNumber() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 , savingsAccount);
		var accountNumber2 = bank.openAccount("arpit", "hispassword", 2000.0 ,savingsAccount);
		
		// ASSERT
		assertNotEquals(accountNumber1, accountNumber2);
	}
	
	
	@Test
	public void openAccountShouldIncreaseTotalAccountCountInTheBank() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0,savingsAccount );
		
		
		// ASSERT
		assertEquals(initialTotalAccounts+1, bank.getAccountCount());
	}
	
	
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountNumber() {
		
		//ACT
		var result = bank.closeAccount(initialTotalAccounts+1, "any-password");
		
		assertEquals(-1, result,0);
		
		
	}
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountPassword() {
		//ACT
		var result = bank.closeAccount(existingAccount1, "wrong-password");
		
		assertEquals(-1, result,0);
		
	}
	
	
	
	@Test
	public void closeAccountShouldCloseTheAccountWithRightCredentials() {
		//ACT
		var result = bank.closeAccount(existingAccount1, correctPassword);
		
		//ASSERT
		assertNotEquals(-1, result,0);
	}
	
	
	@Ignore
	@Test
	public void closeAccountShouldReturnBalanceOnSuccessfulClosure() {
		
		//ACT
		var result= bank.closeAccount(existingAccount1, correctPassword);
		//ASSERT
		assertEquals(initialBalance, result,0.01);
		
		
	}
	
	@Test
	public void closeAccountShouldReduceTheAccountCountInTheBank() {
		//ACT
		var result= bank.closeAccount(existingAccount1, correctPassword);
		
		//ASSERT
		assertEquals(initialTotalAccounts-1, bank.getAccountCount());
	}
	
	@Test
	public void closeShouldFailForAlreadyClosedAccount() {
		
		//ARRANGE
		bank.closeAccount(existingAccount1, correctPassword);
		//Now existingAccount1 is closed. It can't be closed again
		
		//ACT
		var result=bank.closeAccount(existingAccount1, correctPassword);		
		
		//ASSERT
		assertEquals(-1, result,0);
		
	}
	
	@Test
	public void accountNumberShouldNotBeReused() {
		//ARRANGE
		String a4Name="Account 4";
		String a5Name="Account 5";
		bank.openAccount("Name", correctPassword, initialBalance,currentAccount); //3
		bank.openAccount(a4Name, correctPassword, initialBalance, currentAccount); //4
		
		bank.closeAccount(3, correctPassword); //we closed account 3
		
		//ACT
		
		var accountNumber= bank.openAccount(a5Name, correctPassword, initialBalance,savingsAccount);
		
		
		//ASSERT
		assertEquals(5,accountNumber);
		
		var account4= bank.getAccount(4,correctPassword);
		
		assertEquals(a4Name, account4.getName());
		
		
		
	}
	
	
	@Test
	public void weShouldNotBeAbleToGetClosedAccount() {
		var accountNumber = bank.openAccount("Name", correctPassword, initialBalance,savingsAccount);
		bank.closeAccount(accountNumber, correctPassword);
		assertEquals(bank.getAccount(accountNumber, correctPassword),null);
	}
	
	@Test
	public void creditInterstShouldCreditInterstToAllAccounts() {
		var accNo1 = bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var account1 = bank.getAccount(accNo1, correctPassword);
		var accNo2 = bank.openAccount("B", correctPassword, initialBalance,currentAccount); 
		var account2 = bank.getAccount(accNo2, correctPassword);
		var accNo3 = bank.openAccount("C", correctPassword, initialBalance,overdraftAccount); 
		var account3 = bank.getAccount(accNo3, correctPassword);
		
		bank.creditInterest();
		
		assertEquals(initialBalance*(bank.getInterestRate()/1200)+initialBalance, account1.getBalance(),1);
		assertEquals( initialBalance*(0/100)+initialBalance,account2.getBalance(),1);
		assertEquals(initialBalance*(bank.getInterestRate()/1200)+initialBalance, account3.getBalance(),1);
	}
	
	@Test
	public void getBalanceShouldReturnBalanceForCorrectAccountAndPassword() {
		var accNo1 = bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var balance = bank.getAccountBalance(accNo1, correctPassword);	
		assertEquals(balance, initialBalance, 1);
	}
	
	@Test
	public void getBalanceShouldFailForInvalidAccountNumber() {
		bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var balance = bank.getAccountBalance(909090, correctPassword);	
		assertEquals(balance,-1,1);
	}
	
	@Test
	public void getBalanceShouldFailForInvalidPassword() {
		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var balance = bank.getAccountBalance(accNo, "dsdaada");	
		assertEquals(balance,-1,1);
	}
	
	@Test
	public void depositShouldFailForInvalidAccountNumber() {
		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO,"").getCode();
		
		assertEquals(expectedResponse, bank.deposit(accNo+599, 100).getCode());
	}
	
	@Test
	public void depositShouldFailForInvalidAmount() {
		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INVALID_AMOUNT,"").getCode();
		
		assertEquals(expectedResponse, bank.deposit(accNo, -1).getCode());
		
	}
	
	@Test
	public void depositShouldCreditBalanceOnSuccess() {

		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.SUCCESS,"").getCode();
		
		assertEquals(expectedResponse, bank.deposit(accNo, 1).getCode());
		
	}
	
	@Test
	public void withdrawShouldFailForInvalidAccountNumber() {
		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO,"").getCode();
		
		assertEquals(expectedResponse, bank.withdraw(accNo+599,correctPassword, 1).getCode());
		
	}
	@Test
	public void withdrawShouldFailForInvalidPassword() {

		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INVALID_CREDENTIALS,"").getCode();
		
		assertEquals(expectedResponse, bank.withdraw(accNo,correctPassword+"a", 1).getCode());
		
	}
	@Test
	public void withdrawShouldFailForInvalidAmount() {

		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INVALID_AMOUNT,"").getCode();
		
		assertEquals(expectedResponse, bank.withdraw(accNo,correctPassword, -1).getCode());
		
	}
	
	@Test
	public void withdrawShouldFailForOverDraft() {
		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INSUFFICIENT_FUNDS,"").getCode();
		
		assertEquals(expectedResponse, bank.withdraw(accNo,correctPassword, initialBalance+100).getCode());
	}
	
	@Test
	public void withdrawShouldReduceBalanceByAmountOnSuccess() {
		var accNo= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.SUCCESS,"").getCode();
		var resultResponse = bank.withdraw(accNo,correctPassword, 1).getCode();
		assertEquals(expectedResponse, resultResponse);
		assertEquals(initialBalance-1, bank.getAccountBalance(accNo, correctPassword),0.1);
	}
	
	@Test
	public void transferShouldFailForInvalidSourceAccountNumber() {
		var accNo1= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var accNo2= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO,"").getCode();
		assertEquals(expectedResponse, bank.transfer(accNo1+500, correctPassword,accNo2 , 1).getCode());
	}
	
	@Test
	public void transferShouldFailForInvalidPassword() {
		var accNo1= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var accNo2= bank.openAccount("A", correctPassword, initialBalance,currentAccount); 
		var expectedResponse = new Response(ResponseStatus.INVALID_CREDENTIALS,"").getCode();
		assertEquals(expectedResponse, bank.transfer(accNo1, correctPassword+"a",accNo2 , 1).getCode());
	}
	
	@Test
	public void transferShouldFailForInvalidAmount() {

		var accNo1= bank.openAccount("A", correctPassword, initialBalance,currentAccount); 
		var accNo2= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INVALID_AMOUNT,"").getCode();
		assertEquals(expectedResponse, bank.transfer(accNo1, correctPassword,accNo2 , -1).getCode());
		
	}
	
	@Test
	public void transferShouldFailForOverDraft() {

		var accNo1= bank.openAccount("A", correctPassword, initialBalance,currentAccount); 
		var accNo2= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INSUFFICIENT_FUNDS,"").getCode();
		assertEquals(expectedResponse, bank.transfer(accNo1, correctPassword,accNo2 , 1000000).getCode());
	}
	
	@Test
	public void transferShouldReduceBalanceInSourceAccountOnSuccess() {
		var accNo1= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var accNo2= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		bank.transfer(accNo1, correctPassword,accNo2 , 1).getCode();
		assertEquals(initialBalance-1, bank.getAccountBalance(accNo1, correctPassword),1);
		
	}
	@Test
	public void transferShouldFailForInvalidTargetAccountNumber() {
		var accNo1= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var accNo2= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var expectedResponse = new Response(ResponseStatus.INCORRECT_ACCOUNT_NO,"").getCode();
		assertEquals(expectedResponse, bank.transfer(accNo1, correctPassword,accNo2+500 , 1).getCode());
		
	}
	
	@Test
	public void transferShouldAddBalanceInTargetOnSuccess() {
		var accNo1= bank.openAccount("A", correctPassword, initialBalance,savingsAccount); 
		var accNo2= bank.openAccount("A", correctPassword, initialBalance,currentAccount); 
		bank.transfer(accNo1, correctPassword,accNo2 , 1).getCode();
		assertEquals(initialBalance+1, bank.getAccountBalance(accNo2, correctPassword),1);
		
	}
	
	
	
	
	
	
	
	
	
	

}
