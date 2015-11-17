package com.lab2.transit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FareCalculatorTest {
	
	private static final double DELTA = 1e-15; 
	
	private double expected;
	private int age;
	private String time;
	private boolean isHoliday;
	
	@Parameters
	public static Collection<Object[]> testParams() {
		return Arrays.asList(new Object[][] {
				{0.0, 4, "6:00", false}, // $0.00 fare for 4 year old at 6:00am on a non-holiday				
				{0.0, 65, "9:01", false}, // $0.00 fare for a 65-year-old at 9:00am on a non-holiday 
				{2.5, 64, "15:00", false}, // $2.50 fare for 26-year-old at 3:00pm on a non-holiday
				{0.0, 0, "6:59", false}, // $0.00 for a 0-year-old at 6:59am on a non-holiday
				{0.0, 5, "6:59", false}, // $0.00 fare for a 5 year old at 6:59am on a non-holiday
				{2.5, 64, "6:59", false}, // $2.50 fare for a 64-year-old at 6:59am on a non-holiday
				{2.5, 64, "7:00", false}, // $2.50 fare for a 64-year-old at 7:00am on a non-holiday
				{2.5, 64, "7:01", false}, // $2.50 fare for a 64-year-old at 7:01am on a non-holiday
				{0.0, 65, "6:59", false}, // $0.00 fare for a 65-year-old at 6:59am on a non-holiday
				{2.5, 65, "7:00", false}, // $2.50 fare for a 65-year-old at 7:00am on a non-holiday
				{2.5, 65, "7:01", false}, // $2.50 fare for a 65-year-old at 7:01am on a non-holiday
				{2.5, 65, "8:59", false}, // $2.50 fare for 65-year-old at 8:59am on a non-holiday
				{2.5, 65, "9:00", false}, // $2.50 fare for a 65-year-old at 9:00am on a non-holiday
				{0.0, 65, "9:01", false}, // $0.00 fare for a 65-year-old at 9:01am on a non-holiday
				{0.0, 5, "8:59", true}, // $0.00 fare for a 5-year-old at 2:00pm on a holiday
				{2.5, 6, "8:59", true}, // $2.50 fare for a 6-year-old at 8:59am on a holiday
				{0.0, 65, "6:59", true}, // $0.00 fare for 65-year-old at 6:59am on a holiday
				{2.5, 64, "6:59", true} // $2.50 fare for a 64-year-old at 6:59am on a holiday
		});
	}
	
	public FareCalculatorTest(double expected, int age, String time, boolean isHoliday) {
		this.expected = expected;
		this.age = age;
		this.time = time;
		this.isHoliday = isHoliday;
	}
	
	@Test
	public void calculateFareTest() {
		assertEquals(expected, FareCalculator.calculateFare(age, time, isHoliday), DELTA);
	}

}
