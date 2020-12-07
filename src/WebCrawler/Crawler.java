package WebCrawler;

import java.util.ArrayList;

public class Crawler {

    private static Crawler instance = null;
    private UserData arguments;
    private Threads multithread;
    private ArrayList<String> linkList;
    private ArrayList<Visited> visitedLinks;

    private Crawler(UserData arguments, Threads multithread) {
        this.arguments = arguments;
        this.multithread = multithread;
        this.linkList = new ArrayList<>();
        this.visitedLinks = new ArrayList<>();
    }

    public void run() {

    }

    public int getNoOfThreads() {
        return arguments.getNoOfThreads();
    }

    public static Crawler getInstance(UserData arguments, Threads multithread) {
        if (instance == null)
            instance = new Crawler(arguments, multithread);

        return instance;
    }
}
