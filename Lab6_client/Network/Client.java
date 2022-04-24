package Network;
import Commands.*;

import java.io.*;
import java.net.*;

public class Client {
    public final static int SERVICE_PORT = 50001;
    DatagramSocket clientSocket;
    InetAddress IPAddress;
    DatagramPacket inputPacket;
    ByteArrayOutputStream bStream;
    DatagramSocket datagramSocket;
    private byte[] receivingDataBuffer = new byte[4096];
    private byte[] sendingDataBuffer = new byte[1024];

    public void initialize(){
        try{
            datagramSocket = new DatagramSocket(50002);
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("localhost");
            bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            Message message = new Message(null, null,Status.NOT_ESTABLISHED);
            oo.writeObject(message);
            oo.close();
            byte[] serializedMessage = bStream.toByteArray();
            DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length,IPAddress, SERVICE_PORT);
            clientSocket.send(sendingPacket);
            //clientSocket.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void sendCommand(Command command){
        try {
            System.out.println(command);
            Message message = new Message(command, null, Status.ESTABLISHED);
            bStream.reset();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            oo.writeObject(message);
            oo.close();
            byte[] serializedMessage = bStream.toByteArray();
            DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length,IPAddress, SERVICE_PORT);
            clientSocket.send(sendingPacket);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Unable to send command");
        }
    }

    public Command recieveCommand(){
        try {


            receivingDataBuffer = new byte[4096];
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            Message message = (Message) iStream.readObject();
            iStream.close();
            System.out.println("check");
            return message.getCommand();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

}
