package WebCrawler;

import java.util.ArrayList;

public class Crawler {

    private static Crawler instance = null;
    private UserData arguments;
    private Threads multithread;
    private ArrayList<String> linkList;
    private ArrayList<Visited> visitedLinks;

    private Crawler() {
        this.arguments = arguments;
        this.multithread = multithread;
        this.linkList = linkList;
        this.visitedLinks = visitedLinks;
    }

    public void run() {

    }

    public static Crawler getInstance() {
        if (instance == null)
            instance = new Crawler();

        return instance;
    }
}
