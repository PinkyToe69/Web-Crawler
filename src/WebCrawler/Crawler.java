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
    //    for(String line : linkList) {
    //        System.out.println(line);
    //    }

        //============================
        int nrOfLinksPassed = linkList.size();
        int depth = arguments.getDepthLevel();

        for(int link = 0; link < nrOfLinksPassed; link++)       // we take every link passed as argument
        {
            ArrayList<String> linksOntheCurrentLevel = new ArrayList<String>();

            for (int i = 0; i < depth; i++)
            {
                if (i == 0)      // if we're on the first level
                {
                    linksOntheCurrentLevel.add(linkList.get(i));                // we get the first argument link
                    multithread = new Threads(linksOntheCurrentLevel.get(i));   // it will recieve just the first element
                }
                else
                {
                    int nrOfNecessaryThreads;        // number of necessary threads
                    linksOntheCurrentLevel.clear();                             // we rewrite the list
                    linksOntheCurrentLevel.addAll(multithread.getFinalList());
                    nrOfNecessaryThreads = linksOntheCurrentLevel.size();

                    for(int j = 0; j<nrOfNecessaryThreads; j++)
                    {
                        multithread = new Threads(linksOntheCurrentLevel.get(i));
                    }
                }
            }
        }
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
