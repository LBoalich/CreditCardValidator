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
    //Create scanner
    Scanner input = new Scanner(System.in);
    //Get credit card number from the user
    System.out.println("Enter a credit card number as a long integer: ");
    String cardNumber = input.nextLine();
    //Check if card is valid
    String isValid = isValid(cardNumber) ? "valid" : "invalid";
    //Display if card is valid or not
    System.out.println(cardNumber + " is " + isValid);
  }

  /** Return true if the card number is valid */
  public static boolean isValid(String numberString) {
    //Checks if between 13 and 16 digits
    boolean validLength = false;
    if (getSize(numberString) >= 13 && getSize(numberString) <= 16) {
      validLength = true;
    } 
    //Checks if starts with valid prefix
    boolean validPrefix = prefixIsValid(numberString);
    //Preform Luhn Check
    //Double every second digit from right to left
    String singleDigits = singleDigitNumbers(numberString);
    //Add single digits
    int sumOfSingleDigits = sumOfDoubleEvenPlace(singleDigits);
    //Add digits in odd places from right to left
    int sumOfOdd = sumOfOddPlace(numberString);
    //Add sum of single digits and sum of odd
    int sumOfSingleAndOdd = sumOfSingleDigits + sumOfOdd;
    //Check if divisible by 10
    boolean isMod10 = sumOfSingleAndOdd % 10 == 0 ? true : false;
    // Return true if passes all checks
    return (validLength && validPrefix && isMod10);
  }

  /** Return true if the card prefix is valid */
  public static boolean prefixIsValid(String numberString) {
    boolean isVisa = prefixMatched(numberString, 4);
    boolean isMater = prefixMatched(numberString, 5);
    boolean isAmExpress = prefixMatched(numberString, 37);
    boolean isDiscover = prefixMatched(numberString, 6);
    return (isVisa || isMater || isAmExpress || isDiscover);
  }

  /** Return string of single digit numbers: Step One */
  public static String singleDigitNumbers(String numberString) {
    String singleDigit = "";
    int i;
    for (i = getSize(numberString) - 2; i >= 0; i -= 2) {
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
    for (i = 0; i < getSize(numberString); i++) {
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
  public static int sumOfOddPlace(String numberString) {
    int sum = 0;
    int i;
    for (i = getSize(numberString) - 1; i >= 0; i -= 2) {
      sum += Character.getNumericValue(numberString.charAt(i));
    }
    return sum;
  }

  /** Return true if the number d is a prefix for number */
  public static boolean prefixMatched(String numberString, int d) {
    String dString = Integer.toString(d);
    String prefix = getPrefix(numberString, getSize(dString));
    int prefixInt = Integer.parseInt(prefix);
    return prefixInt == d;
  }

  /** Return the number of digits in d */
  public static int getSize(String numberString) {
    return numberString.length();
  }

  /** Return the first k number of digits from number. If the
   * number of digits in number is less than k, return number. */
  public static String getPrefix(String numberString, int k) {
    if (getSize(numberString) < k) {
      return numberString;
    } 
    else {
      return numberString.substring(0, k);
    }
  }
}
