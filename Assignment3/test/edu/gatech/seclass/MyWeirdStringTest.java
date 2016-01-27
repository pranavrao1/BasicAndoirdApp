package edu.gatech.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyWeirdStringTest {

	private MyWeirdStringInterface myweirdstring;

	@Before
	public void setUp() throws Exception {
		myweirdstring = new MyWeirdString();
	}

	@After
	public void tearDown() throws Exception {
		myweirdstring = null;
	}

	@Test
	public void testCountDigits1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals(5, myweirdstring.countDigits());
	}

	@Test
	public void testCountDigits2() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountDigits3() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountDigits4() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEvenCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("' etrptsm 1isi hs5rn,rgt", myweirdstring.getEvenCharacters());
	}

	@Test
	public void testGetEvenCharacters2() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEvenCharacters3() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEvenCharacters4() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOddCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("Idbte u 0edgt nti t19 ih?", myweirdstring.getOddCharacters());
	}

	@Test
	public void testGetOddCharacters2() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOddCharacters3() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOddCharacters4() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(40, 45);
		assertEquals("I'd better put s0me d1gits in this 5tr1nIX, right?", myweirdstring.getWeirdString());
	}

	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring2() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring3() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring4() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring5() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring6() {
		fail("Not yet implemented");
	}

}
