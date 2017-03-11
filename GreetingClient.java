import java.net.*;
import java.io.*;
import java.util.Scanner;


public class GreetingClient
{
	public static void main(String [] args)
	{
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		try
		{
			System.out.println("Connectiong to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);
			

			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			
			String msgToSend;
			Scanner input = new Scanner(System.in);
			do 
			{
				msgToSend = input.next();
			} while (!msgToSend.equals("EXIT"));
			
			System.out.println("You: " + msgToSend);
			out.writeUTF(msgToSend);
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			
			System.out.println("Server: " + in.readUTF());
			client.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
