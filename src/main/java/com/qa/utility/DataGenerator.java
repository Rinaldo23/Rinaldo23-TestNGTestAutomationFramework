package com.qa.utility;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Utility class for generating random data such as text, email, phone numbers,
 * passwords, and numbers.
 */
public class DataGenerator {

	/**
	 * Generates a random string of alphanumeric characters.
	 *
	 * @return Randomly generated string
	 */
	public String generateRandomString() {
		int length = 8; // Length of the generated string
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder name = new StringBuilder();
		Random random = new Random();

		// Build the random string character by character
		for (int i = 0; i < length; i++) {
			name.append(characters.charAt(random.nextInt(characters.length())));
		}

		return name.toString();
	}

	/**
	 * Generates a random email address.
	 *
	 * @return Randomly generated email address
	 */
	public String generateRandomEmail() {
		return generateRandomString() + "@gmail.com";
	}

	/**
	 * Generates a random 10-digit phone number.
	 *
	 * @return Randomly generated phone number
	 */
	public String generateRandomNumber() {
		String digits = "0123456789";
		StringBuilder number = new StringBuilder();
		Random random = new Random();

		// Ensure the first digit is non-zero to get a valid 10-digit number
		number.append(digits.charAt(random.nextInt(digits.length() - 1) + 1));

		// Generate the remaining 9 digits randomly
		for (int i = 1; i < 10; i++) {
			number.append(digits.charAt(random.nextInt(digits.length())));
		}

		return number.toString();
	}

	/**
	 * Generates a random password with at least one uppercase letter, one lowercase
	 * letter, one special character, and one digit.
	 *
	 * @return Randomly generated password
	 */
	public String generateRandomPassword() {
		// Characters to be used in the password
		String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
		String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?";
		String numericChars = "0123456789";

		SecureRandom random = new SecureRandom();

		// List to hold the characters of the password
		List<Character> passwordChars = new ArrayList<>();

		// Ensure at least one character from each character set is included
		passwordChars.add(upperCaseChars.charAt(random.nextInt(upperCaseChars.length())));
		passwordChars.add(lowerCaseChars.charAt(random.nextInt(lowerCaseChars.length())));
		passwordChars.add(specialChars.charAt(random.nextInt(specialChars.length())));
		passwordChars.add(numericChars.charAt(random.nextInt(numericChars.length())));

		// Fill the rest of the password length with random characters from all sets
		String allChars = upperCaseChars + lowerCaseChars + specialChars + numericChars;
		for (int i = 4; i < 8; i++) {
			passwordChars.add(allChars.charAt(random.nextInt(allChars.length())));
		}

		// Shuffle the list to ensure randomness
		Collections.shuffle(passwordChars);

		// Convert the list to a string
		StringBuilder password = new StringBuilder();
		for (char c : passwordChars) {
			password.append(c);
		}

		return password.toString();
	}

	/**
	 * Generates a random integer within a specified range.
	 *
	 * @param startIndex The inclusive start of the range
	 * @param endIndex   The exclusive end of the range
	 * @return Randomly generated integer within the specified range
	 * @throws IllegalArgumentException if startIndex is greater than or equal to
	 *                                  endIndex
	 */
	public int generateRandomNumber(int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			throw new IllegalArgumentException("Start index must be less than end index");
		}

		Random random = new Random();
		// Generate a random number between startIndex (inclusive) and endIndex
		// (exclusive)
		return random.nextInt(endIndex - startIndex) + startIndex;
	}
}