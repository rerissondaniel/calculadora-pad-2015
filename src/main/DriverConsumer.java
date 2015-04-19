package main;
import java.util.Scanner;

public class DriverConsumer {
	Scanner input = new Scanner(System.in);
	Calculadora calc = new Calculadora();
	FileManager fm = new FileManager();
	public void menu() {
		System.out.println("Insira o nome do arquivo onde serão guardadas as operações:");
		String fileName = input.nextLine();
		fm = new FileManager(fileName);
		int num1 = 0, num2 = 0;
		String result = "";
		String opStr = "";
		char op = '.';
		while (true) {
			System.out.println("Insira o primeiro operando (ou -1 para parar): ");
			num1 = input.nextInt();
			if(num1 == -1)break;
			System.out.println("Insira o operador (ou & para parar): ");
			opStr = input.next();
			op = opStr.charAt(0);
			if(op == '&')break;
			System.out.println("Insira o segundo operando(ou -1 para parar):");
			num2 = input.nextInt();
			if(num2 == -1)break;
			if (op != '+' && op != '-' && op != '*' && op != '/')
				continue;
			switch (op) {
			case '+': {
				result = String.format("%d + %d = %d ", num1, num2, calc.soma(num1, num2));
				break;
			}
			case '-': {
				result = String.format("%d - %d = %d ", num1, num2, calc.soma(num1, num2));
				break;
			}
			case '*': {
				result = String.format("%d * %d = %d ", num1, num2, calc.soma(num1, num2));
				break;
			}
			case '/': {
				if (num2 == 0) {
					result = String.format("%d + %d = %d ", num1, num2, calc.soma(num1, num2));
				}else
					result = String.format("%d / %d = %d ", num1, num2, calc.soma(num1, num2));
				break;
			}
			}
			fm.addContent(result);
		}
	}

	public static void main(String[] args) {
		new DriverConsumer().menu();
	}
}