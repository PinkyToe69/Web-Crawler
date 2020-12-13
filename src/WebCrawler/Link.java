package WebCrawler;

import java.util.ArrayList;

public class Link {

    private String currentLinkName,parrentLink;
    private ArrayList<String>childrenLinks;
    private ArrayList<String>content;
    public Link(ArrayList<String>content,String currentLinkName, ArrayList<String>childrenLinks){
        this.content=content;
        this.currentLinkName=currentLinkName;
        this.childrenLinks=childrenLinks;
    }

    public String getLinkName(){
        return currentLinkName;
    }
    public String getParrentLink(){
        return parrentLink;
    }
    public ArrayList<String> getChildrenLink(){
        return childrenLinks;
    }

    public void setParrentLink(String parrentLink) {
        this.parrentLink = parrentLink;
    }

    public ArrayList<String> getContent(){
        return content;
    }

}