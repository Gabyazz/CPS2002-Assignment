import static org.junit.Assert.*;

import org.junit.Test;


public class GoodTest {

	Calculator calculator = new Calculator();
	
	@Test
	public void testAdd() 
	{		
		assertEquals(5, calculator.add(3, 2));
	}
	
	@Test
	public void testSubtract() 
	{
		assertEquals(1, calculator.subtract(3, 2));
	}
	
	@Test
	public void testMultiply()
	{
		assertEquals(6, calculator.multiply(3, 2));
	}
	
	@Test
	public void testDivide1()
	{
		assertEquals(-999, calculator.divide(3, 0));
	}
	
	@Test
	public void testDivide2() 
	{
		assertEquals(5, calculator.divide(3, 1));
	}

}
