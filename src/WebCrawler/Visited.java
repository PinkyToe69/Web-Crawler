package WebCrawler;

import java.util.ArrayList;

/**
 * Clasa implementeaza o metoda de append in care se retin link-urile
 * deja vizitate si se salveaza intr-o lista care va fi prelucrata
 * in BuildSitemap.
 *
 * <p>Metoda de appendList se apeleaza in Crawler in functia run().</p>
 *
 * @author Adina-Luiza Ciobanu
 *
 * @see BuildSitemap
 * @see Crawler
 */

public class Visited {
    /**
     * <b>listOfNodes</b> reprezinta lista in care se salveaza
     * link-urile deja vizitate.
     */
    private ArrayList<Link> listOfNodes;

    /**
     * Constructorul clasei Visited.
     * @param listOfNodes este lista de vizitate.
     */

    public Visited(ArrayList<Link> listOfNodes) {
        this.listOfNodes = listOfNodes;
    }

    /**
     * Constructor al clasei Visited cu ajutorul caruia putem crea o
     * lista de link-uri.
     */

    public Visited() {
        this.listOfNodes=new ArrayList<Link>();
    }

    /**
     * Getter-ul este folosit pentru a prelua lista de link-uri
     * vizitate in constructorul clasei BuildSitemap.
     * @return va prelua lista.
     */

    public ArrayList<Link> getListOfNodes() {
        return listOfNodes;
    }

    /**
     * Metoda de append este folosita in Crawler pentru a adauga
     * link-uri parsate de thread-uri in lista de vizitate.
     *
     * <p>Verifica daca contine deja link-ul, daca da va intoarce
     * return.</p>
     *
     * <p>Daca nu contine deja link-ul, luam un parinte pe care
     * il setam ca null. Daca dimesiunea curenta a listei este 0,
     * newLink va deveni root, ii setam parintele ca parent (null)
     * si il adaugam in lista de noduri vizitate.</p>
     *
     * <p>Daca dimensiunea listei este diferita de 0, inseamna ca
     * am trecut la nivelul urmator. Parcurgem lista de vizitate
     * de doua ori pentru a lua lista de copii de la fiecare link
     * deja vizitat. Comparam ce extragem din lista cu link-ul
     * curent. Daca corespund, ii setam link-ului curent
     * parintele si il adaugam in lista de vizitate.</p>
     *
     * @param newLink este obiect de tip Link in care se retine ce
     *               s-a parsat
     */

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
