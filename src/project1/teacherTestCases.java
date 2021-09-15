package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

public class teacherTestCases {

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

	@Test
	public void testConstructor2Patameters() {
		CountDownTimer s = new CountDownTimer(0, 0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(3, 4);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegMinutes() {
		new CountDownTimer(-1, 0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegSeconds() {
		new CountDownTimer(0, -1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersLargeMinutes() {
		new CountDownTimer(60, 59);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersLargeSeconds() {
		new CountDownTimer(59, 60);
	}

	@Test
	public void testConstructor1Parameter() {
		CountDownTimer s = new CountDownTimer(0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(4);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 4);
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer(-2);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterNeg() {
		new CountDownTimer(-1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterLarge() {
		new CountDownTimer(60);
	}

	@Test
	public void testConstructorSWInput() {
		CountDownTimer s1 = new CountDownTimer();
		CountDownTimer s2 = new CountDownTimer(s1);
		assertEquals(0, s2.getHours());
		assertEquals(0, s2.getMinutes());
		assertEquals(0, s2.getSeconds());

		s1 = new CountDownTimer(2, 3, 4);
		s2 = new CountDownTimer(s1);
		assertEquals(2, s2.getHours());
		assertEquals(3, s2.getMinutes());
		assertEquals(4, s2.getSeconds());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorSWNull() {
		CountDownTimer s = null;
		new CountDownTimer(s);
	}

	@Test
	public void testConstructorString3Parameters() {
		
		// All 0s
		CountDownTimer s = new CountDownTimer("0:0:0");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		// All single digits
		s = new CountDownTimer("2:3:4");
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);

		// Max minutes
		s = new CountDownTimer("2:59:4");
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 59);
		assertTrue(s.getSeconds() == 4);

		// Max seconds
		s = new CountDownTimer("2:3:59");
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 59);
	}

	@Test
	public void testConstructorString2Parameters() {
		// All ints
		CountDownTimer s = new CountDownTimer("3:4");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);

		// Max minutes
		s = new CountDownTimer("59:4");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 59);
		assertTrue(s.getSeconds() == 4);

		// Max seconds
		s = new CountDownTimer("3:59");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 59);
	}

	@Test
	public void testConstructorString1Parameter() {
		CountDownTimer s = new CountDownTimer("5");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 5);
	}
	
	@Test
	public void testConstructorString0Parameter() {
		CountDownTimer s = new CountDownTimer("");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParameterAllNeg() {
		new CountDownTimer("-1:-2:-3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParameterNegHours() {
		new CountDownTimer("-1:2:3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParameterNegMinutes() {
		new CountDownTimer("1:-2:3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParameterNegSeconds() {
		new CountDownTimer("1:2:-3");
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer("-59:-23");" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParameterAllNeg() {
		new CountDownTimer("-59:-23");
	}


	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParameterNegHours() {
		new CountDownTimer("-2:3");
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer("2:-2");" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParameterNegSeconds() {
		new CountDownTimer("2:-3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParameterNeg() {
		new CountDownTimer("-3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParametersAllLarge() {
		new CountDownTimer("1:60:60");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParametersLargeMinutes() {
		new CountDownTimer("1:60:59");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParametersLargeSeconds() {
		new CountDownTimer("1:59:60");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParametersAllLarge() {
		new CountDownTimer("60:1000");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParametersLargeMinutes() {
		new CountDownTimer("60:59");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParametersLargeSeconds() {
		new CountDownTimer("59:60");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParameterLarge() {
		new CountDownTimer("60");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParametersAllAlpha() {
		new CountDownTimer("a:b:c");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParametersAlphaHours() {
		new CountDownTimer("a:2:3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParametersAlphaMinutes() {
		new CountDownTimer("1:a:3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParametersAlphaSeconds() {
		new CountDownTimer("1:2:a");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParametersAlllAplha() {
		new CountDownTimer("a:b");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParametersAlphaMinutes() {
		new CountDownTimer("a:3");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParametersAlphaSeconds() {
		new CountDownTimer("2:a");
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

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringNull() {
		String s = null;
		new CountDownTimer(s);
	}

	@Test
	public void testToString() {
		// 3 parameters; all digits used
		CountDownTimer s = new CountDownTimer(5,10,30);
		assertEquals(s.toString(), "5:10:30");

		// 3 parameters; seconds < 10
		s = new CountDownTimer("20:10:5");
		assertEquals(s.toString(), "20:10:05");
		
		// 3 parameters; minutes and seconds < 10
		s = new CountDownTimer("20:1:5");
		assertEquals(s.toString(), "20:01:05");
		
		// 3 parameters; all 0
		s = new CountDownTimer("0:0:0");
		assertEquals(s.toString(), "0:00:00");

		// 2 parameters; seconds < 10
		s = new CountDownTimer("20:8");
		assertEquals(s.toString(), "0:20:08");
		
		// 2 parameters; minutes < 10
		s = new CountDownTimer("2:50");
		assertEquals(s.toString(), "0:02:50");
		
		// 2 parameters; all 0
		s = new CountDownTimer("0:0");
		assertEquals(s.toString(), "0:00:00");

		// 1 parameter; seconds > 10
		s = new CountDownTimer("11");
		assertEquals(s.toString(), "0:00:11");
				
		// 1 parameter; seconds < 10
		s = new CountDownTimer("8");
		assertEquals(s.toString(), "0:00:08");
		
		// 1 parameter; all 0
		s = new CountDownTimer("0");
		assertEquals(s.toString(), "0:00:00");
	}
	
	@Test
	public void testSetHours() {
		CountDownTimer s = new CountDownTimer();

		s.setHours(1);
		assertEquals(1, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0, s.getSeconds());

		s.setHours(10);
		assertEquals(10, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0, s.getSeconds());
		
		s.setHours(100);
		assertEquals(100, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0, s.getSeconds());
		
		s.setHours(999);
		assertEquals(999, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0, s.getSeconds());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetHoursNeg() {
		CountDownTimer s = new CountDownTimer();
		s.setHours(-1);
	}

	@Test
	public void testSetMinutes() {
		CountDownTimer s = new CountDownTimer();

		s.setMinutes(1);
		assertEquals(0, s.getHours());
		assertEquals(1, s.getMinutes());
		assertEquals(0, s.getSeconds());

		s.setMinutes(10);
		assertEquals(0, s.getHours());
		assertEquals(10, s.getMinutes());
		assertEquals(0, s.getSeconds());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetMinutesLarge() {
		CountDownTimer s = new CountDownTimer();
		s.setMinutes(60);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetMinutesNeg() {
		CountDownTimer s = new CountDownTimer();
		s.setMinutes(-1);
	}

	@Test
	public void testSetSeconds() {
		CountDownTimer s = new CountDownTimer();

		s.setSeconds(1);
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(1, s.getSeconds());

		s.setSeconds(10);
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(10, s.getSeconds());
		
		s.setSeconds(59);
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(59, s.getSeconds());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetSecondsLarge() {
		CountDownTimer s = new CountDownTimer();
		s.setSeconds(60);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetSecondsNeg() {
		CountDownTimer s = new CountDownTimer();
		s.setSeconds(-1);
	}

	@Test
	public void testInc1() {
		CountDownTimer s = new CountDownTimer();

		// inc 1
		s.inc();
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 1);
	}

	@Test
	public void testInc10() {
		CountDownTimer s = new CountDownTimer();

		for (int i = 0; i < 10; i++) {
			s.inc();
		}
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 10);
	}

	@Test
	public void testInc59() {
		CountDownTimer s = new CountDownTimer();

		for (int i = 0; i < 59; i++) {
			s.inc();
		}
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testInc60() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 min
		for (int i = 0; i < 60; i++) {
			s.inc();
		}
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testInc600() {
		CountDownTimer s = new CountDownTimer();

		// inc to 10 min
		for (int i = 0; i < 600; i++) {
			s.inc();
		}
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 10);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testInc3540() {
		CountDownTimer s = new CountDownTimer();

		// inc to 59 min
		for (int i = 0; i < 3540; i++) {
			s.inc();
		}
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testInc3600() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 hour
		for (int i = 0; i < 3600; i++) {
			s.inc();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testInc36000() {
		CountDownTimer s = new CountDownTimer();

		// inc to 10 hours
		for (int i = 0; i < 36000; i++) {
			s.inc();
		}
		assertEquals(s.getHours(), 10);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
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
	public void testAdd600() {
		CountDownTimer s = new CountDownTimer();

		// inc to 10 min
		s.add(600);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 10);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAdd3540() {
		CountDownTimer s = new CountDownTimer();

		// inc to 59 min
		s.add(3540);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAdd3600() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 hour
		s.add(3600);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAdd36000() {
		CountDownTimer s = new CountDownTimer();

		// inc to 10 minutes
		s.add(36000);
		assertEquals(s.getHours(), 10);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNeg() {
		CountDownTimer s = new CountDownTimer();
		s.add(-1);
	}

	@Test
	public void testAddCountDownTimer1Second() {
		CountDownTimer s = new CountDownTimer();

		s.add(new CountDownTimer(1));
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 1);
	}

	@Test
	public void testAddCountDownTimer10Seconds() {
		CountDownTimer s = new CountDownTimer();

		s.add(new CountDownTimer(10));
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 10);
	}

	@Test
	public void testAddCountDownTimer59Seconds() {
		CountDownTimer s = new CountDownTimer();

		s.add(new CountDownTimer(59));
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testAddCountDownTimer1Minute() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 sec
		s.add(new CountDownTimer(1, 0));
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAddCountDownTimer10Minutes() {
		CountDownTimer s = new CountDownTimer();

		// inc to 10 sec
		s.add(new CountDownTimer(10, 0));
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 10);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAddCountDownTimer59Minutes() {
		CountDownTimer s = new CountDownTimer();

		// inc to 59 sec
		s.add(new CountDownTimer(59, 0));
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAddCountDownTimer1Hour() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 minute
		s.add(new CountDownTimer(1, 0, 0));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAddCountDownTimer10Hours() {
		CountDownTimer s = new CountDownTimer();

		// inc to 10 minutes
		s.add(new CountDownTimer(10, 0, 0));
		assertEquals(s.getHours(), 10);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddCountDownTimerNull() {
		CountDownTimer s = new CountDownTimer();
		s.add(null);
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
	public void testDec10Seconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		for (int i = 0; i < 10; i++) {
			s.dec();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 49);
	}

	@Test
	public void testDec59Seconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec to 0 sec
		for (int i = 0; i < 59; i++) {
			s.dec();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testDecTurnOverMinutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 0);

		s.dec();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 58);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testDec10Minutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec 10 min
		for (int i = 0; i < 600; i++) {
			s.dec();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 49);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testDecTo0Minutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec to 0 sec
		for (int i = 0; i < 3540; i++) {
			s.dec();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testDecTo0MinutesAndSeconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec to 0 min, 0 sec
		for (int i = 0; i < 3599; i++) {
			s.dec();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testDecTurnOverAll() {
		CountDownTimer s = new CountDownTimer(1, 0, 0);

		// dec 1
		s.dec();
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testDec1Hour() {
		CountDownTimer s = new CountDownTimer(2, 59, 59);

		// dec 1 hour
		for (int i = 0; i < 3600; i++) {
			s.dec();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testDec10Hours() {
		CountDownTimer s = new CountDownTimer(11, 59, 59);

		// dec 10 minutes
		for (int i = 0; i < 36000; i++) {
			s.dec();
		}
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testDecException() {
		CountDownTimer s = new CountDownTimer();
		s.dec();
	}
	
	@Test
	public void testSubInt1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub 1
		s.sub(1);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 58);
	}

	@Test
	public void testSubInt10Seconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		s.sub(10);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 49);
	}

	@Test
	public void testSubInt59Seconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub to 0 sec
		s.sub(59);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testSubIntTurnOverSeconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 0);

		s.sub(1);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 58);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubInt10Minutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub 10 min
		s.sub(600);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 49);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubIntTo0Minutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub to 0 min
		s.sub(3540);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubIntTo0MinutesAndSeconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub to 0 min, 0 sec
		s.sub(3599);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testSubIntTurnOverAll() {
		CountDownTimer s = new CountDownTimer(1, 0, 0);

		// sub 1
		s.sub(1);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubInt1Hour() {
		CountDownTimer s = new CountDownTimer(2, 59, 59);

		s.sub(3600);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubInt10Hours() {
		CountDownTimer s = new CountDownTimer(11, 59, 59);

		// sub 10 minutes
		s.sub(36000);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSubIntNeg() {
		CountDownTimer s = new CountDownTimer(10, 10, 10);
		s.sub(-1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSubIntException() {
		CountDownTimer s = new CountDownTimer();
		s.sub(1);
	}
	
	@Test
	public void testSubSW1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub 1
		s.sub(new CountDownTimer(1));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 58);
	}

	@Test
	public void testSubSW10Seconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		s.sub(new CountDownTimer(10));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 49);
	}

	@Test
	public void testSubSW59Seconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub to 0 sec
		s.sub(new CountDownTimer(59));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testSubSWTurnOverMinutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 0);

		s.sub(new CountDownTimer(1));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 58);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubSW10Minutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub 10 sec
		s.sub(new CountDownTimer(10, 0));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 49);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubSWTo0Minutes() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub to 0 sec
		s.sub(new CountDownTimer(59, 0));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubSWTo0MinutesAndSeconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub to 0 min, 0 sec
		s.sub(new CountDownTimer(59, 59));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testSubSWTurnOverAll() {
		CountDownTimer s = new CountDownTimer(1, 0, 0);

		// sub 1
		s.sub(new CountDownTimer(1));
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubSW1Hour() {
		CountDownTimer s = new CountDownTimer(2, 59, 59);

		s.sub(new CountDownTimer(1, 0, 0));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testSubSW10Hours() {
		CountDownTimer s = new CountDownTimer(11, 59, 59);

		// sub 10 minutes
		s.sub(new CountDownTimer(10, 0, 0));
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSubSWException() {
		CountDownTimer s = new CountDownTimer();
		s.sub(null);
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

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsString() {
		CountDownTimer s = new CountDownTimer();
		s.equals("Hello");
	}

	@Test
	public void testStaticEquals() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		CountDownTimer s2 = new CountDownTimer(6, 01, 20);
		CountDownTimer s3 = new CountDownTimer(5, 59, 30);
		CountDownTimer s4 = new CountDownTimer(5, 59, 20);
		CountDownTimer s5 = new CountDownTimer(5, 40, 30);
		CountDownTimer s6 = new CountDownTimer(4, 59, 30);
		CountDownTimer s7 = new CountDownTimer(5, 40, 20);
		CountDownTimer s8 = new CountDownTimer(4, 59, 20);
		CountDownTimer s9 = new CountDownTimer(4, 40, 30);

		assertFalse(CountDownTimer.equals(s1,  s2));
		assertTrue(CountDownTimer.equals(s1,  s3));
		assertFalse(CountDownTimer.equals(s1,  s4));
		assertFalse(CountDownTimer.equals(s1,  s5));
		assertFalse(CountDownTimer.equals(s1,  s6));
		assertFalse(CountDownTimer.equals(s1,  s7));
		assertFalse(CountDownTimer.equals(s1,  s8));
		assertFalse(CountDownTimer.equals(s1,  s9));
	}

	@Test
	public void testCompareTo() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		CountDownTimer s2 = new CountDownTimer(6, 01, 20);
		CountDownTimer s3 = new CountDownTimer(5, 50, 20);
		CountDownTimer s4 = new CountDownTimer(5, 59, 30);

		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s3.compareTo(s1) < 0);
		assertTrue(s1.compareTo(s4) == 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCompareToNull() {
		CountDownTimer s = new CountDownTimer();
		s.compareTo(null);
	}

	@Test
	public void testLoadSave () {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		CountDownTimer s2 = new CountDownTimer(5, 59, 30);

		s1.save("file1");
		s1 = new CountDownTimer();  // resets to zero

		s1.load("file1");
		assertTrue(s1.equals(s2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSaveNull() {
		CountDownTimer s = new CountDownTimer();
		s.save(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLoadNull() {
		CountDownTimer s = new CountDownTimer();
		s.load(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLoadFileNotFound() {
		CountDownTimer s = new CountDownTimer();
		s.load("thisfileshouldnotexistatallever");
	}
	
	private void testBadLoadHelper(String fileInput) {
        CountDownTimer s1 = new CountDownTimer(6, 5, 4);
        String badSaveFile = "Project1TestBadLoad";

        // Create the bad load file with the specified input
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(
                new FileWriter(badSaveFile)));
            out.println(fileInput);
        }
        catch (IOException e) {
            e.printStackTrace();
            Assert.fail("IO Exception encountered");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception encountered");
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
        
        // Try to load the bad save file
//        System.out.println("\n\nSTACK TRACE BELOW THIS POINT IS EXPECTED\n\n");
        s1.load(badSaveFile);
//        System.out.println("\n\nSTACK TRACE ABOVE THIS POINT IS EXPECTED\n\n");

        // Check the the ATM is unchanged
        assertEquals(6, s1.getHours());
        assertEquals(5, s1.getMinutes());
        assertEquals(4, s1.getSeconds());
        
        // Delete the bad save file
        File file = new File(badSaveFile);
        if (file.exists()) {
            file.delete();
        }
    }
	
//	@Test (expected = IllegalArgumentException.class)
//    public void testBadLoadAllStrings() {
//        testBadLoadHelper("foo\nbar\nbaz");
//    }
//    
//    @Test (expected = IllegalArgumentException.class)
//    public void testBadLoad2Strings() {
//        testBadLoadHelper("1\nfoo\nbar");
//    }
//    
//    @Test (expected = IllegalArgumentException.class)
//    public void testBadLoad1String() {
//        testBadLoadHelper("1\n2\nbar");
//    }
//    
//    @Test (expected = IllegalArgumentException.class)
//    public void testBadLoad2Numbers() {
//        testBadLoadHelper("1\n2");
//    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testBadLoad1Number() {
        testBadLoadHelper("1");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testBadLoad1String1Value() {
    	testBadLoadHelper("foo");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testBadLoadEmptyString() {
        testBadLoadHelper("");
    }

	@Test
	public void testSuspendState() {
		assertFalse(CountDownTimer.isSuspended());
		CountDownTimer.setSuspend(true);
		assertTrue(CountDownTimer.isSuspended());
		CountDownTimer.setSuspend(false);
		assertFalse(CountDownTimer.isSuspended());
	}

	@Test
	public void testNoMutateOnSuspend() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		CountDownTimer s2 = new CountDownTimer(5, 59, 30);

		CountDownTimer.setSuspend(true);
		
		// 6:00:30
		s1.add(3600);
		
		// 6:00:33
		s1.add(new CountDownTimer(3));
		
		// 5:59:28
		s1.sub(3605);
		
		// 5:59:29
		s1.inc();
		
		// 5:59:27
		s1.dec();
		s1.dec();
		
		// s1 should not have changed at all
		assertEquals(s1, s2);
		
		// reset suspend so that other methods will work
		CountDownTimer.setSuspend(false);
	}
}
