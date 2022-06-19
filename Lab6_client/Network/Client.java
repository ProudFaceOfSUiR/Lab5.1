package Network;
import Commands.*;
import Security.LoginController;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;


public class Client {
    public static int SERVICE_PORT = 50001;
    DatagramSocket clientSocket;
    InetAddress IPAddress;
    DatagramPacket inputPacket;
    ByteArrayOutputStream bStream;
    DatagramSocket datagramSocket;
    int selfPort;
    private LoginController loginController;
    private byte[] receivingDataBuffer = new byte[65000];
    private byte[] sendingDataBuffer = new byte[65000];

    public Client(int port){
        try {
            datagramSocket = new DatagramSocket(port);
            datagramSocket.setSoTimeout(5000);
            selfPort = port;
        } catch (SocketException e) {
            //e.printStackTrace();
        }
    }

    public void initialize(LoginController loginController) throws InterruptedException {
        try{
            loginController.login();
            this.loginController = loginController;
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("localhost");
            bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            Message message = new Message(null, Status.NOT_ESTABLISHED,selfPort);
            message.setLogin(loginController);
            oo.writeObject(message);
            oo.close();
            byte[] serializedMessage = bStream.toByteArray();
            DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length,IPAddress, SERVICE_PORT);
            clientSocket.send(sendingPacket);
            message = recieveMessage();
            if(message.getStatus()==Status.ESTABLISHED && message.getLogin().isApproved()){
                System.out.println("Connection established, you can start entering commands");
            }
            if (message.getStatus()==Status.NOT_ESTABLISHED && !message.getLogin().isApproved()) {
                System.out.println("This login has already been taken");
                initialize(loginController);
            }

        }
        catch(IOException e) {
            //e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("Server is not responding, reconnecting in 5 sec");
            TimeUnit.SECONDS.sleep(5);
            initialize(loginController);
        }
    }

    public Status graphicInitialize(LoginController loginController) {
        Status status = Status.NOT_ESTABLISHED;
        try{
            this.loginController = loginController;
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("localhost");
            bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            Message message = new Message(null, Status.NOT_ESTABLISHED,selfPort);
            message.setLogin(loginController);
            oo.writeObject(message);
            oo.close();
            byte[] serializedMessage = bStream.toByteArray();
            DatagramPacket sendingPacket = new DatagramPacket(serializedMessage, serializedMessage.length,IPAddress, SERVICE_PORT);
            clientSocket.send(sendingPacket);
            message = recieveMessage();
            System.out.println(message);
            if(message.getStatus()==Status.ESTABLISHED && message.getLogin().isApproved()){
                status = Status.ESTABLISHED;
                //loginController.setMessageToGraphics("Connection established, you can start entering commands");
            }
            if (message.getStatus()==Status.NOT_ESTABLISHED && !message.getLogin().isApproved() && loginController.isNew()) {
                status = Status.TAKEN_LOGIN;
            }
            if (message.getStatus()==Status.NOT_ESTABLISHED && !message.getLogin().isApproved() && !loginController.isNew()) {
                status = Status.WRONG_PASSWORD;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
            loginController.setMessageToGraphics("Server is not responding, reconnecting in 5 sec");
        }
        return status;
    }

    public void sendCommand(Command command){
        try {
            //System.out.println(command);
            Message message = new Message(command, Status.ESTABLISHED,selfPort);
            message.setLogin(loginController);
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
