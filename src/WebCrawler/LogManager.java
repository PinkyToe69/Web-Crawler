package WebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Această clasă implementează metodele pentru a scrie înregistrări
 * diferite în funcție de logLevel într-un fișier
 * @author Frîncu Andreea
 */
public class LogManager {

    /**
     * Descrierea membrilor clasei
     * <b>logFile</b> este un obiect de tip File care ia calea fisierului
     * logger.txt si ajuta la scrierea mesajelor in timpul rularii aplicatiei
     *
     * <b>log_level</b> este un membru care reprezinta nivelul de log pentru
     * care se va scrie un mesaj corespunzator
     *
     * <b>message</b> este un membru care contine mesajul ce va fi scris
     *
     * <b>date</b> este un membru care reprezinta data exacta la care este scris
     * mesajul, dupa forma "yyyy-mm-dd hh:mm:ss"
     *
     * <b>url</b> este un membru care contine url-ul pentru care va fi scris
     * mesajul in fisier
     */
    private File logFile;
    private int log_level;
    private String message;
    private String date;
    private String url;

    /**
     * Constructorul clasei <b>LogManager</b>
     * @param log_level - parametru care descrie nivelul conform caruia
     *                  va fi scris un mesaj corespunzator
     */
    public LogManager(int log_level) {
        this.logFile = new File(Paths.get("").toAbsolutePath().toString() + "\\src\\WebCrawler\\logger.txt");
        this.log_level = log_level;
        //this.message = null;
        Date today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.date = dateFormat.format(today);
    }

    /**
     * Se scrie mesajul de tip INFO pentru fiecare url si data exacta la care
     * este scris mesajul
     * @param url
     * @throws IOException
     */
    public void getInfoMessage(String url) throws IOException {
        message = message + date;
        String [] mess = message.split("null");
        String str = "[" + mess[1] + "] " + "INFO: Downloading " + url + "\n";
        FileWriter fr = new FileWriter(logFile, true);
        fr.write(str);
        fr.close();
    }

    /**
     * Se scrie mesajul de tip WARNING daca pagina nu poate fi gasita
     * @param url
     * @throws IOException
     */
    public void getWarningMessage(String url) throws IOException {
        message = message + date;
        String [] mess = message.split("null");
        String str = "[" + mess[1] + "] " + "WARN: Page " + url + " not found (404)" + "\n";
        FileWriter fr = new FileWriter(logFile, true);
        fr.write(str);
        fr.close();
    }

    /**
     * Se scrie mesajul de tip ERROR daca numele hostului la care incearca sa
     * se conecteze nu poate fi convertit la o adresă IP
     * @param url
     * @throws IOException
     */
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

    /**
     * Functie pentru a scrie mesajele corespunzatoare url-ului date ca
     * parametru, in functie de log_level
     * @param url
     * @throws IOException
     */
    public void writeMessage(String url) throws IOException {
        message = null;
        getInfoMessage(url);
    }
}
