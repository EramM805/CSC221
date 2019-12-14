import java.awt.Color;

public abstract class Shape3D extends Shape2D {
	public final double length;
	public Shape3D(int id, String name, String description, Color color, double height, double width, double length) {
		super(id, name, description, color, height, width);
		this.length = length;
	}
	@Override
	public String getDimensions() {
		return this.height + ":" + this.width+ ":" + this.length;
	}
	@Override
	public int compareTo(Shape shape) {
		if(shape instanceof Shape3D == false) {
			return -1;
		}
		Shape3D shape1 = (Shape3D)shape;
		if (this.getName().equals(shape.getName()) && this.height == shape1.height && this.width == shape1.width && this.length == shape1.length) {
			return 0;
		
		}
		
		return -1;
	}
	public String toString() {
		return super.toString() + " " + this.length;
	}

}
