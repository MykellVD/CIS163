package project1;


/*****************************************************************
 CountDownTimer
 @author Jake Umlor
 @author Michael Van Duine
 @version Fall 2021
 *****************************************************************/

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CountDownTimer {

	/** current value for hours */
	private int hours;

	/** current value for minutes */
	private int minutes;

	/** current value for seconds */
	private int seconds;

	/** does not allow suspend */
	private static boolean suspend = false;

	/*****************************************************************
	 Default constructor that sets the CountDownTimer to zero.
	 *****************************************************************/
	public CountDownTimer() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	/*****************************************************************
	 A constructor that initializes the instance variables with the
	 provided values.
	 @param hours the hours in the CountDownTimer
	 @param minutes the minutes in the CountDownTimer
	 @param seconds the seconds in the CountDownTimer
	 *****************************************************************/
	public CountDownTimer(int hours, int minutes, int seconds) {
		if (hours < 0 || minutes < 0 || minutes >= 60 || seconds < 0
				|| seconds >= 60) {
			throw new IllegalArgumentException();
		} else {
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
		}
	}

	/*****************************************************************
	 A constructor that initializes the instance variables with the
	 provided values. Initialize hours to 0.
	 @param minutes the minutes in the CountDownTimer
	 @param seconds the seconds in the CountDownTimer
	 @throws IllegalArgumentException when the parameters minutes and
	 seconds are not valid inputs.
	 *****************************************************************/
	public CountDownTimer(int minutes, int seconds) {
		if (minutes < 0 || minutes >= 60 || seconds < 0 || seconds >=
				60) {
			throw new IllegalArgumentException();
		} else {
			this.minutes = minutes;
			this.seconds = seconds;
			hours = 0;
		}
	}

	/*****************************************************************
	 A constructor that initializes the instance variable seconds with
	 the provided value. Initialize hours and minutes to 0.
	 @param seconds the seconds in the CountDownTimer
	 @throws IllegalArgumentException when the parameter seconds isn't
	 a valid input.
	 *****************************************************************/
	public CountDownTimer(int seconds) {
		if (seconds < 0 || seconds >= 60) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = seconds;
			minutes = 0;
			hours = 0;
		}
	}

	/*****************************************************************
	 A constructor that initializes the instance variables with the
	 other CountDownTimer parameter’s instance variables.
	 @param other the other CountDownTimer
	 @throws IllegalArgumentException when the other CountDownTimer
	 equals null.
	 *****************************************************************/
	public CountDownTimer(CountDownTimer other) {
		if (other == null) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = other.seconds;
			this.minutes = other.minutes;
			this.hours = other.hours;
		}
	}

	/*****************************************************************
	 A constructor that accepts a String as a parameter with the
	 following format: “1:21:30” where 1 indicates hours, 21 indicates
	 minutes,  and 30 indicates seconds.  OR the format “15:20” where
	 the 15 indicates minutes, and 20 indicates seconds, OR the format
	 “30” where 30 indicates seconds.  If a value is not specified,
	 i.e., "", then it is set to zero. You can assume the input has no
	 errors (i.e., a valid set of numbers is provided).
	 @param startTime a string of numbers to be formated
	 @throws IllegalArgumentException when startTime is equal to null
	 and when the length of startTimeSplit is greater than 3.
	 @throws NumberFormatException if the values for hours, minutes,
	 and seconds are invalid.
	 *****************************************************************/
	public CountDownTimer(String startTime) {
		if (startTime == null) {
			throw new IllegalArgumentException();
		} else {
			String[] startTimeSplit = startTime.split(":",
					-1);

			hours = 0;
			minutes = 0;
			seconds = 0;

			if (startTimeSplit.length > 3)  {
				throw new IllegalArgumentException();
			}
			if (startTimeSplit.length == 1 && startTimeSplit[0] != ""){
				hours = 0;
				minutes = 0;
				seconds = Integer.parseInt(startTime);
			} else if (startTimeSplit.length == 2) {
				hours = 0;
				minutes = Integer.parseInt(startTimeSplit[0]);
				seconds = Integer.parseInt(startTimeSplit[1]);
			} else if (startTimeSplit.length == 3) {
				hours = Integer.parseInt(startTimeSplit[0]);
				minutes = Integer.parseInt(startTimeSplit[1]);
				seconds = Integer.parseInt(startTimeSplit[2]);
			}
			if (hours < 0 || minutes > 59 || seconds > 59 || minutes <0
					|| seconds < 0) {
				throw new NumberFormatException();
			} else {
				this.hours = hours;
				this.minutes = minutes;
				this.seconds = seconds;
			}

		}
	}

	/*****************************************************************
	 A method that returns true if  “this” CountDownTimer object is
	 exactly the same (mins = other.mins && secs = other.secs && …) as
	 the other object. (Note: you must cast the other object as a
	 CountDownTimer object.)
	 @param other the other CountDownTimer
	 @return rtn returns false
	 @throws IllegalArgumentException when the other CountDownTimer is
	 null and when other is not an instanceof CountDownTimer
	 *****************************************************************/
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

	/*****************************************************************
	 A static method that returns true if CountDownTimer object t1 is
	 exactly the same as CountDownTimer object t2 (i.e. mins =
	 other.mins && secs = other.secs && …).
	 @param t1 a CountDownTimer
	 @param t2 a CountDownTimer
	 @throws IllegalArgumentException when either t1 or t2 are null
	 *****************************************************************/
	public static boolean equals (CountDownTimer t1, CountDownTimer
			t2) {
		if(t1 == null || t2 == null){
			throw new IllegalArgumentException();
		}
		if (t1.equals(t2)){
			return true;
		}
		return false;
	}

	/*****************************************************************
	 A method that returns 1 if “this” CountDownTimer object is
	 greater than the other CountDownTimer object; returns -1 if
	 “this” CountDownTimer object is less than the other
	 CountDownTimer; or returns 0 if “this” CountDownTimer object is
	 equal to the other CountDownTimer object.
	 @param other the other CountDownTimer
	 @throws IllegalArgumentException when other is null
	 *****************************************************************/
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

	/*****************************************************************
	 A method that returns 1 if  CountDownTimer object t1 is greater
	 than CountDownTimer object t2; returns -1 if the CountDownTimer
	 object t1 is less than CountDownTimer object t2;  returns 0 if
	 the CountDownTimer object t1 is equal to CountDownTimer object t2.
	 @param t1 a CountDownTimer
	 @param t2 a CountDownTimer
	 *****************************************************************/
	public static int compareTo(CountDownTimer t1, CountDownTimer t2){
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

	/*****************************************************************
	 A method that subtracts the given number of seconds from “this”
	 CountDownTimer object.  You may assume the parameter “seconds” is
	 positive.
	 @param seconds the seconds that are going to be subbed
	 @throws IllegalArgumentException when seconds isn't a valid input
	 *****************************************************************/
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

	/*****************************************************************
	 A method that adds the number of seconds to “this” CountDownTimer
	 object.  You may assume the parameter “seconds” is positive.
	 @param seconds the seconds you are going to add
	 @throws IllegalArgumentException when seconds isn't a valid input
	 *****************************************************************/
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

	/*****************************************************************
	 A method that increments the “this” CountDownTimer by 1 second.
	 *****************************************************************/
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

	/*****************************************************************
	 A method that decrements “this” CountDownTimer by 1 second.
	 @throws IllegalArgumentException when the variables are zero
	 *****************************************************************/
	public void dec() {
		if(suspend == false) {
			if(seconds == 0) {
				if(minutes == 0) {
					if(hours == 0){
						throw new IllegalArgumentException();

					}else{
						hours -= 1;
						minutes = 59;
						seconds = 59;
					}
				}else {
					minutes -= 1;
					seconds = 59;
				}
			}else {
				seconds -= 1;
			}
		}
	}

	/*****************************************************************
	 A method that subtracts CountDownTimer other from the “this”
	 CountDownTimer object.
	 @param other the other CountDownTimer
	 @throws IllegalArgumentException when other is equal to null
	 *****************************************************************/
	public void sub(CountDownTimer other) {
		if (other == null)
			throw new IllegalArgumentException();
		this.sub(other.getTotalTime());
	}

	/*****************************************************************
	 A method that adds CountDownTimer other to “this” CountDownTimer
	 object.
	 @param other the other CoutDownTimer
	 @throws IllegalArgumentException when other is null
	 *****************************************************************/
	public void add(CountDownTimer other) {
		if (other == null)
			throw new IllegalArgumentException();
		this.add(other.getTotalTime());
	}

	/*****************************************************************
	 A method that returns a string that represents the state of a
	 CountDownTimer with the following format:  “1:06:01”.  Display the
	 hours as is; minutes with 2 digits including a leading “0” if
	 minutes < 10, and seconds with 2 digits again including a leading
	 “0” if seconds < 10. Other examples: “21:32:00”, “0:00:00”.
	 @return fmStr the formated String
	 *****************************************************************/
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

	/*****************************************************************
	 This method formats the CountDownTimer into all seconds
	 @return the CountDownTimer in seconds
	 *****************************************************************/
	public int getTotalTime() {
		return (this.hours * 3600)+(this.minutes * 60)+this.seconds;
	}

	/*****************************************************************
	 This method gets the Hours
	 @return the hours
	 *****************************************************************/
	public int getHours() {
		return hours;
	}

	/*****************************************************************
	 This method sets hours
	 @param hours the hours
	 @throws IllegalArgumentException when hours is less than zero
	 *****************************************************************/
	public void setHours(int hours) {
		if (hours < 0) {
			throw new IllegalArgumentException();
		} else {
			this.hours = hours;
		}
	}

	/*****************************************************************
	 This method gets the minutes
	 @return the minutes
	 *****************************************************************/
	public int getMinutes() {
		return minutes;
	}

	/*****************************************************************
	 This method sets the hours
	 @param minutes the minutes
	 @throws IllegalArgumentException when minutes is an invalid input
	 *****************************************************************/
	public void setMinutes(int minutes) {
		if (minutes > 59 || minutes < 0) {
			throw new IllegalArgumentException();
		} else {
			this.minutes = minutes;
		}
	}

	/*****************************************************************
	 This method gets the Seconds
	 @return the seconds
	 *****************************************************************/
	public int getSeconds() {
		return seconds;
	}

	/*****************************************************************
	 This method sets the seconds
	 @param seconds the seconds
	 @throws IllegalArgumentException when seconds is an invalid input
	 *****************************************************************/
	public void setSeconds(int seconds) {
		if (seconds > 59 || seconds < 0) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = seconds;
		}
	}

	/*****************************************************************
	 A method that saves the “this” CountDownTimer to a file; use the
	 parameter filename for the name of the file.
	 @param fileName the name of the file being saved
	 @throws IllegalArgumentException if fileName is null
	 @throws FileNotFoundException if the file is not found
	 @throws IOException
	 *****************************************************************/
	public void save(String fileName){
		if (fileName == null)
			throw new IllegalArgumentException();
		PrintWriter out = null;
		try {
			out = new PrintWriter (new BufferedWriter(new FileWriter(fileName)));
			out.println(this.hours + " " + this.minutes + " "
					+ this.seconds);
		}
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		catch (IOException e){
			throw new IllegalArgumentException();
		}
		finally{
			out.close();
		}
	}

	/*****************************************************************
	 A  method that loads the “this” CountDownTimer object from a file;
	 use the parameter filename for the name of the file.
	 @param fileName the name of the file
	 @throws NullPointerException if fileName is null or if the
	 NumberFormatException is caught
	 @throws NumberFormatException if the hours, minutes, and seconds
	 are invalid inputs
	 *****************************************************************/
	public void load(String fileName){
		if (fileName == null)
			throw new IllegalArgumentException();
		int thours, tmins, tsecs;
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(fileName));
			thours = scanner.nextInt();
			tmins = scanner.nextInt();
			tsecs = scanner.nextInt();
			if (thours < 0 || tmins > 59 || tsecs > 59 || tmins < 0 || tsecs < 0) {
				throw new NumberFormatException();
			} else {
				hours = thours;
				minutes = tmins;
				seconds = tsecs;
			}
		}
		catch (FileNotFoundException e){
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

	/*****************************************************************
	 A  method that loads the “this” CountDownTimer object from a file;
	 use the parameter filename for the name of the file.
	 @param suspend if suspend is true or not
	 *****************************************************************/
	public static void setSuspend(boolean suspend) {
		CountDownTimer.suspend = suspend;
	}

	/*****************************************************************
	 A  method that loads the “this” CountDownTimer object from a file;
	 use the parameter filename for the name of the file.
	 @return suspend
	 *****************************************************************/
	public static boolean isSuspended() {
		return suspend;
	}
}