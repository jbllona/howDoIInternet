import java.net.*;
import java.io.*;

public class GreetingClient
{
	private static String msgToSend = "Hello from the other sIIIIIiiidddee";
	public static void main(String [] args)
	{
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		try
		{
			System.out.println("Connectiong to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);
			
			// System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			
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
