import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
		Account account = new Account();
		String name;
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter name: ");

		name = input.next();
		account.setName(name);

		System.out.println(account.getName());

	}
}
