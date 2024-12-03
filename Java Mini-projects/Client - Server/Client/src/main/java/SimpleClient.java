import com.jcraft.jsch.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

public class SimpleClient {

    private static boolean running = true;
    private static final int TIME_OUT = 10_000;

    public static void main (String[] args) throws IOException {

        String serverAddress = "127.0.0.1";
        int PORT = 8100;
//        SimpleTimeLimiter timeLimiter = SimpleTimeLimiter.create();

        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            socket.setSoTimeout(TIME_OUT);
            while (running) {
                System.out.print("\033[0;33mClient\033[0m: ");
//                String request = timeLimiter.callWithTimeout(consoleReader::readLine, TIME_OUT, TimeUnit.MILLISECONDS);
                String request = consoleReader.readLine();
                out.println(request);

                String response = in.readLine();
                if (response != null) {
                    response = response.replaceAll("<newLine>", "\n");
                    response = response.replaceAll("<tab>", "\t");

                    System.out.println(response);
                }

                if (request.equalsIgnoreCase("LOGOUT")) {
                    running = false;
                }
            }
        } catch (SocketException e) {
            System.out.println("It appears that you have not sent any new commands in the previous " + TIME_OUT / 1_000 + " seconds." +
                    "\nThe bandwidth patrol asked me to disconnect you to save bandwidth. That stuff doesn't grow in trees!");
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }

//    public static void main(String[] args) {
//
//        try{
//            JSch jsch=new JSch();
//
//            String host=null;
//
//            host= JOptionPane.showInputDialog("Enter username@hostname",
//                    System.getProperty("user.name")+
//                            "@localhost");
//
//            String user=host.substring(0, host.indexOf('@'));
//            host=host.substring(host.indexOf('@')+1);
//            int port=22;
//
//            Session session=jsch.getSession(user, host, port);
//
//            // username and password will be given via UserInfo interface.
//            UserInfo ui=new MyUserInfo();
//            session.setUserInfo(ui);
//
//            session.connect();
//
//            Channel channel=session.openChannel("sftp");
//            channel.connect();
//            ChannelSftp c=(ChannelSftp)channel;
//
//
//        } catch (JSchException e) {
//            e.printStackTrace();
//        }
//
//    }
}
