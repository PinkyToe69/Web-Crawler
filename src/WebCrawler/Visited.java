package WebCrawler;

import java.util.ArrayList;

public class Visited {
    private ArrayList<Link> listOfNodes;

    public Visited(ArrayList<Link> listOfNodes) {
        this.listOfNodes = listOfNodes;
    }

    public Visited() {
        this.listOfNodes=new ArrayList<Link>();
    }

    public ArrayList<Link> getListOfNodes() {
        return listOfNodes;
    }

    public void appendList(Link newLink){
        if(listOfNodes.contains(newLink))
            return;
        else {
            String parent = null;
            if(listOfNodes.size()==0){
                newLink.setParrentLink(parent);
                listOfNodes.add(newLink);
            }
            else {
                for (int i = 0; i < listOfNodes.size(); i++)
                    for (int j = 0; j < listOfNodes.get(i).getChildrenLink().size(); j++) {
                        if (listOfNodes.get(i).getChildrenLink().get(j) == newLink.getLinkName()) {
                            parent = listOfNodes.get(i).getLinkName();
                            newLink.setParrentLink(parent);
                            listOfNodes.add(newLink);
                            break;
                        }
                    }
            }
        }
    }
}
