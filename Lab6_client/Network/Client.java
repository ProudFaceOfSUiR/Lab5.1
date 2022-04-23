package Network;
import Commands.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;

public class Client {
    public final static int SERVICE_PORT = 50001;
    DatagramSocket clientSocket;
    InetAddress IPAddress;
    ByteArrayOutputStream bStream;

    public void initialize(){
        try{
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
            System.out.println("Unable to send command");
        }
    }



}
