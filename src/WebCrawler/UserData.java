package WebCrawler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Această clasă implmentează metodele necesare
 * pentru popularea membrilor cu argumentele extrase
 * din fișierele de configurare
 * @author Frîncu Andreea
 * @see ParserFile
 */
public class UserData {

    /**
     * Descrierea membrilor clasei
     * <b>noOfThreads</b> este un membru care reprezinta numarul de thread-uri
     * folosite in rularea programului
     *
     * <b>depthLevel</b> este un membru care reprezinta nivelul de adancime pana
     * la care se va parsa o pagina web
     *
     * <b>delay</b> este un membru care reprezinta timpul de asteptare pana cand
     * se va incepe parsarea altei adrese url din fisierul url.txt
     *
     * <b>rootFolder</b> este un membru care contine calea catre folderul root
     *
     * <b>optionRobot</b> este un membru care stabileste daca va fi sau nu luat
     * in considerare fisierul robots.txt
     *
     * <b>preferences</b> este un membru care contine ce tip de fisiere pot fi
     * descarcate
     *
     * <b>urlList</b> este un membru de tip lista care contine lista de url-uri
     * extrase din fisierul url.txt
     *
     * <b>parser</b> este un obiect de tip ParserFile care ajuta la popularea
     * UserData cu argumentele extrase in ParserFile
     */
    private int noOfThreads;
    private int depthLevel;
    private int delay;
    private int logLevel;
    private String rootFolder;
    private String optionRobot;
    private String preferences;
    private ArrayList<String> urlList;
    private ParserFile parser;

    /**
     * Constructorul clasei UserData
     * @param parser - parametru din care se populează membrii clasei UserData
     *               parser.arguments conține lista de argumente
     *               parser.urlList conține lista de url-uri
     * @throws FileNotFoundException
     */
    public UserData(ParserFile parser) throws FileNotFoundException {
        this.urlList = new ArrayList<>();
        this.noOfThreads = Integer.parseInt(parser.arguments.get(0));
        this.depthLevel = Integer.parseInt(parser.arguments.get(1));
        this.delay = Integer.parseInt(parser.arguments.get(2));
        this.rootFolder = parser.arguments.get(3);
        this.optionRobot = parser.arguments.get(4);
        this.preferences = parser.arguments.get(5);
        this.logLevel = Integer.parseInt(parser.arguments.get(6));
        this.urlList = parser.urlList;
    }

    /**
     * Obține numărul de thread-uri
     * @return noOfThreads
     */
    public int getNoOfThreads() {
        return noOfThreads;
    }

    /**
     * Obține nivelul de adâncime pentru care parsăm fiecare pagină web
     * @return depthLevel
     */
    public int getDepthLevel() {
        return depthLevel;
    }

    /**
     * Obține timpul de așteptare pâna la următoarea pagină web din listă care
     * trebuie parsată
     * @return delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Obține calea către root folder
     * @return rootFolder
     */
    public String getRootFolder() {
        return rootFolder;
    }

    /**
     * Obține opțiunea pentru robots.txt de a fi sau nu luat în considerare
     * @return optionRobot
     */
    public String getOptionRobot() {
        return optionRobot;
    }

    /**
     * Obține preferințele pentru descărcarea anumitor tipuri de fișiere
     * @return preferences
     */
    public String getPreferences() {
        return preferences;
    }

    /**
     * Obține nivelul pentru care se va scrie un mesaj î fișierul de log
     * @return logLevel
     */
    public int getLogLevel() {
        return logLevel;
    }

    /**
     * Se obține lista de url-uri
     * @return urlList
     */
    public ArrayList<String> getUrlList() {
        return urlList;
    }
}
