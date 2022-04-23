package Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class Server {
    private final int SERVICE_PORT = 50001;
    private DatagramSocket datagramSocket;
    private byte[] receivingDataBuffer = new byte[1024];
    private byte[] sendingDataBuffer = new byte[1024];
    DatagramPacket inputPacket;

    public void initialize(){
        try {
            datagramSocket = new DatagramSocket(SERVICE_PORT);
            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            datagramSocket.receive(inputPacket);
            String message = new String(inputPacket.getData());
            System.out.println(message);
        } catch (IOException e){
            System.out.println("Unable to launch server");
            System.exit(0);
        }

    }

}
