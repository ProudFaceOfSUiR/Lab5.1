package Network;

import Commands.Command;
import PostgreSQL.DatabaseManager;
import Security.LoginController;
import com.company.Database;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Server {
        InetAddress IPAddress;
        private final int SERVICE_PORT = 50001;
        private DatagramSocket datagramSocket;
        private byte[] receivingDataBuffer = new byte[65000];
        DatagramPacket inputPacket;
        ByteArrayOutputStream bStream;
        private int port;
        public Message message;
        Database database;

    public Server(Database database) {
        try {
            datagramSocket = new DatagramSocket(SERVICE_PORT);
            this.database = database;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

        public Message recieveMessage () {
        try {
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            Message message = (Message) iStream.readObject();
            port = message.getPort();
            iStream.close();
            this.message = message;
            System.out.println(this.message.getCommand() + "in resieve mes");
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to receive command");
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

        public void sendCommand (Command command) throws IOException {
        Message message = new Message(command, Status.ESTABLISHED, port);
        DatagramSocket clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName("localhost");
        bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        oo.writeObject(message);
        oo.close();
        byte[] serializedMessage = bStream.toByteArray();
            System.out.println(port);
        DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length, IPAddress, port);
        clientSocket.send(sendingPacket);
    }

        public void sendMessage (Message message) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName("localhost");
        bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        oo.writeObject(message);
        oo.close();
        byte[] serializedMessage = bStream.toByteArray();
            System.out.println(port);
        DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length, IPAddress, port);
        clientSocket.send(sendingPacket);
    }




    }

