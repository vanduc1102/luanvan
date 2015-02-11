import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class ThreadTCP extends Thread {
	private Socket socket = null;
	private Random r = null;
	public ThreadTCP(Socket connectionSocket)
	{
		socket = connectionSocket;
		r = new Random();
	}
	public void run()
	{
		System.out.println("Thread start.....");
		BufferedReader inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			inFromClient =
			        new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outToClient = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
             
	
		String clientMessenger = null;
		while(true)
		{
	        try {
				clientMessenger = inFromClient.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	        if(clientMessenger == null)
	        	continue;
	        System.out.println("Received: " + clientMessenger);
			
			if(clientMessenger.split(":")[0].equals("UPDATE"))
			{
				
				double speed = 10.0 + r.nextDouble() * 30.0;
				try {
					outToClient.writeBytes(clientMessenger + ":" + speed + ":0.0:" + System.currentTimeMillis() + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			else if(clientMessenger.split(":")[0].equals("BUILDROAD"))
			{
			}
			else if(clientMessenger.split(":")[0].equals("FORECAST"))
			{
			}
		}
	}
}
