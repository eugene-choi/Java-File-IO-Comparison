import java.io.*;
import java.util.Scanner;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileManager {

	// 1.
	// Method for testing FileInputStream.
	public static long testInputStreamSpeed(File file) throws IOException {
		Timer.start();
	    FileInputStream input = new FileInputStream(file);
	    while(input.read() != -1) {}
	    return Timer.stop();
	}
	
	// 2.
	// Method for testing BufferedReader.
	public static long testBufferedReader(File file) throws IOException {
        Timer.start();
        BufferedReader input = new BufferedReader(new FileReader(file));
        while(input.read() != -1) {}
        return Timer.stop();
	}
	
	// 3.
	// Method for testing BufferedInputStream.
	public static long testBufferedStream(File file) throws IOException {
        Timer.start();
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
        while(input.read() != -1) {}
        return Timer.stop();
	}
	
	// 4.
	// Method for testing Scanner.
	public static long testScanner(File file) throws IOException {
		Timer.start();
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext()) scanner.next();
		return Timer.stop();
	}
	
	
}
