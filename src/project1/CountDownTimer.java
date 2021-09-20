package project1;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CountDownTimer {

	private int hours;
	private int minutes;
	private int seconds;

	private static boolean suspend = false;

	public CountDownTimer() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	public CountDownTimer(int hours, int minutes, int seconds) {
		if (hours < 0 || minutes < 0 || minutes >= 60 || seconds < 0 || seconds >= 60) {
			throw new IllegalArgumentException();
		} else {
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
			}
	}

	public CountDownTimer(int minutes, int seconds) {
		if (minutes < 0 || minutes >= 60 || seconds < 0 || seconds >= 60) {
			throw new IllegalArgumentException();
		} else {
			this.minutes = minutes;
			this.seconds = seconds;
			hours = 0;
		}
	}

	//add test case
	public CountDownTimer(int seconds) {
		if (seconds < 0 || seconds >= 60) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = seconds;
			minutes = 0;
			hours = 0;
		}
	}

	//add test case
	public CountDownTimer(CountDownTimer other) {
		if (other == null) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = other.seconds;
			this.minutes = other.minutes;
			this.hours = other.hours;
		}
	}

	public CountDownTimer(String startTime) {
		if (startTime == null) {
			throw new IllegalArgumentException();
		} else {
			String[] startTimeSplit = startTime.split(":",-1);

			hours = 0;
			minutes = 0;
			seconds = 0;

			if (startTimeSplit.length > 3)  {
				throw new IllegalArgumentException();
			}
			if (startTimeSplit.length == 1 && startTimeSplit[0] != "") {
				seconds = Integer.parseInt(startTime);
			} else if (startTimeSplit.length == 2) {
				minutes = Integer.parseInt(startTimeSplit[0]);
				seconds = Integer.parseInt(startTimeSplit[1]);
			} else if (startTimeSplit.length == 3) {
				hours = Integer.parseInt(startTimeSplit[0]);
				minutes = Integer.parseInt(startTimeSplit[1]);
				seconds = Integer.parseInt(startTimeSplit[2]);
			}
			if (hours < 0 || minutes > 59 || seconds > 59 || minutes < 0 || seconds < 0) {
				throw new NumberFormatException();
			} else {
				this.hours = hours;
				this.minutes = minutes;
				this.seconds = seconds;
			}

		}
	}

	public boolean equals(Object other) {
		boolean rtn = false;
		if (other == null){
			throw new IllegalArgumentException();
		}
		else if (other instanceof CountDownTimer){
			if(this.hours == ((CountDownTimer) other).hours &&
					this.minutes == ((CountDownTimer) other).minutes &&
					this.seconds == ((CountDownTimer) other).seconds) {
				rtn = true;
			}
			else {
				rtn = false;
			}
		} else {
			throw new IllegalArgumentException();
		}
		return rtn;
	}

	public static boolean equals (CountDownTimer t1, CountDownTimer t2) {
		if (t1.equals(t2)){
			return true;
		}
		return false;
	}

	//add test case
	public int compareTo(CountDownTimer other) {
		if (other == null) {
			throw new IllegalArgumentException();
		} else {
			if (this.getTotalTime() > other.getTotalTime()) {
				return 1;
			}
			else if (this.getTotalTime() < other.getTotalTime()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}

	//add test case
	public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
		if (t1.getTotalTime() > t2.getTotalTime()) {
			return 1;
		}
		else if (t1.getTotalTime() < t2.getTotalTime()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	//add test case
	public void sub(int seconds) {
		if (suspend == false) {
			if (seconds < 0) {
				throw new IllegalArgumentException();
			} else {
				for (int i = 0; i < seconds; i++) {
					dec();
				}
			}
		}
	}

	public void add(int seconds) {
		if(suspend == false) {
			if(seconds < 0) {
				throw new IllegalArgumentException();
			}else {
				for(int i=0;i<seconds;i++) {
					inc();
				}
			}
		}
	}

	public void inc(){
		if(suspend == false) {
			if(seconds == 59) {
				seconds = 0;
				if(minutes == 59) {
					minutes = 0;
					hours += 1;
				}else {
					minutes += 1;
				}
			}else {
				seconds += 1;
			}
		}
	}

	public void dec() {
		if(suspend == false) {
			if(seconds == 0) {
				if(minutes == 0) { //add test case
					if(hours == 0){
						throw new IllegalArgumentException();

					}else{ //add test case
						hours -= 1;
						minutes = 59;
						seconds = 59;
					}
				}else { //add test case
					minutes -= 1;
					seconds = 59;
				}
			}else {
				seconds -= 1;
			}
		}
	}

	public void sub(CountDownTimer other) {
		if (other == null)
			throw new IllegalArgumentException();
		//add test case and error checks
		this.sub(other.getTotalTime());
	}

	public void add(CountDownTimer other) {
		if (other == null)
			throw new IllegalArgumentException();
		//add test case
		this.add(other.getTotalTime());
	}

	public String toString() {
		String fmStr = hours + ":";
		if (minutes < 10) {
			fmStr += "0" + minutes + ":";
		}
		else {
			fmStr += minutes + ":";
		}
		if (seconds < 10) {
			fmStr += "0" + seconds;
		}
		else {
			fmStr += seconds;
		}

		return fmStr;
	}

	//add test case
	public int getTotalTime() {
		return (this.hours * 3600) + (this.minutes * 60) + this.seconds;
	}

	public int getHours() {
		return hours;
	}

	//add test case
	public void setHours(int hours) {
		if (hours < 0) {
			throw new IllegalArgumentException();
		} else {
			this.hours = hours;
		}
	}

	public int getMinutes() {

		return minutes;
	}

	//add test case
	public void setMinutes(int minutes) {
		if (minutes > 59 || minutes < 0) {
			throw new IllegalArgumentException();
		} else {
			this.minutes = minutes;
		}
	}

	public int getSeconds() {
		return seconds;
	}

	//add test case
	public void setSeconds(int seconds) {
		if (seconds > 59 || seconds < 0) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = seconds;
		}
	}

	public void save(String fileName){
		if (fileName == null)
			throw new IllegalArgumentException();
		PrintWriter out = null;
		try {
			out = new PrintWriter (new BufferedWriter(new FileWriter(fileName)));
			out.println(this.hours + " " + this.minutes + " " + this.seconds);
		}
		//add test case
		catch (FileNotFoundException e) {

		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally{
			out.close();
		}
	}

	public void load(String fileName){
		if (fileName == null)
			throw new IllegalArgumentException();
		int thours, tmins, tsecs;
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(fileName));
			//add test case where nextInts are negative
			thours = scanner.nextInt();
			tmins = scanner.nextInt();
			tsecs = scanner.nextInt();
			if (thours < 0 || tmins < 59 || tsecs < 59 || tmins > 0 || tsecs > 0) {
				hours = thours;
				minutes = tmins;
				seconds = tsecs;
			} else {
				throw new NumberFormatException();
			}
		}
		catch (FileNotFoundException e){
			throw new IllegalArgumentException();
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	//turns on and off all stopWatch object
	public static void setSuspend(boolean suspend) {
		CountDownTimer.suspend = suspend;
	}

	//returns suspend
	public static boolean isSuspended() {
		return suspend;
	}
}