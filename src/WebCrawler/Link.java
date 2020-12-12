package WebCrawler;

import java.util.ArrayList;

public class Link {

    private String content,currentLinkName,parrentLink;
    private ArrayList<String>childrenLinks;
    public Link(String content,String currentLinkName,String parrentLink,ArrayList<String>childrenLinks){
        this.content=content;
        this.currentLinkName=currentLinkName;
        this.parrentLink=parrentLink;
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
    public String getContent(){
        return content;
    }

}
