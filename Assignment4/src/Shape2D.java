import java.awt.Color;

public abstract class Shape2D extends Shape{
	public final double height;
	public final double width;
	public Shape2D(int id, String name, String description, Color color, double height, double width) {
		super(id, name, description, color);
		this.height = height;
		this.width = width;
	}

	public String getDimensions() {
		return height + ":" + width;
	}
	@Override
	public int compareTo(Shape shape) {
		if(shape instanceof Shape2D == false) {
			return -1;
		}
		Shape2D shape1 = (Shape2D)shape;
		if (this.getName() == shape.getName() && this.height == shape1.height && this.width == shape1.width) {
			return 0;
		
		}
		return -1;
	
	}
	public String toString() {
		return super.toString() + " " + this.height + " " + this.width;
	}

}
