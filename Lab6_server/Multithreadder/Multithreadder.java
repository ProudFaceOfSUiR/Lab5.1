package Multithreadder;
import Network.Message;
import Network.Server;
import Network.Status;
import PostgreSQL.DatabaseManager;
import Security.LoginController;
import com.company.Database;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Multithreadder extends RecursiveTask {
    @Override
    protected String compute() {

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
            System.out.println(server.message.getLogin().getLogin());
            if (server.message.getStatus() == Status.ESTABLISHED) {
                ForkJoinPool forkJoinPool = new ForkJoinPool();
                System.out.println(server.message.getCommand());
                try {
                    Database.setLogin(server.message.getLogin().getLogin());
                    server.message.getCommand().execute(Database);
                    server.sendCommand(server.message.getCommand());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Database.updateHistoryLog(server.message.getCommand());
            }
            if (server.message.getStatus() == Status.NOT_ESTABLISHED) {

                LoginController loginController = server.message.getLogin();
                boolean userExists = databaseManager.userExists(loginController);
                if (!userExists && loginController.isNew()) {
                    System.out.println("case 1");
                    databaseManager.addNewUser(loginController);
                    Message reply = new Message(null, Status.ESTABLISHED, server.message.getPort());
                    reply.setLogin(loginController);
                    reply.getLogin().setApproved(true);
                    try {
                        server.sendMessage(reply);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (userExists && loginController.isNew()) {
                    System.out.println("case 2");
                    Message reply = new Message(null, Status.NOT_ESTABLISHED,server.message.getPort());
                    reply.setLogin(loginController);
                    reply.getLogin().setApproved(false);
                    try {
                        server.sendMessage(reply);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (userExists && !loginController.isNew()) {
                    System.out.println("case 3");
                    if (databaseManager.passwordMatches(loginController.getPassword(), loginController.getLogin())) {
                        Message reply = new Message(null, Status.ESTABLISHED,server.message.getPort());
                        reply.setLogin(loginController);
                        reply.getLogin().setApproved(true);
                        try {
                            server.sendMessage(reply);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Message reply = new Message(null, Status.NOT_ESTABLISHED,server.message.getPort());
                        reply.setLogin(loginController);
                        reply.getLogin().setApproved(false);
                        try {
                            server.sendMessage(reply);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (userExists && loginController.isNew()) {
                    System.out.println("case 4");
                    Message reply = new Message(null, Status.NOT_ESTABLISHED,server.message.getPort());
                    reply.setLogin(loginController);
                    reply.getLogin().setApproved(false);
                    try {
                        server.sendMessage(reply);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


