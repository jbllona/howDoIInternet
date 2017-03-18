import java.util.Scanner;

public class WelcomScreen
{
	public static UserInfo desplay()
	{
		Scanner input = new Scanner(System.in).useDelimiter("\n");
		System.out.print("Welcom!\nEnter your desired display name:");
		String displayName = input.next();
		System.out.print("Port: (usually 6066): ");
		int port = Integer.parseInt(input.next());
		System.out.print("Server address: ");
		String address = input.next();
		return new UserInfo(displayName, port, address);
	}
}
class UserInfo
{
	static String name;
	static int port;
	static String address;
	UserInfo(String n, int p, String a)
	{
		name = n;
		port = p;
		address = a;
	}
}
