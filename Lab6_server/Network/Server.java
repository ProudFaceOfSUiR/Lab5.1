package Network;

import Commands.Command;
import Security.LoginController;
import com.company.Database;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Server {//extends RecursiveTask<String> {
    InetAddress IPAddress;
    private final int SERVICE_PORT = 50001;
    private DatagramSocket datagramSocket;
    private byte[] receivingDataBuffer = new byte[65000];
    DatagramPacket inputPacket;
    ByteArrayOutputStream bStream;
    private int port;
    public Message message;
    Database database;

    public Server(Database database){
        try {
            datagramSocket = new DatagramSocket(SERVICE_PORT);
            this.database = database;
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
            //this.message = new Message(null, null);
            this.message = message;
            System.out.println(this.message.getCommand()+"in resieve mes");
            return message;
        } catch (IOException e){
            e.printStackTrace();
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

    public void sendMessage(Message message) throws IOException {
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


    @Override
    protected String compute(){
        while (true) {
            message = recieveMessage();
            System.out.println("me");
            if(message.getStatus() == Status.ESTABLISHED) {
                //Lock lock = new ReentrantLock();
                //ForkJoinPool forkJoinPool = new ForkJoinPool();

                //if(lock.tryLock(100, TimeUnit.MILLISECONDS)){
                ;
                //}

                System.out.println(server.message.getCommand());
                try {
                    //System.out.println(forkJoinPool.invoke(server));
                    //server.compute();
                    server.fork();
                    server.join();
                    server.isDone();
                    server.fork();
                    server.join();
                    server.sendCommand(server.message.getCommand());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Database.updateHistoryLog(server.message.getCommand());
            }
            if(server.message.getStatus() == Status.NOT_ESTABLISHED){

                LoginController loginController = server.message.getLogin();
                boolean userExists = databaseManager.userExists(loginController);
                if(!userExists && loginController.isNew()){
                    System.out.println("case 1");
                    databaseManager.addNewUser(loginController);
                    Message reply = new Message(null, Status.ESTABLISHED);
                    reply.setLogin(loginController);
                    reply.getLogin().setApproved(true);
                    server.sendMessage(reply);
                }
                if(userExists && loginController.isNew()){
                    System.out.println("case 2");
                    Message reply = new Message(null, Status.NOT_ESTABLISHED);
                    reply.setLogin(loginController);
                    reply.getLogin().setApproved(false);
                    server.sendMessage(reply);
                }
                if(userExists && !loginController.isNew()){
                    System.out.println("case 3");
                    if (databaseManager.passwordMatches(loginController.getPassword(), loginController.getLogin())) {
                        Message reply = new Message(null, Status.ESTABLISHED);
                        reply.setLogin(loginController);
                        reply.getLogin().setApproved(true);
                        server.sendMessage(reply);
                    }
                    else{
                        Message reply = new Message(null, Status.NOT_ESTABLISHED);
                        reply.setLogin(loginController);
                        reply.getLogin().setApproved(false);
                        server.sendMessage(reply);
                    }
                }
                if(userExists && loginController.isNew()){
                    System.out.println("case 4");
                    Message reply = new Message(null, Status.NOT_ESTABLISHED);
                    reply.setLogin(loginController);
                    reply.getLogin().setApproved(false);
                    server.sendMessage(reply);
                }
            }

     */

}
