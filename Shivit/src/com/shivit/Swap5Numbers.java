package com.shivit;

import java.util.Scanner;

public class Swap5Numbers {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the value of a: ");
		int a = scanner.nextInt();

		System.out.print("Enter the value of b: ");
		int b = scanner.nextInt();

		System.out.print("Enter the value of c: ");
		int c = scanner.nextInt();

		System.out.print("Enter the value of d: ");
		int d = scanner.nextInt();

		System.out.print("Enter the value of e: ");
		int e = scanner.nextInt();
		a = a + c;
		c = a - c;
		a = a - c;

		b = b + e;
		e = b - e;
		b = b - e;

		d = d + c;
		c = d - c;
		d = d - c;
		System.out.println("\nOutput : ");
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		System.out.println("d = " + d);
		System.out.println("e = " + e);
		scanner.close();
	}
}
