package com.kikolski;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 
 * @author Damian
 * Najwygodniej odpalic poprzez plugin do eclipse'a: "TestNG for eclipse"
 */
public final class AutomatonUnitTest {
	private Automaton automaton;
	
	@DataProvider
	private Object[][] correctData() {
		return new Object[][] {
				{"1+2"},
				{"1+2/4"},
				{"1+2-3*4/2"},
				{"1+2*(1+4)"},
				{"(2+4)*(1+4)"},
				{"(1+1)(2-1)"},
				{"(11232+112)(2221-1441)+1112"},
				{"4(11232+112)*(2221/1441)/1112"},
				{"987654-9876+123/123112*4343(123-12)"},
		};	
	}
	
	@DataProvider
	private Object[][] incorrectData() {
		return new Object[][] {
				{"1+"},
				{"1+2/"},
				{"*23"},
				{")12("},
				{"5*()"},
		};	
	}	
	@BeforeMethod
	private void setUp() {
		automaton = new Automaton();
	}
	
	@Test(dataProvider="correctData")
	public void shouldReturnTrueForCorrectExpression(String expression) {
		boolean endState = automaton.operate(expression);
		Assert.assertEquals(true, endState);
	}
	
	@Test(dataProvider="incorrectData")
	public void shouldReturnFalseForIncorrectExpression(String expression) {
		boolean endState = automaton.operate(expression);
		Assert.assertEquals(false, endState);
	}
}
