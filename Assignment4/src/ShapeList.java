import java.util.TreeSet;

public class ShapeList{
	TreeSet<Shape> setShapes;
	public ShapeList() {
		this.setShapes = new TreeSet<>();
	}
	
	public boolean add(Shape shape) throws Exception{
		if(setShapes.contains(shape)) {
			throw new Exception("Duplicate object");  
		}
		else {
			setShapes.add(shape);
		}
        return true;

	}
	public TreeSet<Shape2D> get2DShapes(){
		TreeSet<Shape2D> output = new TreeSet<Shape2D>();
		for(Shape shape: setShapes) {
			if(shape instanceof Shape2D && shape instanceof Shape3D == false) {
				output.add((Shape2D) shape);
			}
		}
		return output;
	}
	public TreeSet<Shape3D> get3DShapes(){
		TreeSet<Shape3D> output = new TreeSet<Shape3D>();
		for(Shape shape: setShapes) {
			if(shape instanceof Shape3D) {
				output.add((Shape3D) shape);
			}
		}
		return output;
	}
	public void printFormatted() {
		String linedash = "+------+-------------+-------+-----------------------+-------------------+";
		String columns = "| ID   | Name        | Color | Dimensions            | Description       |";
		System.out.printf("%s%n%s%n%s%n", linedash, columns, linedash);
		for(Shape shape: setShapes) {
			System.out.printf("| %-4s | %-11s | %-5s | ", shape.getId(), shape.getName(), shape.getColor());
			System.out.printf("$%-20s |", ((Shape2D) shape).getDimensions());
			System.out.printf(" %-17s | %n%s%n", shape.getDescription(), linedash);		
		}
	}
	public int getSize() {
		return setShapes.size();
	}

}
