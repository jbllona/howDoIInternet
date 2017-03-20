import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class GreetingServer extends Thread
{
	String systemCommandString = "###SYSTEM::";
	String systemCommandPatternString = "(#{3})(SYSTEM::)";
	Pattern systemCommandPattern;
	private ServerSocket serverSocket;
	Hashtable<String, String> activeUsers;
	
	public GreetingServer(int port) throws IOException
	{
		activeUsers = new Hashtable<String, String>();
		systemCommandPattern = Pattern.compile(systemCommandPatternString);
		serverSocket = new ServerSocket(port);
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				
				Socket server = serverSocket.accept();
				DataInputStream in = new DataInputStream(server.getInputStream());
				String recievedMessage = in.readUTF();
				System.out.println("Client: " + recievedMessage);
				
				Socket client = null;
				Scanner input = new Scanner(System.in).useDelimiter("\n");
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				
				Matcher m = systemCommandPattern.matcher(recievedMessage);
				if(m.find())
				{
					out.writeUTF("was a command");
					processSystemCommand(recievedMessage.substring(11));
				}
				else
				{
					out.writeUTF("was a message");
				}
				
				// server.close();
			}
		}
		catch(SocketTimeoutException s)
		{
			System.out.println("Socket timed out");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		// server.close();
	}
	
	public void processSystemCommand(String command)
	{
		
	}
	
	public static void main(String[] args)
	{
		int port = Integer.parseInt(args[0]);
		int timeOutMs = 0;
		
		Thread t = null;
		
		try
		{

			t = new GreetingServer(port);
			t.start();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
