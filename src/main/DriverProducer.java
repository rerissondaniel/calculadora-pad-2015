package main;

import java.util.Random;

public class DriverProducer {

	public void produce(int numProductions) {
		System.out.println("anygeneration");
		Random generator = new Random();
		int num1, num2, op;
		for (int i = 0; i < numProductions; i++) {
			do
				num1 = generator.nextInt();
			while (num1 == -1);
			do
				num2 = generator.nextInt();
			while (num2 == -1);
			op = generator.nextInt(4);
			switch (op) {
				case 0:
					System.out.println(String.format("%d %c %d", num1, '+', num2));
					break;
				case 1:
					System.out.println(String.format("%d %c %d", num1, '-', num2));
					break;
				case 2:
					System.out.println(String.format("%d %c %d", num1, '*', num2));
					break;
				case 3:
					System.out.println(String.format("%d %c %d", num1, '/', num2));
					break;
			}
		}
	}

	public static void main(String[] args) {
		new DriverProducer().produce(10);
	}
}
