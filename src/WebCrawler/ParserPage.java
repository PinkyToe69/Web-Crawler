package WebCrawler;

import java.util.ArrayList;

public class ParserPage implements Parser {

    private ArrayList<String> linkList;

    public ParserPage(ArrayList<String> linkList) {
        this.linkList = new ArrayList<>();
    }

    public ArrayList<String> getLinkList() {
        return linkList;
    }

    public void parse() {

    }
}
