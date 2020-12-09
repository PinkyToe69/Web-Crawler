package WebCrawler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class UserData {

    private int noOfThreads;
    private int depthLevel;
    private int delay;
    private int logLevel;
    private String rootFolder;
    private String optionRobot;
    private String preferences;
    private ArrayList<String> urlList;
    private ParserFile parser;

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

    public int getNoOfThreads() {
        return noOfThreads;
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public int getDelay() {
        return delay;
    }

    public String getRootFolder() {
        return rootFolder;
    }

    public String getOptionRobot() {
        return optionRobot;
    }

    public String getPreferences() {
        return preferences;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public ArrayList<String> getUrlList() {
        return urlList;
    }
}
