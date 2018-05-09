
/*
 * Matthew Henschke
 * Lab 2: Numeric Analyzer
 */
package edu.cuny.csi.csc330.lab2;
import java.util.Arrays;
import java.lang.Math;
public class NumericAnalyzer {
	//class variables
    private int [] input;
	private int max;
	private int min;
	private int range;
	private int median;
	private int mean;
	private int sum;
	private int variance;
	private int Dev;
	
	//constructor with array parameter
	public NumericAnalyzer(int [] num) {
		input = num;
		Arrays.sort(input);
	}
	//mutator functions to obtain all mathematical data
	 public void getMin() {
		 min = input[0];
	 }
	 public void getMax() {
		 max = input[input.length - 1];
	 }
	 public void getSum() {
		 sum = 0; 
		 for (int i = 0; i < input.length; i++)
			 sum += input[i];
	 }
	 public void getRange() {
		 range= max - min;
	 }
	 public void getMedian() {
		 median = input[(input.length / 2)];
	 }
	 public void getMean() {
		 mean = sum / input.length; 
	 }
	 
	 public void getVariance() {
		 int total = 0;
		 int val;
		 for (int i = 0; i < input.length; i++) {
			  val = input[i] - mean;
			  total+= Math.pow(val,2);
		 }
		   variance = total/ input.length;
	 }
	 public void getStdDev() {
		 Dev = (int)Math.sqrt(variance);
	 }
	 //perform calculations and display results function
	 public void PerformCalc() {
		 getMin();
		 getMax();
		 getSum();
		 getRange();
		 getMedian();
		 getMean();
		 getVariance();
		 getStdDev();
	 }
	 public void Display() {
		 System.out.print("The numbers you have entered are: ");
		 for(int i = 0 ; i < input.length ; i++ ) 
			System.out.printf("%,d\t", input[i]);
		 System.out.print("\n");
		 System.out.println("The size is: " + input.length);
		 System.out.println("The min is: " +  min );
		 System.out.println("The max is: " + max);
		 System.out.println("The sum is: " + sum);
		 System.out.println("The range is: " + range);
		 System.out.println("The median is: " + median);
		 System.out.println("The mean is: " + mean);
		 System.out.println("The variance is: " + range);
		 System.out.println("The standard deviation is: " + Dev);
		 
			
		 
	 }
     //main function
	public static void main(String[] args) {
		System.out.println("\t\t\tNumeric Analyzer Simulator");
		if (args.length == 0) {
			System.out.println("Invalid Input, Please Enter At Least One Number");
			System.exit(1);
		}
		int [] numbers = new int[args.length]; 
		//converting the strings to the numbers
		for(int i = 0 ; i < args.length ; ++i ) 
			numbers[i] = Integer.parseInt(args[i]); 
		   
		NumericAnalyzer n1 = new NumericAnalyzer(numbers);
		   n1.PerformCalc();
		   n1.Display();
		  
		   System.exit(0);}
}
//end of program

