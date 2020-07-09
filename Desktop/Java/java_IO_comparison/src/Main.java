import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Main {
	public static void main(String[] args) throws IOException {
		
		System.out.println("Program started: now testing performances of Java IO functions");
		File[] fileList = new File[3];
		
		System.out.println("\n>>>>>>>>>> Generating dummy files of sizes 1MB, 10MB, 100MB...");
		fileList[0] = DummyFileGenerator.makeFile(DummyFileGenerator.input1MB, DummyFileGenerator.size1MB);
		fileList[1] = DummyFileGenerator.makeFile(DummyFileGenerator.input10MB, DummyFileGenerator.size10MB);
		fileList[2] = DummyFileGenerator.makeFile(DummyFileGenerator.input100MB, DummyFileGenerator.size100MB);
		System.out.println("- SUCCESS: generating 3 dummy files of sizes 1MB, 10MB, 100MB complete!");
		
		
		String[] io = {"FileInputStream", "BufferedReader", "BufferedInputStream", "Scanner"};
		String[] size = {"1MB", "10MB", "100MB"};
		
		long[][] results = new long[5][3];
		
		System.out.println("\n>>>>>>>>>> Testing performances of FileInputStream function...");
		for(int i = 0; i  < 3; i++) {
			results[0][i] = FileManager.testInputStreamSpeed(fileList[i]);
			System.out.format("(%d) Input performance of file size %dMB: %d MS\n", (i + 1), (int) Math.pow(10, i), results[0][i]);
		}
		System.out.println("- SUCCESS: complete!");
		
		System.out.println("\n>>>>>>>>>> Testing performances of BufferedReader function..."); 
		for(int i = 0; i  < 3; i++) {
			results[1][i] = FileManager.testBufferedReader(fileList[i]);
			System.out.format("(%d) Input performance of file size %dMB: %d MS\n", (i + 1), (int) Math.pow(10, i), results[1][i]);
		}
		System.out.println("- SUCCESS: complete!");
		
		System.out.println("\n>>>>>>>>>> Testing performances of BufferedInputStream function...");
		for(int i = 0; i  < 3; i++) {
			results[2][i] = FileManager.testBufferedStream(fileList[i]);
			System.out.format("(%d) Input performance of file size %dMB: %d MS\n", (i + 1), (int) Math.pow(10, i), results[2][i]);
		}
		System.out.println("- SUCCESS: complete!");
		
		System.out.println("\n>>>>>>>>>> Testing performances of Scanner function...");
		for(int i = 0; i  < 3; i++) {
			results[3][i] = FileManager.testScanner(fileList[i]);
			System.out.format("(%d) Input performance of file size %dMB: %d MS\n", (i + 1), (int) Math.pow(10, i), results[3][i]);
		}
		System.out.println("- SUCCESS: complete!");
		
		System.out.println("\n>>>>>>>>>> Generating results as csv file...\n");
		printToCSV(new File("./"), "results.csv", io, size, results);
		
		
		String[][] output = new String[3][4];
		TreeMap<Long, String> minHeap = new TreeMap<Long, String>();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) minHeap.put(results[j][i], io[j]);
			int j = 0;
			while(!minHeap.isEmpty()) {
				Entry<Long, String> temp = minHeap.pollFirstEntry();
				output[i][j++] = String.format("%d MS (%s)", temp.getKey(), temp.getValue());
			}
		}
		System.out.println("\n>>>>>>>>>> Generating sorted data as csv file...");
		printToCSVRankings(new File("./"), "sorted.csv", size, output);
	}
	
	public static void printToCSV(File csvLocation, String fileName, 
			String[] metaRow, String[] metaCol, long[][] results) throws IOException, FileNotFoundException {
		File csv = new File(csvLocation, fileName);
		
		try(FileWriter writer = new FileWriter(csv);
			BufferedWriter bw = new BufferedWriter(writer)) {
			
			int rows = metaRow.length;
			int cols = metaCol.length;

			// Headers:
			for(int i = 0; i <= cols; i++) {
				if(i != 0) bw.write(metaCol[i - 1]);
				bw.write((i == cols) ? "\n" : ",");
			}
			
			// Values:
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j <= cols; j++) {
					if(j == 0) bw.write(metaRow[i]);
					else bw.write(Long.toString(results[i][j - 1]) + " MS");
					bw.write((j == (cols)) ? "\n" : ",");
				}
			}
			System.out.format("- SUCCESS: csv file at\nAddress: %s\nName: %s\n", csv.getAbsolutePath(), csv.getName());
 		}
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}
	
	public static void printToCSVRankings(File csvLocation, String fileName, 
			String[] header, String[][] results) throws IOException, FileNotFoundException {
		File csv = new File(csvLocation, fileName);
		
		try(FileWriter writer = new FileWriter(csv);
			BufferedWriter bw = new BufferedWriter(writer)) {
			
			int cols = results[0].length;
			int rows = header.length;
			
			// Values:
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j <= cols; j++) {
					if(j == 0) bw.write(header[i]);
					else bw.write(results[i][j - 1]);
					bw.write((j == (cols)) ? "\n" : ",");
				}
			}
			System.out.format("- SUCCESS: csv file at\nAddress: %s\nName: %s\n", csv.getAbsolutePath(), csv.getName());
		}
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
	}
	

}
