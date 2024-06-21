package com.shivit;

public class BestTimeToBuyAndSellStock {

	public static int maxProfit(int[] prices) {
		int maxProfit = 0;
		int minPrice = Integer.MAX_VALUE;

		for (int price : prices) {
			minPrice = Math.min(minPrice, price);
			maxProfit = Math.max(maxProfit, price - minPrice);
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		int[] prices1 = { 7, 1, 5, 3, 6, 4 };
		int[] prices2 = { 7, 6, 4, 3, 1 };
		System.out.println("Max profit for prices1: " + maxProfit(prices1));
		System.out.println("Max profit for prices2: " + maxProfit(prices2));
	}

}
