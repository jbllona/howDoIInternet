import java.net.*;
import java.io.*;
import java.util.Scanner;


public class GreetingClient
{
	public static void main(String [] args)
	{
		UserInfo localUser = WelcomScreen.desplay();	// get info for initializing
		try
		{
			
			System.out.println("Connecting to " + localUser.address + " on port " + localUser.port);
			
			Socket client = null;	// initializing Socket
			client = new Socket(localUser.address, localUser.port);
			
			// // initializing I/O
			Scanner input = new Scanner(System.in).useDelimiter("\n");	
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			String msgToSend = "";
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			
			out.writeUTF("###SYSTEM::CREATEUSER::"+localUser.name);
			
			if(in.readUTF().equals("success")
			{
				
			}
			
			while(true)
			{
				
				System.out.print("You: ");
				
				msgToSend = input.next();
				
				if(msgToSend.equals("EXIT"))
				{
					break;
				}
				
				out.writeUTF(msgToSend);
				
				
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
