package in.aishvary.assignmrn2;

import in.bankAccount.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount bankAcc01 = new BankAccount("Aishvary Khandelwal", 101, 10, "abcd");
		
		bankAcc01.deposit(1000);
		System.out.println("Balance after deposit "+ bankAcc01.getBalance());
		
		bankAcc01.withdraw(11, "abc");
		System.out.println("Balance after withdrawl with wrong password " + bankAcc01.getBalance());

		bankAcc01.withdraw(11, "abcd");
		System.out.println("Balance after withdrawl with correct password " + bankAcc01.getBalance());
		
		
	}

}
