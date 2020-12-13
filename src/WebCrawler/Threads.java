package WebCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Threads extends Thread {
    private String currentLink;
    private ParserPage parsedList;
    private Link node;
    private ArrayList<String> childLinks;
    private final Lock executionMutex;

    public Threads(String linkPassed) {
        currentLink = linkPassed;
        parsedList = new ParserPage(currentLink);
        childLinks = new ArrayList<String>();
        executionMutex = new ReentrantLock(false);
    }

    public String getCurrentLink() {
        return currentLink;
    }

    public ArrayList<String> getFinalList() {
        return childLinks;
    }

    public Link getNode() {
        return node;
    }

    @Override
    public void run() {
        ArrayList<String> content = new ArrayList<String>();

        // won't work without try-catch, we're gonna solve this with the exception class
        try {
            executionMutex.lock();
            parsedList.parse();
            childLinks.addAll(parsedList.getLinkList());        // save the links we discover
            content.addAll(parsedList.getLinkContent());        // get the content of the link (png, jpg, etc.)

            node=new Link(content, currentLink, childLinks);

            executionMutex.unlock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}