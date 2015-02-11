

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GPSLogger {
	public static void main (String args[])throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(6666);
		int cnt =0;
		while (true){
			byte [] receiveData = new byte[1024];
			byte [] sendData = new byte [1024];
			
			DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
			System.out.println("Wait for client packet");
			
			serverSocket.receive(receivePacket);
			
			String sentence = new String(receivePacket.getData());
			String[] src =  sentence.trim().split(" ");
			int count = src.length;
			System.out.println("Receive: " + sentence.trim());			
			Connection conn = null;
			try 
			{
				//test 3306, tao user thuog, ket noi user thuog, grant access to other comp.
				//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				//Class.forName("com.myqsl.jdbc.Driver");
				String url = "jdbc:mysql://172.28.10.96:3306/test1";
				//String url = "jdbc:odbc:test1";
				//conn = DriverManager.getConnection("jdbc:odbc:TEST", "root", "12345678");
				conn = DriverManager.getConnection(url, "minhtri", "12345678");
				for (int i = 0; i<count ; i +=7 )
				{
				//System.out.println(src[6].length());
				String query = "insert into GPSTest (IMEI, longitude, latitude, elevation, currentTime, accuracy, speed) " +
						"values (" 
								+ src[i] + ", "
								+ src[i+1] + ", "
								+ src[i+2] + ", "
								+ src[i+3] + ", "
								+ "'" + src[i+4] + "', "								
								+ src[i+5] + ", "
								+ src[i+6] + ");"
								;
				try 
				{
					System.out.println(query);
					Statement st = conn.createStatement();
					//ResultSet rs = st.executeUpdate(query);
					int rs = st.executeUpdate(query);
					/*while (rs.next())
					{
						String s = rs.getString("MobileID");
						System.out.println("abc"+s);
					}*/
				}
				catch (SQLException ex)
				{
					System.err.println(ex.getMessage());
				}
				}
			}
			catch (Exception ex)
			{
				System.err.println("1"+ex.getMessage());
			}
			
			System.out.println("Complete serving client: " + ++cnt);
		}
	}
}
