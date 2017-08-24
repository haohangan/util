
package test.znode_monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ForLeterMainTest {
	
	/**
	 * 参考 org.apache.zookeeper.client.FourLetterWordMain.main
	 * @param host
	 * @param port
	 * @param cmd
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static String status(String host,int port,String cmd) throws UnknownHostException, IOException{
		Socket sock = new Socket(host, port);
        BufferedReader reader = null;
        try {
            OutputStream outstream = sock.getOutputStream();
            outstream.write(cmd.getBytes());
            outstream.flush();
            // this replicates NC - close the output stream before reading
            sock.shutdownOutput();

            reader =
                    new BufferedReader(
                            new InputStreamReader(sock.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } finally {
            sock.close();
            if (reader != null) {
                reader.close();
            }
        }
	}
	
	
	
     public static void main(String[] args) throws IOException {
//    	 args = new String[]{"localhost","2184","srvr"};
//		org.apache.zookeeper.client.FourLetterWordMain.main(args);
//    	String result = status("localhost",2183,"conf");
//    	 String result = status("localhost",2183,"cons");
//    	 String result = status("localhost",2183,"crst");
//    	 String result = status("localhost",2183,"dump");
//    	 String result = status("localhost",2183,"envi");
//    	 String result = status("localhost",2183,"ruok");//are you ok?i am ok
//    	 String result = status("localhost",2183,"srst");
//    	 String result = status("localhost",2183,"srvr");//Lists full details for the server.
//    	 String result = status("localhost",2182,"stat");//Lists brief details for the server and connected clients.
//    	 String result = status("localhost",2183,"dirs");
//    	 String result = status("localhost",2183,"wchp");
//    	 String result = status("localhost",2183,"mntr");//Outputs a list of variables that could be used for monitoring the health of the cluster.
    	 String result = status("localhost",2183,"srvr");
    	System.out.println(result);
	}
}
