package WebCrawler;

import java.io.IOException;
import java.util.ArrayList;

public class Threads extends Thread{
    private String currentLink;
    private ParserPage parsedList;
    private ArrayList<Link> listOfNodes;
    private ArrayList<String> childLinks;
    private boolean done = false;

    public Threads(String linkPassed)
    {
        currentLink = linkPassed;
        parsedList = new ParserPage(currentLink);
        listOfNodes = new ArrayList<Link>();
        childLinks = new ArrayList<String>();
    }

    public ArrayList<String> getFinalList()
    {
        return childLinks;
    }

    public boolean getStatus()
    {
        return done;
    }

    @Override
    public void run()
    {
       //   to insert try-catch later...
    //    int numberOfURLs = urlList.size();
        ArrayList<String> content = new ArrayList<String>();

        //   parsedList = new ParserPage(urlList.get(i));
        // won't work without try-catch, we're gonna solve this with the exception class
        try {
            parsedList.parse();
            childLinks.addAll(parsedList.getLinkList());        // save the links we discover
            content.addAll(parsedList.getLinkContent());        // get the content of the link (png, jpg, etc.)

        //    for(int i = 0; i<childLinks.size(); i++)
        //    {
        //        System.out.println("Thread "+ Thread.currentThread().getId() +
        //                " found the following links on link " + currentLink);
        //        System.out.println(childLinks.get(i));
        //        System.out.println();
        //     }
        //    System.out.println("Content size is: " + content.size());
        //    System.out.println("Thread "+ Thread.currentThread().getId() +
        //            " found the following content on link " + currentLink);
        //    for(int j = 0; j<content.size(); j++)
        //    {
        //        System.out.println(content.get(j));
        //    }
        //    System.out.println("============================================");


            //  Here we're gonna need the Link class to add the content and the link list for the current node (link)
            //  ... to do ...

            done = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
