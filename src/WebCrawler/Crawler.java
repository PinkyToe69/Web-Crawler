package WebCrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Crawler {

    private static Crawler instance = null;
    private UserData arguments;
    private ParserFile parser;
    private Threads multithread;
    private ArrayList<String> linkList;
    private ArrayList<Visited> visitedLinks;

    private Crawler() throws FileNotFoundException {
        this.linkList = new ArrayList<>();
        this.visitedLinks = new ArrayList<>();
        this.parser = new ParserFile();
    }

    public Crawler run() throws IOException, URISyntaxException {
        parser.parse();
        arguments = new UserData(parser);
        this.linkList = arguments.getUrlList();
        /*for(String line : linkList){
            System.out.println(line);
        }*/
        return null;
    }

    public int getNoOfThreads() {
        return arguments.getNoOfThreads();
    }

    public static Crawler getInstance() throws FileNotFoundException {
        if (instance == null)
            instance = new Crawler();

        return instance;
    }
}
