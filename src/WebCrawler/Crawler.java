package WebCrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import java.util.concurrent.Semaphore;

public class Crawler {

    private static Crawler instance = null;
    private UserData arguments;
    private ParserFile parser;
    private Threads multithread;
    private ArrayList<String> linkList;
    private ArrayList<Visited> visitedLinks;
    private Semaphore limitSemaphore;

    private Crawler() throws FileNotFoundException {
        this.linkList = new ArrayList<String>();
        this.visitedLinks = new ArrayList<Visited>();
        this.parser = new ParserFile();
    }

    public Crawler run() throws IOException, URISyntaxException, InterruptedException {
        parser.parse();
        arguments = new UserData(parser);
        this.linkList = arguments.getUrlList();
    //    for(String line : linkList) {
    //        System.out.println(line);
    //    }

        //============================
        int nrOfLinksPassed = linkList.size();
        int depth = arguments.getDepthLevel();

        System.out.println("The depth is " + depth);
        System.out.println(nrOfLinksPassed + " links had been passed");

        for(int link = 0; link < nrOfLinksPassed; link++)       // we take every link passed as argument
        {
            ArrayList<String> linksOnTheCurrentLevel = new ArrayList<String>();
            ArrayList<String> linksOnThePreviousLevel = new ArrayList<String>();

            for (int i = 0; i < depth; i++)
            {
                if (i == 0)      // if we're on the first level
                {
                    limitSemaphore.acquire();   // blocks a permit
                    linksOnTheCurrentLevel.add(linkList.get(i));                // we get the first argument link
                    multithread = new Threads(linksOnTheCurrentLevel.get(i));   // it will receive just the first element
                    multithread.start();
                    multithread.join();

                    linksOnTheCurrentLevel.clear();                             // we rewrite the list
                    linksOnTheCurrentLevel.addAll(multithread.getFinalList());
                    limitSemaphore.release();   // release a permit

                }
                else {
                    int nrOfNecessaryThreads;        // number of necessary threads
                    nrOfNecessaryThreads = linksOnTheCurrentLevel.size();

                    linksOnThePreviousLevel.addAll(linksOnTheCurrentLevel);

                    for (int j = 0; j < nrOfNecessaryThreads; j++) {
                        if (limitSemaphore.availablePermits() > 0) {
                            limitSemaphore.acquire();   // blocks a permit
                            multithread = new Threads(linksOnThePreviousLevel.get(j));
                            multithread.start();
                            //    multithread.join();
                        } else {
                            for (int k = 0; k < arguments.getNoOfThreads(); k++) {
                                multithread.join();
                                linksOnThePreviousLevel.addAll(multithread.getFinalList());  // extended list
                                limitSemaphore.release();   // release a permit
                            }
                        }
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
