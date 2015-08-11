package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Validator;
public class ValidatorTest
{

	@Test
	public void testValidateIntWithCharacter()
	{
		assertFalse(Validator.validateInt("a"));
	}
	
	@Test
	public void testValidateIntWithDouble()
	{
		assertFalse(Validator.validateInt("1.5"));
	}
	
	@Test
	public void testValidateIntWithInt()
	{
		assertTrue(Validator.validateInt("123444"));
	}

	@Test
	public void testValidateIntWithRange()
	{
		assertFalse(Validator.validateDoubleWithRange("200", 1, 100));
	}
	
	@Test
	public void testValidateIntWithRange2()
	{
		assertTrue(Validator.validateIntWithRange("200", 0, 1000));
	}
	
	@Test
	public void testValidateIntWithRange3()
	{
		assertFalse(Validator.validateIntWithRange("ssadasd", 1, 200));
	}

	@Test
	public void testValidateDouble()
	{
		assertFalse(Validator.validateDouble("s"));
	}
	
	@Test
	public void testValidateDouble2()
	{
		assertTrue(Validator.validateDouble("1"));
	}
	
	@Test
	public void testValidateDouble3()
	{
		assertTrue(Validator.validateDouble("40.002"));
	}
	
	@Test
	public void testValidateDouble4()
	{
		assertTrue(Validator.validateDouble("0.002"));
	}
	
	@Test
	public void testValidateDouble5()
	{
		assertFalse(Validator.validateDouble("0.a02"));
	}
	
	@Test
	public void testValidateDoubleWithRange()
	{
		assertFalse(Validator.validateDoubleWithRange("asdsa", 0, 1000000));
	}
	
	@Test
	public void testValidateDoubleWithRange2()
	{
		assertTrue(Validator.validateDoubleWithRange("999999", 0, 1000000));
	}

	@Test
	public void testValidateDateWithFormat()
	{
		assertTrue(Validator.validateDateWithFormat("02/08/2015"));
	}
	
	@Test
	public void testValidateDateWithFormat2()
	{
		assertTrue(Validator.validateDateWithFormat("2/8/2015"));
	}
	
	@Test
	public void testValidateDateWithFormat3()
	{
		assertFalse(Validator.validateDateWithFormat("13/08/2015"));
	}
	
	@Test
	public void testValidateDateWithFormat4()
	{
		assertFalse(Validator.validateDateWithFormat("02/32/2015"));
	}

	@Test
	public void testValidateDateWithFormat5()
	{
		assertFalse(Validator.validateDateWithFormat("a2/32/2015"));
	}
}
