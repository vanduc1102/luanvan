import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	public static void main(String[] args) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6655);
        System.out.println("Server start....");
        while(true)
        {
        	Socket connectionSocket = welcomeSocket.accept();
        	ThreadTCP newThread = new ThreadTCP(connectionSocket);
        	newThread.start();
        }
	}
}
