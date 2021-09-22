package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.swing.plaf.ComponentInputMapUIResource;

public class TestCountDownTimer {

	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
	}

	@Test
	public void testConstructor3Parameters() {
		CountDownTimer s = new CountDownTimer(0, 0, 0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(2, 3, 4);
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(-2, 3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegHour() {
		new CountDownTimer(-2, 3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, -3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegMinute() {
		new CountDownTimer(2, -3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, 3, -4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegSecond() {
		new CountDownTimer(2, 3, -4);
	}

	// Testing for exceptions; testing all 3 at the same time
	@Test
	public void testConstructor3ParametersNegInput() {
		try {
			new CountDownTimer(-2, 3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, -3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, 3, -4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer(12,67,14);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeMinute() {
		new CountDownTimer(12, 60, 14);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeSecond() {
		new CountDownTimer(12, 59, 60);
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer("a");" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParameterAlpha() {
		new CountDownTimer("a");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringLarge() {
		new CountDownTimer("1:23:45:678");
	}

	@Test
	public void testAdd1() {
		CountDownTimer s = new CountDownTimer();

		s.add(1);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 1);
	}

	@Test
	public void testAdd10() {
		CountDownTimer s = new CountDownTimer();

		s.add(10);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 10);
	}

	@Test
	public void testAdd59() {
		CountDownTimer s = new CountDownTimer();

		s.add(59);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testAdd60() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 min
		s.add(60);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 0);
	}


	@Test
	public void testDec1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec 1
		s.dec();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 58);
	}

	@Test
	public void testDecRollOver() {
		CountDownTimer s = new CountDownTimer(1, 0, 0);

		// dec 1
		s.dec();
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testIncRollOver() {
		CountDownTimer s = new CountDownTimer(0, 59, 59);

		// inc 1
		s.inc();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testEquals() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		CountDownTimer s2 = new CountDownTimer(6, 01, 20);
		CountDownTimer s3 = new CountDownTimer(5, 59, 30);
		CountDownTimer s4 = new CountDownTimer(5, 59, 20);
		CountDownTimer s5 = new CountDownTimer(5, 40, 30);
		CountDownTimer s6 = new CountDownTimer(4, 59, 30);
		CountDownTimer s7 = new CountDownTimer(5, 40, 20);
		CountDownTimer s8 = new CountDownTimer(4, 59, 20);
		CountDownTimer s9 = new CountDownTimer(4, 40, 30);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
		assertEquals(s3, s1);
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s1.equals(s9));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsNull() {
		CountDownTimer s = new CountDownTimer();
		s.equals(null);
	}

	@Test //JU
	public void testToStringNormal(){
		CountDownTimer c = new CountDownTimer("5:10:30");
		assertEquals(c.toString(),"5:10:30");
	}

	@Test //JU
	public void testToStringShortSeconds() {
		CountDownTimer c  = new CountDownTimer("20:10:8");
		assertEquals(c.toString(),"20:10:08");
	}

	@Test //JU
	public void testToStringNoHours() {
		CountDownTimer c = new CountDownTimer("20:08");
		assertEquals(c.toString(), "0:20:08");
	}

	@Test //JU
	public void testToStringMinutes() {
		CountDownTimer c = new CountDownTimer("8");
		assertEquals(c.toString(), "0:00:08");
	}

	@Test //MVD
	public void testToStringLargeHours() {
		CountDownTimer c = new CountDownTimer("5000:0:0");
		assertEquals(c.toString(), "5000:00:00");
	}

	@Test (expected = NumberFormatException.class)//MVD
	public void testToStringNegHours() {
		CountDownTimer c = new CountDownTimer("-23:0:0");
	}

	@Test (expected = NumberFormatException.class)//MVD
	public void testToStringNegMins() {
		CountDownTimer c = new CountDownTimer("0:-23:0");
	}

	@Test (expected = NumberFormatException.class)//MVD
	public void testToStringNegSecs() {
		CountDownTimer c = new CountDownTimer("0:0:-23");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testToStringBlankColons() {
		CountDownTimer c = new CountDownTimer(":::");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testToStringString() {
		CountDownTimer c = new CountDownTimer("this is string");
	}

	@Test (expected = NumberFormatException.class) //JU
	public void testToStringBlankHour(){
		CountDownTimer c = new CountDownTimer(":15:30");
	}

	@Test (expected = NumberFormatException.class) //JU
	public void testToStringBlankMin(){
		CountDownTimer c = new CountDownTimer("6::30");
	}

	@Test (expected = NumberFormatException.class) //JU
	public void testToStringBlankSec(){
		CountDownTimer c = new CountDownTimer("6:15:");
	}

	@Test //JU
	public void testAddInt(){
		CountDownTimer c = new CountDownTimer(5,59,45);
		c.add(20);
		assertEquals(c.toString(),"6:00:05");
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testAddIntNeg(){
		CountDownTimer c = new CountDownTimer(5,59,45);
		c.add(-56);
	}

	@Test //JU
	public void testStopWatchEquals() {
		CountDownTimer c1 = new CountDownTimer(1,21,31);
		CountDownTimer c2 = new CountDownTimer(1,21,31);
		CountDownTimer c3 = new CountDownTimer(2,32,52);

		assertTrue(CountDownTimer.equals(c1, c2));
		assertFalse(CountDownTimer.equals(c1, c3));
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testLoadInvalidFile() {
		CountDownTimer c = new CountDownTimer();
		c.load("FakeFile");

	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testLoadNullFile() {
		CountDownTimer c = new CountDownTimer();
		c.load(null);

	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSaveNullFile() {
		CountDownTimer c = new CountDownTimer();
		c.save(null);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testAddNull() {
		CountDownTimer c = new CountDownTimer();
		c.add(null);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testConstructorStringLargeMinutes() {
		new CountDownTimer("1:72:32");
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testConstructorStringLargeSeconds() {
		new CountDownTimer("1:32:1234");
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testConstructor5IllegalArguments1() {
		new CountDownTimer(1200);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testConstructor5IllegalArguments2() {
		new CountDownTimer(-12);
	}

	@Test //JU
	public void testIsSuspended() {

		boolean test = false;
		CountDownTimer.setSuspend(true);
		test = CountDownTimer.isSuspended();

		assertTrue(test == true);
		CountDownTimer.setSuspend(false);
	}

	@Test //JU
	public void testSetters() {
		CountDownTimer s = new CountDownTimer(0,0,0);
		s.setSeconds(30);
		s.setMinutes(29);
		s.setHours(3);

		assertEquals (s.toString(),"3:29:30");
	}

	@Test //JU
	public void testNoMutateOnSuspend() {
		CountDownTimer s1 = new CountDownTimer(5,59,30);
		CountDownTimer s2 = new CountDownTimer(5,59,30);


		CountDownTimer.setSuspend(true);
		s1.add(1000);
		assertTrue (s1.equals(s2));
		CountDownTimer.setSuspend(false);
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testConstructorStringLongMinutes() {
		new CountDownTimer("1:3849:12");
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testConstructorValidInputsMinSecLarge(){
		new CountDownTimer(70, 70);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testConstructorValidInputsMinSecNeg(){
		new CountDownTimer(-5, -5);
	}

	@Test //JU
	public void testConstructorMinSec(){
		CountDownTimer c = new CountDownTimer(23, 35);

		assertEquals(c.getHours(), 0);
		assertEquals(c.getMinutes(), 23);
		assertEquals(c.getSeconds(), 35);
	}

	@Test //JU
	public void testConstructorSec(){
		CountDownTimer c = new CountDownTimer(35);

		assertEquals(c.getHours(), 0);
		assertEquals(c.getMinutes(), 0);
		assertEquals(c.getSeconds(), 35);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSaveNull() {
		CountDownTimer s = new CountDownTimer();
		s.save(null);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testConstructorStringNull() {
		String s = null;
		new CountDownTimer(s);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testCompareToNull(){
		CountDownTimer c = new CountDownTimer();
		c.compareTo(null);
	}

	@Test //JU
	public void testEqualInstanceOf(){
		CountDownTimer c = new CountDownTimer(4,5,8);
		assertTrue(c instanceof CountDownTimer);
	}

	@Test (expected = IllegalArgumentException.class)//JU
	public void testNotEqualInstanceOf(){
		CountDownTimer c = new CountDownTimer(100,500,800);
		assertFalse(c instanceof CountDownTimer);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSubNeg(){
		CountDownTimer c = new CountDownTimer();
		c.sub(-5);
	}

	@Test //JU
	public void testSub(){
		CountDownTimer c = new CountDownTimer(4 ,45,15);
		c.sub(5);
		assertEquals(c.getHours(), 4);
		assertEquals(c.getMinutes(), 45);
		assertEquals(c.getSeconds(), 10);
	}

	@Test //JU
	public void testSubRollOverMin(){
		CountDownTimer c = new CountDownTimer(4 ,41,15);
		c.sub(25);
		assertEquals(c.getHours(), 4);
		assertEquals(c.getMinutes(), 40);
		assertEquals(c.getSeconds(), 50);
	}

	@Test //JU
	public void testSubRollOverHour(){
		CountDownTimer c = new CountDownTimer(4 ,0,15);
		c.sub(25);
		assertEquals(c.getHours(), 3);
		assertEquals(c.getMinutes(), 59);
		assertEquals(c.getSeconds(), 50);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSubNull(){
		CountDownTimer c = new CountDownTimer();
		c.sub(null);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSetSecNeg(){
		CountDownTimer c = new CountDownTimer();
		c.setSeconds(-6);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSetMinNeg(){
		CountDownTimer c = new CountDownTimer();
		c.setMinutes(-6);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSetHoursNeg(){
		CountDownTimer c = new CountDownTimer();
		c.setHours(-6);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSetSecLarger(){
		CountDownTimer c = new CountDownTimer();
		c.setSeconds(600);
	}

	@Test (expected = IllegalArgumentException.class) //JU
	public void testSetMinLarge(){
		CountDownTimer c = new CountDownTimer();
		c.setMinutes(600);
	}

	@Test //JU
	public void testGetTotalTime(){
		CountDownTimer c = new CountDownTimer(5, 23, 40);
		assertEquals(c.getTotalTime(), 19420);
	}

	@Test (expected = IllegalArgumentException.class)//JU
	public void testDecException(){
		CountDownTimer c = new CountDownTimer(0,0,0);
		c.dec();
	}


	@Test (expected = IllegalArgumentException.class)//MVD
	public void testEqualsObjectNull() {
		CountDownTimer c = new CountDownTimer(0, 0, 0);
		c.equals(c, null);
		c.equals(null, c);
	}

	@Test //MVD
	public void testCompareToOne() {
		CountDownTimer c = new CountDownTimer(1, 1, 1);
		CountDownTimer d = new CountDownTimer(0, 0, 0);
		assertEquals(c.compareTo(d), 1);
	}

	@Test //MVD
	public void testCompareToNegOne() {
		CountDownTimer c = new CountDownTimer(0, 0, 0);
		CountDownTimer d = new CountDownTimer(1, 1, 1);
		assertEquals(c.compareTo(d), -1);
	}

	@Test //MVD
	public void testCompareToZero() {
		CountDownTimer c = new CountDownTimer();
		CountDownTimer d = new CountDownTimer();
		assertEquals(c.compareTo(d), 0);
	}

	@Test //MVD
	public void testCompareToDoubleOne() {
		CountDownTimer b = new CountDownTimer();
		CountDownTimer c = new CountDownTimer(1);
		CountDownTimer d = new CountDownTimer(0);
		assertEquals(b.compareTo(c,d), 1);
	}

	@Test //MVD
	public void testCompareToDoubleNegOne() {
		CountDownTimer b = new CountDownTimer();
		CountDownTimer c = new CountDownTimer(0);
		CountDownTimer d = new CountDownTimer(1);
		assertEquals(b.compareTo(c,d), -1);
	}

	@Test //MVD
	public void testCompareToDoubleZero() {
		CountDownTimer b = new CountDownTimer();
		CountDownTimer c = new CountDownTimer();
		CountDownTimer d = new CountDownTimer();
		assertEquals(b.compareTo(c,d), 0);
	}

	@Test //MVD
	public void testCountDownTimerOther() {
		CountDownTimer c = new CountDownTimer();
		CountDownTimer d = new CountDownTimer(c);
	}

	@Test (expected = IllegalArgumentException.class)//MVD
	public void testCountDownTimerOtherNull() {
		CountDownTimer c = null;
		CountDownTimer d = new CountDownTimer(c);
	}

	@Test //MVD
	public void testSuspend() {
		CountDownTimer c = new CountDownTimer(1, 1, 1);
		c.setSuspend(true);
		c.setSuspend(false);

	}

	@Test //MVD
	public void testGetSuspend() {
		CountDownTimer c = new CountDownTimer(1, 1, 1);
		c.setSuspend(true);
		assertTrue(c.isSuspended());
		c.setSuspend(false);
	}

	@Test //MVD
	public void testSubtractSuspend() {
		CountDownTimer c = new CountDownTimer(50);
		c.setSuspend(true);
		c.sub(40);
		c.setSuspend(false);
	}


	@Test //MVD
	public void testIncrementSuspend() {
		CountDownTimer c = new CountDownTimer(50);
		c.setSuspend(true);
		c.inc();
		c.setSuspend(false);
	}

	@Test //MVD
	public void testDecrementSuspend() {
		CountDownTimer c = new CountDownTimer(50);
		c.setSuspend(true);
		c.inc();
		c.setSuspend(false);
	}

	@Test //MVD
	public void testIncrement() {
		CountDownTimer c = new CountDownTimer(1,59,59);
		while (c.getTotalTime() != 10000) {
			c.inc();
		}
	}

	@Test //MVD
	public void testDecrement() {
		CountDownTimer c = new CountDownTimer(1,59,59);
		while (c.getTotalTime() != 0) {
			c.dec();
		}
	}

	@Test (expected = IllegalArgumentException.class)//MVD
	public void testSubObjectNull() {
		CountDownTimer c = new CountDownTimer(1,59,59);
		CountDownTimer d = null;
		c.sub(d);
	}

	@Test (expected = IllegalArgumentException.class)//MVD
	public void testAddObjectNull() {
		CountDownTimer c = new CountDownTimer(1,59,59);
		CountDownTimer d = null;
		c.add(d);
	}

	@Test //MVD
	public void testSubObject() {
		CountDownTimer c = new CountDownTimer(1,59,59);
		CountDownTimer d = new CountDownTimer(1,59,0);
		CountDownTimer e = new CountDownTimer(0,0,59);
		c.sub(e);
		assertEquals(c,d);
	}

	private void testLoadHelper(String fInput) {
		CountDownTimer c = new CountDownTimer(4,2,3);
		String testFile = "testbadload";

		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(testFile)));
			out.println(fInput);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

		c.load(testFile);

		assertEquals(4, c.getHours());
		assertEquals(2, c.getMinutes());
		assertEquals(3, c.getSeconds());

		File f = new File(testFile);
		if (f.exists()) {
			f.delete();
		}
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testLoad3String() {
		testLoadHelper("java test boring");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testLoad2String() {
		testLoadHelper("1 test boring");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testLoadString() {
		testLoadHelper("1 2 bored");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testLoadNum() {
		testLoadHelper("1");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testLoadStringVal() {
		testLoadHelper("bored");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testLoadEmpty() {
		testLoadHelper("");
	}

	@Test //MVD
	public void testSave() {
		CountDownTimer t1 = new CountDownTimer(3,6,5);
		CountDownTimer t2 = new CountDownTimer(3,6,5);
		t1.save("testsave");
		t1 = new CountDownTimer();
		t1.load("testsave");
		assertTrue(t1.equals(t2));
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testNegativeSecsSave() {
		testLoadHelper("1 1 -1");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testNegativeMinsSave() {
		testLoadHelper("1 -1 1");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testNegativeHoursSave() {
		testLoadHelper("-1 1 1");
	}

	@Test //MVD
	public void testWorkingSave() { //MVD
		testLoadHelper("4 2 3");
	}

	@Test (expected = IllegalArgumentException.class) //MVD
	public void testEqualsObject() {
		CountDownTimer c = new CountDownTimer();
		File f = new File("testsave");
		c.equals(f);
	}

	@Test //MVD
	public void testAdd() {
		CountDownTimer c = new CountDownTimer();
		CountDownTimer d = new CountDownTimer(30);
		c.add(d);
		assertEquals(c, d);
	}
}