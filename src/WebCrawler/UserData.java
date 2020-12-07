package WebCrawler;

import java.util.ArrayList;

public class UserData {

    private int noOfThreads;
    private int depthLevel;
    private int delay;
    private String rootFolder;
    private String optionRobot;
    private String preferences;
    private ArrayList<String> urlList;
    private ParserFile parser;

    public UserData(int noOfThreads, int depthLevel, int delay, String rootFolder, String optionRobot,
                    String preferences, ArrayList<String> urlList, ParserFile parser) {
        this.noOfThreads = noOfThreads;
        this.depthLevel = depthLevel;
        this.delay = delay;
        this.rootFolder = rootFolder;
        this.optionRobot = optionRobot;
        this.preferences = preferences;
        this.urlList = new ArrayList<>();
        this.parser = parser;
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
}
