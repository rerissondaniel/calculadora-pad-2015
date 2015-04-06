package main;
import java.util.Scanner;

public class Driver {
	Scanner input = new Scanner(System.in);
	Calculadora calc = new Calculadora();

	public void menu() {
		int num1 = 0, num2 = 0;
		double result = 0;
		String opStr = "";
		char op = '.';
		while (true) {
			System.out.println("Insira o primeiro operando: ");
			num1 = input.nextInt();
			System.out.println("Insira o operador: ");
			opStr = input.next();
			op = opStr.charAt(0);
			System.out.println("Insira o segundo operando:");
			num2 = input.nextInt();
			if (op != '+' && op != '-' && op != '*' && op != '/')
				continue;
			switch (op) {
			case '+': {
				result = calc.soma(num1, num2);
				break;
			}
			case '-': {
				result = calc.subtracao(num1, num2);
				break;
			}
			case '*': {
				result = calc.multiplicacao(num1, num2);
				break;
			}
			case '/': {
				if (num2 == 0) {
					System.out.println("Indefinido");
					continue;
				}
				result = calc.divisao(num1, num2);
				break;
			}
			}
			System.out.println("Resultado é: " + result);
		}
	}

	public static void main(String[] args) {
		new Driver().menu();
	}
}