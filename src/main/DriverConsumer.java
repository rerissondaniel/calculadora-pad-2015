package main;

import java.io.IOException;
import java.util.Scanner;

public class DriverConsumer {
	Calculadora calc = new Calculadora();
	FileManager in = new FileManager("feed");
	FileManager out = new FileManager("feedback");
	Scanner input;

	
	public DriverConsumer(Calculadora calc) {
		super();
		this.calc = calc;
		try {
			if(!in.getFile().exists()){
				in.getFile().createNewFile();
			}
			input = new Scanner(in.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void menu() {
		int num1 = 0, num2 = 0;
		String result = "";
		String opStr = "";
		char op = '.';
		while (true) {
			if(in.isEmpty()){
				System.out
						.println("Insira o primeiro operando (ou -1 para parar): ");
				num1 = input.nextInt();
				if (num1 == -1)
					break;
				System.out.println("Insira o operador (ou & para parar): ");
				opStr = input.next();
				op = opStr.charAt(0);
				if (op == '&')
					break;
				System.out.println("Insira o segundo operando(ou -1 para parar):");
				num2 = input.nextInt();
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
							calc.soma(num1, num2));
					break;
				}
				case '*': {
					result = String.format("%d * %d = %d ", num1, num2,
							calc.soma(num1, num2));
					break;
				}
				case '/': {
					if (num2 == 0) {
						result = String.format("%d + %d = %d ", num1, num2,
								calc.soma(num1, num2));
					} else
						result = String.format("%d / %d = %d ", num1, num2,
								calc.soma(num1, num2));
					break;
				}
				}
				in.erase();
				out.addContent(result);
			}
		}
	}

	public static void main(String[] args) {
		new DriverConsumer(new Calculadora()).menu();
	}
}