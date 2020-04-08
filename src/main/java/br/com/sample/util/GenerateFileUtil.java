package br.com.sample.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GenerateFileUtil {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		byte[] buffer = new Teste().getText().getBytes();
		int number_of_lines = 40000;
	
		FileChannel rwChannel;
		try {
			rwChannel = new RandomAccessFile("textfile.txt", "rw").getChannel();
			ByteBuffer wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, buffer.length * number_of_lines);
			for (int i = 0; i < number_of_lines; i++) {
			    wrBuf.put(buffer);
			}
			rwChannel.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
