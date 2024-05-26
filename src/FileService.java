import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileService {

    private static FileService instance; 
    private static final String logFile = "./files/log.csv";
    private static PrintWriter writer;     

    private FileService() {
        try {
            writer = new PrintWriter(logFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static synchronized FileService getInstance() {
        if (instance == null) {
            if (instance == null) {
                instance = new FileService();
            }
        }
        return instance;
    }

    private String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }


    public void logAction(String action) {
        if (writer != null) {
            writer.println(action + " at " + getDateTime());
            writer.flush();
        } else {
            System.err.println("Error: Log writer not initialized!"); 
        }
    }
   
    

}
