import java.awt.Color;

public abstract class Shape implements Comparable<Shape>{
	private final int id;
	private final String name;
	private final String description;
	private Color color;
	public Shape(int id, String name, String description, Color color){
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
	}
	public abstract double area();
	public abstract double perimeter();
	public String toString() {
		return this.id + " " + this.name + " " + this.description + " " + this.getColor();
	}
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}

	public String getColor() {
		if(this.color.equals(Color.RED))
	        return "Red";
	    else if(this.color.equals(Color.BLUE))
	        return "Blue";
	    else if(this.color.equals(Color.YELLOW))
	        return "Yellow";
	    else if(this.color.equals(Color.GREEN))
	        return "Green";
	    else if(this.color.equals(Color.BLACK))
	        return "Black";
	    else if(this.color.equals(Color.WHITE))
	        return "White";
	    else
	        return "Unknown Color";


	}
}
