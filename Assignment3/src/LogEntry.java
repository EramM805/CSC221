
public class LogEntry {
	private String id;
	private String timestamp;
	private String source;
	private String destination;
	private String protocol;
	private String length;
	private String description;
	public LogEntry(String id, String timestamp, String source, String destination, String protocol, String length, String description) {
		if(timestamp.length() == 0 || timestamp == null || source.length() == 0 || source == null || destination.length() == 0 || destination == null || protocol.length() == 0 || protocol == null || protocol.equals("-")|| length.length() == 0 || length == null || length.equals("0") || description.length() == 0 || description == null) {
			throw new IllegalArgumentException();
		}
		try{  
		   Double.parseDouble(id);  
		 }catch(NumberFormatException e){  
			  throw new IllegalArgumentException();
		  }  
		this.id = id;
		this.timestamp = timestamp;
		this.source = source;
		this.destination = destination;
		this.protocol = protocol;
		this.length = length;
		this.description = description;
	}
	public String toString(){
		String output = this.id + "," + this.timestamp + "," + this.source + "," + this.destination + "," + this.protocol + "," + this.length + "," + this.description;
		return output;
	}
	public String getId(){
		return this.id;
	}
	public String getTimeStamp(){
		return this.timestamp;
	}
	public String getSource(){
		return this.source;
	}
	public String getDestination(){
		return this.destination;
	}
	public String getProtocol(){
		return this.protocol;
	}
	public String getLength(){
		return this.length;
	}
	public String getDescription(){
		return this.description;
	}

	
}
