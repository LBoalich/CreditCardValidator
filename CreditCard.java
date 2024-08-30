/*
 * Name: Credit Card Validator
 * Author: Leah Boalich
 * Date: August 29, 2024
 * Assignment: Module 1 Chapter 6 Exercise 31
 * Description: This program takes a string number input from the user and uses the Luhn check to validate if the number is a valid credit card number.
 */

import java.util.Scanner;

public class CreditCard {
  /** Main method */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String cardNumber = input.nextLine();
    String singleDigits = singleDigitNumbers(cardNumber);
    int sumOfSingleDigits = sumOfDoubleEvenPlace(singleDigits);
    System.out.println(sumOfSingleDigits);
  }

  /** Return true if the card number is valid */
  public static boolean isValid(long number) {
    return true;
  }

  /** Return string of single digit numbers: Step One */
  public static String singleDigitNumbers(String numberString) {
    String singleDigit = "";
    int i;
    for (i = numberString.length() - 2; i >= 0; i -= 2) {
      int digit = Character.getNumericValue(numberString.charAt(i));
      int doubledDigit = digit * 2;
      singleDigit += Integer.toString(getDigit(doubledDigit));
    }
    return singleDigit;
  }

  /** Get the result from Step 2 */
  public static int sumOfDoubleEvenPlace(String numberString) {
    int sum = 0;
    int i;
    for (i = 0; i < numberString.length(); i++) {
      sum += Character.getNumericValue(numberString.charAt(i));
    }
    return sum;
  }

  /** Return this number if it is a single digit, otherwise,
   * return the sum of the two digits */
  public static int getDigit(int number) {
    if (number < 10) {
      return number;
    }
    else {
      String stringNumber = Integer.toString(number);
      int newNumber = 0;
      int i;
      for (i = 0; i < stringNumber.length(); i++) {
        newNumber += Character.getNumericValue((stringNumber.charAt(i)));
      }
      return newNumber;
    }
  }

  /** Return sum of odd-place digits in number */
  public static int sumOfOddPlace(long number) {
    return 0;
  }

  /** Return true if the number d is a prefix for number */
  public static boolean prefixMatched(long number, int d) {
    return true;
  }

  /** Return the number of digits in d */
  public static int getSize(long d) {
    return 0;
  }

  /** Return the first k number of digits from number. If the
   * number of digits in number is less than k, return number. */
  public static long getPrefix(long number, int k) {
    return 0;
  }
}
