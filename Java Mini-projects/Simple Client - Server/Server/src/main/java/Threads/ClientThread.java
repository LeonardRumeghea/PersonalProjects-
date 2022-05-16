package Threads;

import DAO.Friendship;
import DAO.User;
import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class ClientThread extends Thread {

    //    Commands:

    //    register <name> - sign up
    //    login <name> - sign in
    //    friend <name_1> <name_2> ... <name_n> - friends with the supplied name should be added (if those accounts exist)
    //    sendALL <message> - send a message to all of your friends
    //    sendTO <toUser> <message> - send a message to a friend whose name is provided
    //    readNew - show unread messages
    //    readAll - show all messages

    //    mkdir <name>
    //	  upload <path>
    //	  ls

    private final Socket socket;
    private Utility.User user;
    private boolean running = true;

    public ClientThread (Socket socket) { this.socket = socket ; }

    public void run () {
        try {
            socket.setSoTimeout(10_000);
        } catch (SocketException e) { e.printStackTrace(); }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (running) {
                String request = in.readLine();
                String command, content = null;

                if (!request.contains(" ")) { // exit / logout
                    command = request;
                } else {
                    command = request.substring(0, request.indexOf(' '));
                    content = request.substring(request.indexOf(' ') + 1);
                }

                System.out.println("Request: " + request);
                System.out.println("Command: " + command);
                System.out.println("Content: " + content);

                switch (command.toUpperCase()) {
                    case "REGISTER" -> signUp(content);
                    case "LOGIN" -> signIn(content);
                    case "FRIEND" -> addNewFriends(content);
                    case "SENDTO" -> sendMessageTo(content);
                    case "SENDALL" -> sendMessageToAll(content);
                    case "READNEW" -> readNewMessages();
                    case "READALL" -> readAllMessages();
                    case "UPLOAD" -> uploadFileToServer(content);
                    case "MKDIR" -> createDirectory(content);
                    case "LOGOUT" -> signOut();
                    case "EXIT" -> stopTheServer();
                    default -> unknownCommand();
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Because the client [Address: " +
                    socket.getLocalAddress() +
                    " - Port: " + socket.getPort() +
                    " - Local Port: " + socket.getLocalPort() +
                    "] had been afk for too long, it was unplugged.");
            SimpleServer.logout(socket);

        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void sendMessageToClient(String buffer) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("\033[4;36mServer\033[0m: " + buffer);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unknownCommand() {
        String answer = "Sorry, but I'm not familiar with this command. Please try a different one.";
        sendMessageToClient(answer);
    }

    private void signUp(String name) {
        DAO.User userDAO = new User();

        try {
            if (userDAO.exits(name)) {
                sendMessageToClient("There is already a user with this name. Please try again.");
                return;
            }

            userDAO.insert(name);
            this.user = userDAO.findByName(name);
            sendMessageToClient("Welcome \033[1m" + name + "\033[0m!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void signIn(String name) {
        try {
            this.user = (new User()).findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sendMessageToClient(
                user.getId() != null
                        ? "Welcome back \033[1m" + name + "\033[0m!"
                        : "Invalid name!"
        );
    }

    private void signOut() {
        running = false;
        SimpleServer.logout(socket);
        sendMessageToClient("Bye \033[1m" + user.getName() + "\033[0m!");
    }

    private void stopTheServer() {
        sendMessageToClient("New connections are no longer accepted by the server!");
        SimpleServer.stop();
    }

    private void addNewFriends(String buffer) {

        if (buffer == null || buffer.trim().equals("")) {
            sendMessageToClient("You did not correctly input the names of the people you wish to add.");
            return;
        }

        if (user == null) {
            sendMessageToClient("To add friends, you must be logged in.");
            return;
        }

        String[] friends = buffer.split(" ");
        List<String> incorrectNames = new ArrayList<>();

        DAO.User userDAO = new DAO.User();
        DAO.Friendship friendshipDAO = new DAO.Friendship();

        for (String friend : friends) {
            try {
                if (userDAO.exits(friend)) {
                    friendshipDAO.insert(userDAO.findByName(friend), user);
                } else {
                    incorrectNames.add(friend);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (incorrectNames.size() == 0) {
            sendMessageToClient("All of your friends have been added successfully.");
        }
        else {
            StringBuilder buff = new StringBuilder();
            for (String name : incorrectNames) {
                buff.append("\033[1m").append(name).append("\033[0m").append(" ");
            }
            sendMessageToClient("We were unable to add the following friends: " + buff);
        }
    }

    private void sendMessageTo(String buffer) {

        if (user == null) {
            sendMessageToClient("To add friends, you must be logged in.");
            return;
        }

        if (buffer == null || buffer.trim().equals("")) {
            sendMessageToClient("You did not correctly input command. Please try again.");
            return;
        }

        buffer = buffer.trim();

        String name = buffer.substring(0, buffer.indexOf(' '));
        String message = buffer.substring(buffer.indexOf(' ') + 1);

        if (name.trim().equals("")) {
            sendMessageToClient("You misspelled the name of the receiver. Please try again.");
            return;
        }

        if (message.trim().equals("")) {
            sendMessageToClient("You typed the message you wanted to send to \033[1m" + name + "\033[0m incorrectly. Please try again.");
            return;
        }

        try {
            if ((new User()).findByName(name).getId() == null) {
                sendMessageToClient("There is no user with the name \033[1m" + name + "\033[0m.");
                return;
            }

            try {
                (new DAO.Message()).insert(user.getId(), (new User()).findByName(name).getId(), message);
                sendMessageToClient("The message was delivered.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToAll(String buffer) {

        if (user == null) {
            sendMessageToClient("To add friends, you must be logged in.");
            return;
        }

        if (buffer == null || buffer.trim().equals("")) {
            sendMessageToClient("You did not correctly input command. Please try again.");
            return;
        }

        buffer = buffer.trim();

        List<Utility.User> friends = null;
        try {
            friends = (new Friendship()).getFriends(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (friends == null) {
            sendMessageToClient("There are no friends for you to message. To add a buddy, use the \033[1mfriend <name>\033[0m command.");
            return;
        }

        for (Utility.User friend : friends) {
            try {
                (new DAO.Message()).insert(user.getId(), (new User()).findByName(friend.getName()).getId(), buffer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        sendMessageToClient("The messages was delivered.");
    }

    private void readNewMessages() {

        if (user == null) {
            sendMessageToClient("To add friends, you must be logged in.");
            return;
        }

        List<Utility.Message> messages = null;
        try {
            messages = (new DAO.Message()).getUnreadMessages(user.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (messages == null || messages.size() == 0) {
            sendMessageToClient("There are no new messages for you.");
            return;
        }

        StringBuilder buff = new StringBuilder();
        buff.append("There are ").append(messages.size()).append(" new messages for you:");

        for (Utility.Message message : messages) {
            try {
                buff.append("<newLine>From: ").append( (new User()).findById(Integer.parseInt(message.getFrom())).getName() );
                buff.append("<newLine><tab>Message: ").append(message.getContent());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        sendMessageToClient(buff.toString());
    }

    private void readAllMessages() {

        if (user == null) {
            sendMessageToClient("To add friends, you must be logged in.");
            return;
        }

        List<Utility.Message> messages = null;
        try {
            messages = (new DAO.Message()).getAllMessages(user.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (messages == null || messages.size() == 0) {
            sendMessageToClient("There are no new messages for you.");
            return;
        }

        StringBuilder buff = new StringBuilder();
        buff.append("There are ").append(messages.size()).append(" messages for you:");

        for (Utility.Message message : messages) {
            try {
                buff.append("<newLine>From: ").append( (new User()).findById(Integer.parseInt(message.getFrom())).getName() );
                buff.append("<newLine><tab>Message: ").append(message.getContent());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        sendMessageToClient(buff.toString());
    }

    private void createDirectory(String buffer) {

        if (user == null) {
            sendMessageToClient("To create a folder, you must be logged in.");
            return;
        }

        try {
            ChannelSftp channel = setupJsch();
            channel.connect();

            System.out.println("SFTP Connected: " + channel.isConnected());

            channel.mkdir("D:/" + buffer);
            sendMessageToClient("The \"" + buffer + "\" folder was successfully created.");


            Vector<ChannelSftp.LsEntry> entities = channel.ls("/");

            System.out.println("Entries in root directory");
            for (ChannelSftp.LsEntry entry : entities) {
                System.out.println(entry.getFilename());
            }

            channel.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            sendMessageToClient("Unfortunately, I was unable to establish a \"" + buffer + "\" folder.");
            e.printStackTrace();
        }
    }

    private void uploadFileToServer(String buffer) {

        if (user == null) {
            sendMessageToClient("To add friends, you must be logged in.");
            return;
        }

        try {
            ChannelSftp channelSftp = setupJsch();
            System.out.println(channelSftp);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        //jsch.setKnownHosts("src/Users_Storage" + "");
        Session jschSession = jsch.getSession("SimpleServer", "127.0.0.1", socket.getLocalPort());
        jschSession.setPassword("admin");
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }
}