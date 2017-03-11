import java.net.*;
import java.io.*;

public class GreetingServer extends Thread
{
	String msgToSend = "I don't know why you say goodbye I say hello!";
	private ServerSocket serverSocket;
	
	public GreetingServer(int port, int timeOut) throws IOException
	{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(timeOut);
	}
	
	public void run()
	{
		while(true)
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
				break;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args)
	{
		int port = Integer.parseInt(args[0]);
		int timeOutMs = Integer.parseInt(args[1]);
		try
		{
			Thread t = new GreetingServer(port, timeOutMs);
			t.start();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
