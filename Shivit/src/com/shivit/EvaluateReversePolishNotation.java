package com.shivit;

import java.util.Stack;

public class EvaluateReversePolishNotation {

	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();

		for (String token : tokens) {
			if (isOperator(token)) {
				int b = stack.pop();
				int a = stack.pop();
				int result = evaluate(a, b, token);
				stack.push(result);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}

		return stack.pop();
	}

	private boolean isOperator(String token) {
		return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
	}

	private int evaluate(int a, int b, String operator) {
		switch (operator) {
		case "+":
			return a + b;
		case "-":
			return a - b;
		case "*":
			return a * b;
		case "/":
			return a / b;
		default:
			throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}

	public static void main(String[] args) {
		EvaluateReversePolishNotation erpn = new EvaluateReversePolishNotation();

		String[] tokens1 = { "2", "1", "+", "3", "*" };
		System.out.println(erpn.evalRPN(tokens1));

		String[] tokens2 = { "4", "13", "5", "/", "+" };
		System.out.println(erpn.evalRPN(tokens2));

		String[] tokens3 = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };
		System.out.println(erpn.evalRPN(tokens3));
	}

}
