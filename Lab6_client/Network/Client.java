package Network;
import Commands.*;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;


public class Client {
    public final static int SERVICE_PORT = 50001;
    DatagramSocket clientSocket;
    InetAddress IPAddress;
    DatagramPacket inputPacket;
    ByteArrayOutputStream bStream;
    DatagramSocket datagramSocket;
    private byte[] receivingDataBuffer = new byte[4096];
    private byte[] sendingDataBuffer = new byte[1024];

    public Client(){
        try {
            datagramSocket = new DatagramSocket(50002);
            datagramSocket.setSoTimeout(5000);
        } catch (SocketException e) {
            //e.printStackTrace();
        }
    }

    public void initialize() throws InterruptedException {
        try{
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("localhost");
            bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            Message message = new Message(null, Status.NOT_ESTABLISHED);
            oo.writeObject(message);
            oo.close();
            byte[] serializedMessage = bStream.toByteArray();
            DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length,IPAddress, SERVICE_PORT);
            clientSocket.send(sendingPacket);
            if(recieveMessage().getStatus()==Status.ESTABLISHED){
                System.out.println("Connection established, you can start entering commands");
            }
        }
        catch(IOException e) {
            //e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("Server is not responding, reconnecting in 5 sec");
            TimeUnit.SECONDS.sleep(5);
            initialize();
        }

    }
    public void sendCommand(Command command){
        try {
            //System.out.println(command);
            Message message = new Message(command, Status.ESTABLISHED);
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

    public Message recieveMessage(){
        try {
            receivingDataBuffer = new byte[4096];
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            Message message = (Message) iStream.readObject();
            iStream.close();
            return message;
        } catch (IOException | ClassNotFoundException e){
            //e.printStackTrace();
            return null;
        }
    }

}
