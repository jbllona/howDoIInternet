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
			System.out.println("Connecting to " + serverName + " on port " + port);
			
			Socket client = null;
			Scanner input = new Scanner(System.in).useDelimiter("\n");
			String msgToSend;
			
			while(true)
			{
				client = new Socket(serverName, port);
				

				OutputStream outToServer = client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
				
				
				System.out.print("You: ");
				
				msgToSend = input.next();
				
				if(msgToSend.equals("EXIT"))
				{
					break;
				}
				
				out.writeUTF(msgToSend);
				InputStream inFromServer = client.getInputStream();
				DataInputStream in = new DataInputStream(inFromServer);
				System.out.println("sent");
				System.out.println("\nServer: " + in.readUTF());
				
			}
			
			
			client.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
