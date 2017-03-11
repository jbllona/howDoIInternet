import java.net.*;
import java.io.*;
import java.util.Scanner;


class WaitForInput extends Thread
{
	Scanner input;
	Thread serverThread;
	public WaitForInput(int port, int timeOut)
	{
		input = new Scanner(System.in);
		
		serverThread = null;
		try
		{
			serverThread = new GreetingServer(port, timeOut);
			serverThread.start();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public WaitForInput(int port)
	{
		input = new Scanner(System.in);
		
		serverThread = null;
		try
		{
			serverThread = new GreetingServer(port);
			serverThread.start();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void run()
	{
		String userCommand = input.next();
		if(userCommand.equalsIgnoreCase("exit"))
		{
			System.out.println("attempting to exit");
			serverThread.stop();
			System.exit(0);
		}
	}
}

public class GreetingServer extends Thread
{
	String msgToSend = "I don't know why you say goodbye I say hello!";
	private ServerSocket serverSocket;
	
	
	public GreetingServer(int port, int timeOut) throws IOException
	{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(timeOut);
	}
	
	public GreetingServer(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
	}
	
	public void run()
	{
		try
		{
			System.out.println("You (to self): Wondering if after all these years I'll meet the client port");
			Socket server = serverSocket.accept();
			DataInputStream in = new DataInputStream(server.getInputStream());
			System.out.println("Client: " + in.readUTF());
			
			System.out.println("You: " + msgToSend);
			DataOutputStream out = new DataOutputStream(server.getOutputStream());
			out.writeUTF(msgToSend);
			
			server.close();
		}
		catch(SocketTimeoutException s)
		{
			System.out.println("Socket timed out");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		int port = Integer.parseInt(args[0]);
		int timeOutMs = 0;
		if(args.length > 1)
		{
				timeOutMs = Integer.parseInt(args[1]);
		}
		
		try
		{
			Thread t = new WaitForInput(port);
			t.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
