package com.shivit;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountNumbersOfWords {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String inputString = scanner.nextLine();

		Map<String, Integer> wordCounts = countWords(inputString);

		System.out.println("Word counts:");
		for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		scanner.close();
	}

	public static Map<String, Integer> countWords(String inputString) {
		Map<String, Integer> wordCounts = new HashMap<>();

		String[] words = inputString.split("\\s+");

		for (String word : words) {
			wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
		}

		return wordCounts;
	}
}
