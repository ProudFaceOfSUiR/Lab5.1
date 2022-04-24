package Network;

import Commands.Command;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class Server {
    InetAddress IPAddress;
    private final int SERVICE_PORT = 50001;
    private DatagramSocket datagramSocket;
    private byte[] receivingDataBuffer = new byte[4096];
    private byte[] sendingDataBuffer = new byte[1024];
    public boolean connected;
    DatagramPacket inputPacket;
    ByteArrayOutputStream bStream;

    public Server(){
        try {
            datagramSocket = new DatagramSocket(SERVICE_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void bind(){
        try {
            bStream = new ByteArrayOutputStream();
            datagramSocket = new DatagramSocket(SERVICE_PORT);
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            Message message = (Message) iStream.readObject();
            iStream.close();
            connected = true;

            if (message.getStatus() == Status.NOT_ESTABLISHED){
                System.out.println("Connection established, you can start executing commands");
            }
            else{
                System.out.println("Client is found");
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Unable to launch server");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Message recieveMessage(){
        try {
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            Message message = (Message) iStream.readObject();
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

    public Command recieveCommand(){
        try {
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            Message message = (Message) iStream.readObject();
            iStream.close();
            return message.getCommand();
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
        DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length,IPAddress, 50002);
        clientSocket.send(sendingPacket);
    }
}
