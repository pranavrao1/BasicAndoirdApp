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

    /**
     * Test: This test to check the functionality of counting digits.
     * Test Type: State Test
     */
	@Test
	public void testCountDigits1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals(5, myweirdstring.countDigits());
	}

    /**
     * Test: This test to check the functionality of counting digits with special characters.
     * Test Type: State Test
     */
	@Test
	public void testCountDigits2() {
        myweirdstring.setWeirdString("\tEx@mpl# T#$t \n *#$$@g#");
        assertEquals(0, myweirdstring.countDigits());
	}

    /**
     * Test: This is test to check multiple functionality of MyWeirdString.
     * Test Type: Integration Test
     */
	@Test
	public void testCountDigits3() {
        myweirdstring.setWeirdString("Th1s is a we2rd str3ng");
        String newString = myweirdstring.getOddCharacters();

        MyWeirdString myWeirdStringNew = new MyWeirdString();
        myWeirdStringNew.setWeirdString(newString);
        assertEquals(2, myWeirdStringNew.countDigits());
	}

    /**
     * Test: This test is to ensure the performance of the functionality of counting digits is within the
     *       time specified.
     * Test Type: Performance
     */
	@Test(timeout = 10)
	public void testCountDigits4() {
		myweirdstring.setWeirdString(getLargeAlphNumericString());
        assertEquals(63,myweirdstring.countDigits());
	}

    /**
     * Test: This test is to check the functionality of displaying even characters.
     * Test Type: State Test
     */
	@Test
	public void testGetEvenCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("' etrptsm 1isi hs5rn,rgt", myweirdstring.getEvenCharacters());
	}

    /**
     * Test: This test is to check the functionality of displaying even characters with special characters.
     * Test Type: State Test
     */
	@Test
	public void testGetEvenCharacters2() {
        myweirdstring.setWeirdString("@\tEx@mpl# T#$t \n *#$$@g#");
        assertEquals("\txml #t\n*$@#", myweirdstring.getEvenCharacters());
	}

    /**
     * Test: This is test to check multiple functionality of MyWeirdString.
     * Test Type: Integration Test
     */
	@Test
	public void testGetEvenCharacters3() {
        myweirdstring.setWeirdString("Th1s is a we2rd str3ng");
        String newString = myweirdstring.getEvenCharacters();

        MyWeirdString myWeirdStringNew = new MyWeirdString();
        myWeirdStringNew.setWeirdString(newString);
        assertEquals(1, myWeirdStringNew.countDigits());
	}

    /**
     * Test: This test is to ensure the performance of the functionality of displaying even characters is
     *       within the time specified.
     * Test Type: Performance
     */
	@Test(timeout = 10)
	public void testGetEvenCharacters4() {
        myweirdstring.setWeirdString(getLargeAlphNumericString());
        assertEquals(getEvenCharactersLargeString(),myweirdstring.getEvenCharacters());
	}

    /**
     * Test: This test is to check the functionality of displaying odd characters.
     * Test Type: State Test
     */
	@Test
	public void testGetOddCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("Idbte u 0edgt nti t19 ih?", myweirdstring.getOddCharacters());
	}

    /**
     * Test: This test is to check the functionality of displaying odd characters with special characters.
     * Test Type: State Test
     */
	@Test
	public void testGetOddCharacters2() {
        myweirdstring.setWeirdString("\tEx@mpl# T#$t \n *#$$@g#");
        assertEquals("\txml #t\n*$@#", myweirdstring.getOddCharacters());
	}

    /**
     * Test: This is test to check multiple functionality of MyWeirdString.
     * Test Type: Integration Test
     */
	@Test
	public void testGetOddCharacters3() {
        myweirdstring.setWeirdString("Th1s is my v3ry str@nge @nd we2rd str3ng");
        myweirdstring.convertDigitsToRomanNumeralsInSubstring(1,40);
        assertEquals(0, myweirdstring.countDigits());
	}

    /**
     * Test: This test is to ensure the performance of the functionality of displaying odd characters is
     *       within the time specified.
     * Test Type: Performance
     */
	@Test(timeout = 10)
	public void testGetOddCharacters4() {
		myweirdstring.setWeirdString(getLargeAlphNumericString());
        assertEquals(getOddCharactersLargeString(),myweirdstring.getOddCharacters());
	}

    /**
     * Test: This test is to check the functionality of displaying roman numeral
     *       converted string.
     * Test Type: State Test
     */
    @Test
    public void testConvertDigitsToRomanNumeralsInSubstring1() {
        myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
        myweirdstring.convertDigitsToRomanNumeralsInSubstring(40, 45);
        assertEquals("I'd better put s0me d1gits in this 5tr1nIX, right?", myweirdstring.getWeirdString());
    }

    /**
     * Test: This is to check the right exception is called when incorrect parameters are passed.
     * Test Type: Behavior Test
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToRomanNumeralsInSubstring2() {
        myweirdstring.setWeirdString("My wei3rd 4St6ring");
        myweirdstring.convertDigitsToRomanNumeralsInSubstring(0,5);
    }

    /**
     * Test: This is to check the right exception is called when incorrect parameters are passed.
     * Test Type: Behavior Test
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConvertDigitsToRomanNumeralsInSubstring3() {
        myweirdstring.setWeirdString("So3e 6ore o7 wei3rd 4St6ring");
        myweirdstring.convertDigitsToRomanNumeralsInSubstring(15, 10);
    }

    /**
     * Test: This is to check the right exception is called when incorrect parameters are passed.
     * Test Type: Behavior Test
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToRomanNumeralsInSubstring4() {
        myweirdstring.setWeirdString("My wei3rd 4St6ring a7d m0re");
        myweirdstring.convertDigitsToRomanNumeralsInSubstring(5, 50);
    }

    /**
     * Test: This test is to check the functionality of displaying roman numeral with special characters.
     * Test Type: State Test
     */
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring5() {
        myweirdstring.setWeirdString("\tMy wei3rd 4$t6ring @7d m0ri");
        myweirdstring.convertDigitsToRomanNumeralsInSubstring(11, 25);
        assertEquals("\tMy wei3rd IV$tVIring @VIId m0ri",myweirdstring.getWeirdString());
	}

    /**
     * Test: This test is to ensure the performance of the functionality of displaying roman numeral
     *       converted string is within the time specified.
     * Test Type: Performance
     */
	@Test(timeout = 10)
	public void testConvertDigitsToRomanNumeralsInSubstring6() {
		myweirdstring.setWeirdString(getLargeAlphNumericString());
        myweirdstring.convertDigitsToRomanNumeralsInSubstring(1,400);
        assertEquals(getCompletelyConvertedRomanNumeralString(),myweirdstring.getWeirdString());
	}

    /**
     * This is to get a large alpha-numeric string.
     * @return result string
     */
    private String getLargeAlphNumericString(){
        return "This is my w3ird string t0 t3st p3rf0rmanc3 0f vari0us parts 0f my w3ird string int3rfac3." +
                "It wi77 b3 run and sh0u7d b3 3x3cut3d v3ry quick7y. If it tak3s t00 much tim3 as th3 aim h3r3 is t0 " +
                "ch3ck f0r th3 p3rf0rmanc3 0f th3 app7icati0n. Taking a 70ng3r than usua7 tim3 can hav3 significant " +
                "impact 0n th3 syst3m. Th3s3 t3sts ar3 critica7 t0 c0nfirm 0ur c0d3 und3r t3sts maintains high qua7ity."+
                "Tests Ends.";
    }

    /**
     * This is to get a large even alpha-numeric string.
     * @return result string
     */
    private String getEvenCharactersLargeString(){
        return "hsi ywidsrn 0ts 3frac fvr0sprs0 ywidsrn n3fc.tw7 3rnads07 333u3 3yqiky fi a3 0 uhtm st3amhr st hc " +
                "0 h 3frac ft3apiain aiga7n3 hnuu7tm a a3sgiiatipc nt3ss3.T33tssa3ciia 0cnim0rcd n3 3t anan ihqaiyTssEd.";
    }

    /**
     * This is to get a large odd alpha-numeric string.
     * @return result string
     */
    private String getOddCharactersLargeString(){
        return "Ti sm 3r tigt 3tpr0mn30 aiu at fm 3r tigitra3I i7b u n hudb xctdvr uc7.I ttkst0mc i3a h i 33i " +
                "0c3kfrt3pr0mn30 h p7ct0.Tkn  0grta sa i3cnhv infcn mat0 h ytm hs 3t r rtc7t 0fr u 03udrtssmitishg u7t.et ns";
    }

    /**
     * This is to get a large Roman Numerical alpha-numeric string.
     * @return result string
     */
    private String getCompletelyConvertedRomanNumeralString(){
        return "This is my wIIIird string t0 tIIIst pIIIrf0rmancIII 0f vari0us parts 0f my wIIIird string intIIIrfacIII." +
                "It wiVIIVII bIII run and sh0uVIId bIII IIIxIIIcutIIId vIIIry quickVIIy. If it takIIIs t00 much timIII " +
                "as thIII aim hIIIrIII is t0 chIIIck f0r thIII pIIIrf0rmancIII 0f thIII appVIIicati0n. Taking a " +
                "VII0ngIIIr than usuaVII timIII can havIII significant impact 0n thIII systIIIm. ThIIIsIII tIIIsts arIII " +
                "criticaVII t0 c0nfirm 0ur c0dIII undIIIr tIIIsts maintains high quaVIIity.Tests Ends.";
    }
}
