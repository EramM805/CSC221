import java.awt.Color;

public class Quadrilateral3D extends Shape3D{

	public Quadrilateral3D(int id, String name, String description, Color color, double height, double width,
			double length) {
		super(id, name, description, color, height, width, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return this.width*this.height;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 2*(this.width*this.height+this.width*this.length+this.length*this.height);
	}

	
}
