package WebCrawler;

public class Link {

    private string content,currentLinkName,parrentLink;
    private ArrayList<String>childrenLinks;
    public Link(string content,string currentLinkName,string parrentLink,ArrayList<String>childrenLinks){
        this.content=content;
        this.currentLinkName=currentLinkName;
        this.parrentLink=parrentLink;
        this.childrenLinks=childrenLinks;
    }
    public string getLinkName(){
        return currentLinkName;
    }
    public string getParrentLink(){
        return parrentLink;
    }
    public ArrayList<String> getChildrenLink(){
        return childrenLinks;
    }
    public string getContent(){
        return content;
    }

}
