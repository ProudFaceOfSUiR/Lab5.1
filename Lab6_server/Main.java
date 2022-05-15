import Network.Message;
import Network.Server;
import Network.Status;
import PostgreSQL.DatabaseManager;
import Security.LoginController;
import com.company.Database;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    /**
     * @param args
     * @author Alexandr Grebtsov
     * Main class
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Database Database = new Database();
        Server server = new Server(Database);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.initialize();
        Database.initialize(databaseManager);
        databaseManager.createNewUserBase();
        databaseManager.createNewPasswordBase();
        Database.getCollection().clear();
        databaseManager.readCollectionFromDatabase(Database);


        while (true) {
            server.message = server.recieveMessage();
            System.out.println("me");
            if(server.message.getStatus() == Status.ESTABLISHED) {
                //Lock lock = new ReentrantLock();
                ForkJoinPool forkJoinPool = new ForkJoinPool();
                //server.compute();

                //if(lock.tryLock(100, TimeUnit.MILLISECONDS)){
                    ;
                //}

                System.out.println(server.message.getCommand());
                try {
                    //System.out.println(forkJoinPool.invoke(server));
                    //server.compute();
                    //server.fork();
                    //server.join();
                    //server.isDone();
                    //server.fork();
                    //server.join();
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
        }
    }
}


