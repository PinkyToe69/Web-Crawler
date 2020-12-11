package WebCrawler;

import java.io.IOException;
import java.util.ArrayList;

public class Threads extends Thread{
    private String currentLink;
    private ParserPage parsedList;
    private ArrayList<Link> listOfNodes;
    private ArrayList<String> finalListofURLs;

    public Threads(String linkPassed)
    {
        currentLink = linkPassed;
    }


    public ArrayList<String> getFinalList()
    {
        return finalListofURLs;
    }

    public void run()
    {
       //   to insert try-catch later...
    //    int numberOfURLs = urlList.size();
        ArrayList<String> content = new ArrayList<String>();
        ArrayList<String> childLinks = new ArrayList<String>();

        //   parsedList = new ParserPage(urlList.get(i));
        // won't work without try-catch, we're gonna solve this with the exception class
        try {
            parsedList.parse();
            childLinks.addAll(parsedList.getLinkList());       // save the links we discover//    content.addAll(parsedList.get_urlContent());            // save the content - need to teamwork for that
            /**
             * Here we're gonna need the Link class to add the content and the link list for the current node (link)
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
        finalListofURLs.addAll(childLinks);         // we create the new list of url for the next depth level
    }
}
