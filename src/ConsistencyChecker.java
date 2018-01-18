import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsistencyChecker {
	
	public static void main(String[] args) throws IOException {
		File folder = new File("..//ConsistencyChecker//src//histories");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles ){
			if(file.getName().contains(".txt")){
				
				System.out.println("Validation for history file: "+file.getName());
				System.out.println("**************************************");
				System.out.println();
							
				FileInputStream fstream = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String operation;
				
				while((operation = br.readLine()) != null){
					new Operation(operation);
					Operation.validateOperation();							
				}				
				System.out.println();				
				br.close();				
			}
		}
		System.out.println("SUMMARY");
		System.out.println("*******");
		
		System.out.println("Total Writes: "+Operation.getWrites());
		System.out.println("Total Reads: "+Operation.getReads());
		
		System.out.println("Total Failed Writes: "+Operation.getFailedWrites());
		System.out.println("Total Failed Reads: "+Operation.getFailedReads());
	}
}