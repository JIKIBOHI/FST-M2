package Activities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Activity2 {

	@Test
	public void notEnoughFunds()
	{
		BankAccount acc1 = new BankAccount(9);
		assertThrows(NotEnoughFundsException.class,() -> acc1.withdraw(10), "Withdrawl must be less than balance");
	}
	
	@Test
	public void enoughFunds()
	{
		BankAccount acc1 = new BankAccount(100);
		assertDoesNotThrow(() -> acc1.withdraw(100));
	}
}
