package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {
	private File file;

	public FileManager() {
	}

	public FileManager(String fileName) {
		this.file = new File(fileName);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void addContent(String content) {
		try {
			SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,
					true));
			writer.write(content + " "
					+ sdt.format(new Date(System.currentTimeMillis())) + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo.");
			e.printStackTrace();
		}
	}

	public void erase() {
		try {
			file.delete();
			file.createNewFile();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}

	}

	public boolean isEmpty() {
		return getFile().length() == 0;
	}
}
