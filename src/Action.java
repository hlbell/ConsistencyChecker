
public class Action {
	
	public static int getCL(String operation,int clusterSize){
		
		if(operation.contains("ONE")){		
			return 1;			
		}
		
		if(operation.contains("QUORUM")){
			
			return (int) Math.floor((clusterSize/2)+1);			
		}
		
		if(operation.contains("ALL")){
			
			return clusterSize;
		}		
		return clusterSize;		
	}
	
	public static  String getValue(String operation){
		int startIndex = operation.indexOf("={");
		int endIndex = operation.indexOf("}");

		String action = operation.substring(startIndex+2, endIndex);		
		String[] value = action.split(", ");		
		
//		System.out.println(value[value.length-1]);
		return value[value.length-1];	
	}
	
	public static int getLiveNodes(String operation){	
		int startIndex = operation.indexOf("={");
		int endIndex = operation.indexOf("}");
		
		String action = operation.substring(startIndex+2, endIndex);
		String[] value = action.split(",");
		
		return Integer.parseInt(value[1].replaceAll("\\s+","" ));
	}
}
