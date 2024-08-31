/*
 * Name: Credit Card Validator
 * Author: Leah Boalich
 * Date: August 29, 2024
 * Assignment: Module 1 Chapter 6 Exercise 31
 * Description: This program takes a string number input from the user and uses the Luhn check to validate if the number is a valid credit card number.
 */

/** Import Scanner */ 
import java.util.Scanner;

/** Create credit card class*/
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
    //Checks if card is visa
    boolean isVisa = prefixMatched(numberString, 4);
    //Checks if card is master card
    boolean isMater = prefixMatched(numberString, 5);
    //Checks if card is american express
    boolean isAmExpress = prefixMatched(numberString, 37);
    //Checks if card is discover
    boolean isDiscover = prefixMatched(numberString, 6);
    //Returns true if card is any one of the cards
    return (isVisa || isMater || isAmExpress || isDiscover);
  }

  /** Return string of single digit numbers: Step One */
  public static String singleDigitNumbers(String numberString) {
    //Create string of sigle digits
    String singleDigit = "";
    //Loop through number string to get single digits and add to single digit string
    int i;
    for (i = getSize(numberString) - 2; i >= 0; i -= 2) {
      //Get current digit and convert to int
      int digit = Character.getNumericValue(numberString.charAt(i));
      //Double the digit
      int doubledDigit = digit * 2;
      //If digit not single add digits together
      singleDigit += Integer.toString(getDigit(doubledDigit));
    }
    //Return the string of single digits
    return singleDigit;
  }

  /** Get the result from Step 2 */
  public static int sumOfDoubleEvenPlace(String numberString) {
    //Initialize sum
    int sum = 0;
    //Loop through single digit string and add digits to sum
    int i;
    for (i = 0; i < getSize(numberString); i++) {
      //Get digit at current index, convert to number, add to sum
      sum += Character.getNumericValue(numberString.charAt(i));
    }
    //Return sum
    return sum;
  }

  /** Return this number if it is a single digit, otherwise,
   * return the sum of the two digits */
  public static int getDigit(int number) {
    //If number is single digit, return the number
    if (number < 10) {
      return number;
    }
    //If not a single digit add the digits together
    else {
      //Convert number to string
      String stringNumber = Integer.toString(number);
      //Initialze variable to hold the sum of the digits
      int newNumber = 0;
      //Loop over the digits and add to newNumber
      int i;
      for (i = 0; i < stringNumber.length(); i++) {
        //Get digit at current index and convert to number then add to newNumber
        newNumber += Character.getNumericValue((stringNumber.charAt(i)));
      }
      //Return the new single digit number
      return newNumber;
    }
  }

  /** Return sum of odd-place digits in number */
  public static int sumOfOddPlace(String numberString) {
    //Initialize sum
    int sum = 0;
    //Loop through number and add digits to sum
    int i;
    for (i = getSize(numberString) - 1; i >= 0; i -= 2) {
      //Get digit at current index, convert to number, add to sum
      sum += Character.getNumericValue(numberString.charAt(i));
    }
    //Return sum
    return sum;
  }

  /** Return true if the number d is a prefix for number */
  public static boolean prefixMatched(String numberString, int d) {
    //Convert d to string
    String dString = Integer.toString(d);
    //Get prefix of number string the same length as d
    String prefix = getPrefix(numberString, getSize(dString));
    //Convert prefix to integer
    int prefixInt = Integer.parseInt(prefix);
    //Return if prefix is equal to d
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
      //If number string less than k return the number string
      return numberString;
    } 
    else {
      //If number string greater than or equeal to k, return the prefix of number string same length of k
      return numberString.substring(0, k);
    }
  }
}
