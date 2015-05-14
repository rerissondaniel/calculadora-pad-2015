package main;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class DriverConsumer {
	Calculadora calc = new Calculadora();
	Scanner input = new Scanner(System.in);

	public DriverConsumer(Calculadora calc) {
	}

	public void menu() throws FileNotFoundException {
		int num1 = 0, num2 = 0;
		String result = "";
		char op = '.';
		while (true) {
			String[] tokens = input.nextLine().split(" ");
			num1 = Integer.parseInt(tokens[0]);
			op = tokens[1].charAt(0);
			num2 = Integer.parseInt(tokens[2]);
			if (num2 == -1)
				break;
			if (op != '+' && op != '-' && op != '*' && op != '/')
				continue;
			switch (op) {
			case '+': {
				result = String.format("%d + %d = %d ", num1, num2,
						calc.soma(num1, num2));
				break;
			}
			case '-': {
				result = String.format("%d - %d = %d ", num1, num2,
						calc.subtracao(num1, num2));
				break;
			}
			case '*': {
				result = String.format("%d * %d = %d ", num1, num2,
						calc.multiplicacao(num1, num2));
				break;
			}
			case '/': {
				if (num2 == 0) {
					result = String.format("%d + %d = %s ", num1, num2,
							"Indefinido");
				} else
					result = String.format("%d / %d = %.0f ", num1, num2,
							calc.divisao(num1, num2));
				break;
			}
			}
			System.out.println(result);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new DriverConsumer(new Calculadora()).menu();
	}
}
