import java.util.Scanner;


public class TestPainting {
	public static void main(String[] args) {
		Painting testPaintingOne = new Painting();
		testPaintingOne.setArtistName("Mark Rothko");
		testPaintingOne.setName("No. 6 (Violet, Green and Red)");
		testPaintingOne.setPrice(186000000);
		testPaintingOne.setYear(1951);
		Painting testPaintingTwo = new Painting();
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the artist's name: ");
		String artistName = scan.nextLine();
		System.out.print("Please enter the name of the painting: ");
		String name = scan.nextLine();
		System.out.print("Please enter the price of the painting: ");
		double price = scan.nextDouble();
		System.out.print("Please enter the year the painting was created: ");
		int year = scan.nextInt();
		testPaintingTwo.setArtistName(artistName);
		testPaintingTwo.setName(name);
		testPaintingTwo.setPrice(price);
		testPaintingTwo.setYear(year);
		scan.close();	
		String strFormat = "%24s: %s%n";
		String numFormat = "%24s: %,9.2f%n";
		String discountFormat = "%24s: %,9.2f %s %,9.2f %n";
		System.out.printf(strFormat, "Artist Name",testPaintingTwo.getArtistName());
		System.out.printf(strFormat, "Name",testPaintingTwo.getName());
		System.out.printf(numFormat, "Price",testPaintingTwo.getPrice());
		System.out.printf(strFormat, "Year",testPaintingTwo.getYear());
		System.out.printf(strFormat, "Age",testPaintingTwo.getAge());
		System.out.printf(discountFormat, "Discounted Price Range",  testPaintingTwo.getMinimumDiscountPrice(),"-",testPaintingTwo.getMaximumDiscountPrice());
		
	}
}
