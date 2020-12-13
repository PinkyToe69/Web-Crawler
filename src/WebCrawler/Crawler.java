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
    private Visited visitedLinks;
    private Semaphore limitSemaphore;
    private Link node;

    private Crawler() throws FileNotFoundException {
        this.linkList = new ArrayList<String>();
        this.visitedLinks = new Visited();
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
        limitSemaphore=new Semaphore(arguments.getNoOfThreads());

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
                    node = multithread.getNode();
                    visitedLinks.appendList(node);

                    linksOnTheCurrentLevel.clear();                             // we rewrite the list
                    linksOnTheCurrentLevel.addAll(multithread.getFinalList());
                    limitSemaphore.release();   // release a permit

                }
                else {
                    int nrOfNecessaryThreads;        // number of necessary threads
                    ArrayList<Threads> listOfThreads = new ArrayList<Threads>();
                    nrOfNecessaryThreads = linksOnTheCurrentLevel.size();

                    linksOnThePreviousLevel.addAll(linksOnTheCurrentLevel);

                    for (int j = 0; j < nrOfNecessaryThreads; j++) {
                        if (limitSemaphore.availablePermits() > 0) {
                            limitSemaphore.acquire();   // blocks a permit
                            multithread = new Threads(linksOnThePreviousLevel.get(j));
                            listOfThreads.add(multithread);
                            multithread.start();
                            System.out.println("Starting parsing " + multithread.getCurrentLink());
                            //    multithread.join();
                        } else {
                            for (int k = 0; k < arguments.getNoOfThreads(); k++) {
                                System.out.println("Parsing " + listOfThreads.get(k).getCurrentLink());
                                listOfThreads.get(k).join();
                                node = listOfThreads.get(k).getNode();
                                visitedLinks.appendList(node);
                                linksOnThePreviousLevel.addAll(listOfThreads.get(k).getFinalList());  // extended list
                                limitSemaphore.release();   // release a permit
                            }
                            listOfThreads.clear();
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
