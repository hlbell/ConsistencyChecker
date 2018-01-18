import java.util.ArrayList;

public class Operation extends Action {
	static String operation;
	static int clusterSize;
	static int liveNodes;
	static int totalWrites;
	static int totalReads;
	static int failedWrites;
	static int failedReads;
	static ArrayList<String> values = new ArrayList<String>();
	
	public Operation(String operation){
		
		this.operation = operation;		
	}
		
	public static String getTime(){
		
		int startIndex = operation.indexOf("=");		
		int endIndex = operation.indexOf(",");
		
		String time = operation.substring(startIndex+1, endIndex+1);
		String[] value = time.split(",");

		return value[0];		
	}
	
	public static void validateOperation(){
				
		if(operation.contains("topology")){
			
			clusterSize=getLiveNodes(operation);
			 liveNodes = getLiveNodes(operation);
		}
		if(operation.contains("write")){
			if (operation.contains("ok") && (getCL(operation,clusterSize) >liveNodes)){
					
				System.out.println("Write operation failed at time: "+getTime());
				System.out.println("Expected Confidence Level: "+getCL(operation,clusterSize));
				System.out.println("Live nodes: "+liveNodes);
				failedWrites++;
			}
			if (operation.contains("fail") && (getCL(operation,clusterSize)<liveNodes)){
				
				System.out.println("Write operation failed at time :"+getTime());
				System.out.println("Expected Confidence Level: "+getCL(operation,clusterSize));
				System.out.println("Live nodes: "+liveNodes);
				failedWrites++;
			}
			
			values.add(getValue(operation));
			totalWrites++;
		}
		
		if(operation.contains("read")){
			
			if(operation.contains("ok") && (getCL(operation,clusterSize)>liveNodes)){
				
				System.out.println("Read operation failed at time: "+getTime());
				if(!values.contains(getValue(operation))){
					
					System.out.println(getValue(operation)+" not previously written.");				
				}
				
				System.out.println("Expected Confidence Level: "+getCL(operation,clusterSize));
				System.out.println("Live nodes: "+liveNodes);			
				failedReads++;	
			}
			
			if(operation.contains("fail") && (getCL(operation,clusterSize)<liveNodes)){
				
				System.out.println("Read operation failed at time: "+getTime());
				System.out.println("Expected Confidence Level: "+getCL(operation,clusterSize));
				System.out.println("Live nodes: "+liveNodes);
				failedReads++;
			}
			totalReads++;
		}
		if(operation.contains("live_nodes")){
			
			 liveNodes = getLiveNodes(operation);			
		}
	}
	
	public static String getType(){
		int startIndex = operation.indexOf("pe=");
		int endIndex = operation.indexOf(", a");	

		String type = operation.substring(startIndex, endIndex);
		String[] value = type.split("=");
		
		return value[1];		
	}
	
	public static int getReads(){
		
		return totalReads;
	}
	
	public static int getWrites(){
		
		return totalWrites;
	}
	
	public static int getFailedReads(){
		
		return failedReads;
	}
	
	public static int getFailedWrites(){
		
		return failedWrites;
	}
}
