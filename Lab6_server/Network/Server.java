package Network;

import Commands.Command;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    InetAddress IPAddress;
    private final int SERVICE_PORT = 50001;
    private DatagramSocket datagramSocket;
    private byte[] receivingDataBuffer = new byte[4096];
    DatagramPacket inputPacket;
    ByteArrayOutputStream bStream;
    private int port;

    public Server(){
        try {
            datagramSocket = new DatagramSocket(SERVICE_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public Message recieveMessage(){
        try {
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            Message message = (Message) iStream.readObject();
            port = message.getPort();
            iStream.close();
            return message;
        } catch (IOException e){
            System.out.println("Unable to receive command");
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendCommand(Command command) throws IOException {
        Message message = new Message(command, Status.ESTABLISHED);
        DatagramSocket clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName("localhost");
        bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        oo.writeObject(message);
        oo.close();
        byte[] serializedMessage = bStream.toByteArray();
        DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length,IPAddress, port);
        clientSocket.send(sendingPacket);
    }
}
