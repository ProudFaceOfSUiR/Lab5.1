import Network.Message;
import Network.Server;
import Network.Status;
import PostgreSQL.DatabaseManager;
import com.company.Database;
import xmlParser.Parser;

import java.io.IOException;

public class Main {
    /**
     * @param args
     * @author Alexandr Grebtsov
     * Main class
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        Database Database = new Database();
        Database.initialize();
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.initialize();
        databaseManager.createNewUserBase();
        databaseManager.readCollectionFromDatabase(Database);
        databaseManager.addElementToDatabase(Database.getCollection().first());
        //DatabaseManager.execute();
        if (args.length > 0) Parser.parseFromXML(Database, args[0]);
        else System.out.println("No file");
        while (true) {
            Message message = server.recieveMessage();
            if(message.getStatus()== Status.ESTABLISHED) {
                message.getCommand().execute(Database);
                server.sendCommand(message.getCommand());
                Database.updateHistoryLog(message.getCommand());
            }
            if(message.getStatus() == Status.NOT_ESTABLISHED){
                server.sendCommand(null);
            }
        }
    }
}


