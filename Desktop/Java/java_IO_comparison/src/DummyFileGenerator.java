import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DummyFileGenerator {
	static long size1MB = (1L << 20);
    static long size10MB = (1L << 20) * 10;
    static long size100MB = (1L << 20) * 100;
    static long size1000MB = (1L << 20) * 1000;
    static byte oneByte = 1;
    static String input1MB = "input1MB.txt";
    static String input10MB = "input10MB.txt";
    static String input100MB = "input100MB.txt";
    static String input1000MB = "input1000MB.txt";
    
    /*
	 * Method for generating files for testing. Takes file name and file size (in MB)
	 * as parameters and generates a dummy file in the project folder.
	 */
	static File makeFile(String fileName, long sizeInMB) throws IOException {
		File newFile = new File(fileName);
		newFile.createNewFile();
		RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
		raf.setLength(sizeInMB);
		raf.close();
		return newFile;
	}
    
    static void getDummyFile(String fileName, long fileSize) throws IOException {
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fileName));
		for(int i = 0; i < fileSize; i++) output.write(oneByte);
		output.flush();
		output.close();    	
    }
}
