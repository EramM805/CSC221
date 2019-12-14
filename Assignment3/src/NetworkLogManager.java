import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NetworkLogManager{
	private ArrayList<LogEntry> listLogEntries;
	public NetworkLogManager(){
		listLogEntries = new ArrayList<LogEntry>();
	}
	public Boolean loadFile(String fileName){
        File text = new File(fileName);
        Scanner scan;
		try {
			scan = new Scanner(text);
			while(scan.hasNextLine()){
	            String line = scan.nextLine();
	            String[] input = line.split(",");
	            try {
	         	   LogEntry log = new LogEntry(input[0], input[1], input[2], input[3], input[4], input[5], input[6]);
	         	   listLogEntries.add(log);
	            }catch(Exception e){
	            	System.out.printf("Skipping line: %s%n", line);
	            }
	        }
			scan.close();
			return true;
		} catch (FileNotFoundException e1) {
			return false;
			
		}
        
	}
	public String toString(){
		return("NetworkLogManager: there are " + listLogEntries.size() + " records");
	}
	public LogEntry searchById(String search){
		return CommonSearch(search, "id");
	}
	public ArrayList<LogEntry> searchByRange(String start, String end){
		ArrayList<LogEntry> output = new ArrayList<LogEntry>();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyy HH:mm:ss");
		Date dateStart;
		Date dateEnd;
		try {
			dateStart = formatter.parse(start);
			dateEnd = formatter.parse(end);
			for(LogEntry log: listLogEntries){
	    		try{
	    			Date dateLog =  formatter.parse(log.getTimeStamp());
	            	if(dateLog.after(dateStart) && dateLog.before(dateEnd) || (dateLog.equals(dateStart) || (dateLog.equals(dateEnd)))) {
	            		output.add(log);
	            	}
	    		}
	    		catch (ParseException e) {
	    			e.printStackTrace();
	    		}

	        	
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}
	public LogEntry searchBySource(String search){
		return CommonSearch(search, "source");
	}
	public LogEntry searchByDestination(String search){
		return CommonSearch(search, "destination");
	}
	public LogEntry searchByProtocol(String search){
		return CommonSearch(search, "protocol");
	}
	public LogEntry searchByLength(String search){
		return CommonSearch(search, "length");
	}
	public LogEntry searchByDescription(String search){
		return CommonSearch(search, "description");
	}
	private LogEntry CommonSearch(String search, String criteria) {
		for(LogEntry log: listLogEntries){
			String compareTo = "";
			switch (criteria) { 
		        case "id": 
		        	compareTo = log.getId();
		            break; 
		        case "source": 
		        	compareTo = log.getSource();
		            break; 
		        case "length": 
		        	compareTo = log.getLength();
		            break; 
		        case "protocol": 
		        	compareTo = log.getProtocol();
		            break; 
		        case "description": 
		        	compareTo = log.getDescription();
		            break; 
		        case "destination": 
		        	compareTo = log.getDestination();
		            break; 
		        
			}
			if(compareTo.equals(search)) {
				return log;
			}
		}
		return null;
	}
  } 

