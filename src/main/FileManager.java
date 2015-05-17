package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	private File file;

	public FileManager() {
	}

	public FileManager(String fileName) {
		this.file = new File(fileName);
		try {
			if (!file.exists()) {
				if(file.getParentFile() != null)
					file.getParentFile().mkdirs();
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void addContent(String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,
					true));
			writer.write(content + "\n");
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
		if(!getFile().exists())return true;
		return getFile().length() == 0;
	}
}
