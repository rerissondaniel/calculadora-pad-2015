package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverConsumer {
	Calculadora calc = new Calculadora();
	FileManager list = new FileManager("pidList.txt");
	FileManager log = new FileManager("results.txt");
	Scanner input, pidList;

	public DriverConsumer(String listName) {
		FileManager f = new FileManager(listName);
		try {
			if (!f.getFile().exists()) {
				f.getFile().createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateSite(FileManager in, FileManager out) {
		try {
			if (!in.getFile().exists()) {
				in.getFile().createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (!out.getFile().exists()) {
				out.getFile().createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void menu() throws FileNotFoundException {
		int num1 = 0, num2 = 0;
		String result = "";
		String[] tokens;
		char op = '.';
		while (true) {
			pidList = new Scanner(list.getFile());
			ArrayList<String> pidArr = new ArrayList<String>();
			while (pidList.hasNextLine()) {
				pidArr.add(pidList.nextLine());
			}
			pidList.close();
			for (String pid : pidArr) {
				FileManager in = new FileManager("data/" + pid + "/feed.txt");
				FileManager outTemp = new FileManager("data/" + pid
						+ "/feedback.txt");
				FileManager out = new FileManager("data/" + pid
						+ "/results.txt");
				updateSite(in, out);
				if (!in.isEmpty()) {
					input = new Scanner(in.getFile());

					tokens = input.nextLine().split(" ");
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
							result = String.format("%d / %d = %s ", num1, num2,
									"Indefinido");
						} else
							result = String.format("%d / %d = %.0f ", num1,
									num2, calc.divisao(num1, num2));
						break;
					}
					}
					result += " | PID: "
							+ pid
							+ " | Hora: "
							+ new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss")
									.format(System.currentTimeMillis());
					log.addContent(result);
					outTemp.addContent(result);
					out.addContent(result);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
					input.close();
					in.erase();
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new DriverConsumer("pidList.txt").menu();
	}
}
