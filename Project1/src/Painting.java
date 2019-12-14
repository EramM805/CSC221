import java.util.GregorianCalendar;

public class Painting{
	private String artistName;
	private String name;
	private double price;
	private int year;
	public Painting(){
		this.artistName = "-";
		this.name = "-";
		this.price = 0.0;
		this.year = 0;
	}
	public Painting(String artistName, String name, double price, int year){
		this.artistName = artistName;
		this.name = name;
		this.price = price;
		this.year = year;
	}
	public String getArtistName(){
		return this.artistName;
	}
	public String getName(){
		return this.name;
	}
	public double getPrice(){
		return this.price;
	}
	public int getYear(){
		return this.year;
	}
	public void setArtistName(String artistName){
		this.artistName = artistName;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setYear(int year){
		this.year = year;
	}
	public double getMinimumDiscountPrice(){
		return this.price - this.price*.15;
	}
	public double getMaximumDiscountPrice(){
		return this.price - this.price*.10;
	}
	public int getAge(){
		GregorianCalendar gcal = new GregorianCalendar(); 
		return gcal.getWeekYear() - this.year;
	}
}
