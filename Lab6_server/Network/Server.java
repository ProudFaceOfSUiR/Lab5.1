package Network;

import Commands.Command;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class Server {
    private final int SERVICE_PORT = 50001;
    private DatagramSocket datagramSocket;
    private byte[] receivingDataBuffer = new byte[4096];
    private byte[] sendingDataBuffer = new byte[1024];
    public boolean connected;
    DatagramPacket inputPacket;

    public void waitUntilClient(){
        try {
            datagramSocket = new DatagramSocket(SERVICE_PORT);
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
            //System.out.println(iStream.readObject().getClass());
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

}
