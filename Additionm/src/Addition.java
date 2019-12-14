import java.util.Scanner;
// import java.util.*; -> This gives us the classes in util but not the other packages. This will give us the scanner
//  import java.*; -> This will not give us scanner

public class Addition {
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter 1st integer: "); // this takes as string and converts to int for us
		int number1 = input.nextInt();
		System.out.print("Enter 2nd integer: ");
		int number2 = input.nextInt();
		System.out.print("Enter 3rd integer: ");
		int number3 = input.nextInt();
		int sum = number1 + number2 + number3;
		System.out.println("The sum is " + sum);
		input.close(); // Remember to have this for the project

	}

}
