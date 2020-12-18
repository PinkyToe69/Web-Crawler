package WebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogManager {

    private File logFile;
    private int log_level;
    private String message;
    private String date;
    private String url;

    public LogManager(int log_level) {
        this.logFile = new File(Paths.get("").toAbsolutePath().toString() + "\\logger.txt");
        this.log_level = log_level;
        //this.message = null;
        Date today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.date = dateFormat.format(today);
    }

    public void getInfoMessage(String url) throws IOException {
        message = message + date;
        String [] mess = message.split("null");
        String str = "[" + mess[1] + "] " + "INFO: Downloading " + url + "\n";
        FileWriter fr = new FileWriter(logFile, true);
        fr.write(str);
        fr.close();
    }

    public void getWarningMessage(String url) throws IOException {
        message = message + date;
        String [] mess = message.split("null");
        String str = "[" + mess[1] + "] " + "WARN: Page " + url + " not found (404)" + "\n";
        FileWriter fr = new FileWriter(logFile, true);
        fr.write(str);
        fr.close();
    }

    public void getErrorMessage(String url) throws IOException {
        message = message + date;
        String [] mess = message.split("null");
        String [] url2 = url.split("://");
        url = url2[1];
        String [] url3 = url.split("/");
        String str = "[" + mess[1] + "] " + "ERR: Cannot resolve host " + url3[0] + "\n";
        FileWriter fr = new FileWriter(logFile, true);
        fr.write(str);
        fr.close();
    }

    public void writeMessage(String url) throws IOException {
        message = null;
        getInfoMessage(url);
    }
}
