package WebCrawler;

import java.util.ArrayList;

public class Visited {
    private ArrayList<Link> listOfNodes;

    public Visited(ArrayList<Link> listOfNodes) {
        this.listOfNodes = listOfNodes;
    }

    public ArrayList<Link> getListOfNodes() {
        return listOfNodes;
    }

    public void appendList(Link newLink){
        if(listOfNodes.contains(newLink))
            return;
        else {
            listOfNodes.add(newLink);
        }
    }
}
