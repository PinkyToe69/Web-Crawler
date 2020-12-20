package WebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clasa realizeaza constructia si afisare in fisier a sitemap-ului.
 * Foloseste o functie recursiva pentru gestionarea link-urilor.
 *
 * <p>Se preia lista de vizitate din clasa Visited. Pe baza listei
 * se construieste sitemap-ul</p>
 *
 * @author Adina-Luiza Ciobanu
 *
 * @see Visited
 */

public class BuildSitemap {

    /**
     * <b>sitemapFilename</b> este fisierul in care se va salva
     * sitemapul.
     *
     * <b>visitedLinks</b> reprezinta o lista de obiecte de tip
     * Link in care se preia lista de vizitate din Visited.
     */

    private String sitemapFilename;
    private ArrayList<Link> visitedLinks;

    /**
     * Constructorul clasei BuildSitemap
     * @param sitemapFilename
     * @param visited este un obiect de tip Visited pe care il
     *                folosim ca sa putem apela metoda din Visited
     *                si anume getListOfNodes() pentru a popula lista
     *                declarata mai sus (visitedLinks) cu obiectele din
     *                lista de vizitate.
     */

    public BuildSitemap(String sitemapFilename, Visited visited) {
        this.sitemapFilename = sitemapFilename;
        this.visitedLinks=visited.getListOfNodes();
    }

    /**
     * Metoda pe care am realizat-o pentru a putea construi obiecte
     * de tip Link in functie de denumirea link-ului. Am folosit
     * acest mecanism pentru a reapela functia writeMe.
     * @param name este parametrul pe care il ia functia si reprezinta
     *             in functia writeMe denumirea link-ului curent.
     * @return in cazul in care denumirea corespunde cu denumirea obiectului
     * din lista de vizitate, va returna obiectul de tip Link.
     */

    private Link getChild(String name){
        for(int i = 0; i < visitedLinks.size(); i++){
            if(visitedLinks.get(i).getLinkName() == name){
                return visitedLinks.get(i);
            }
        }
        return null;
    }

    /**
     *Aceasta metoda realizeaza scrierea in fisier.
     *
     * <p>Vom afisa cate un tab in functie de adancimea la care ne
     * aflam.</p>
     *
     * <p>Afisam denumirea root-ului si apoi pe noi linii afisam
     * continutul de la acel nivel si copiii acestuia.</p>
     *
     * <p>Am tratat cazul de root separat, iar in rest am afisat
     * in functie de adancime.</p>
     *
     * <p>Luam un obiect de tip Link (child) pe care il vom folosi
     * sa apelam metoda de mai sus getChild(). Am apelat aceasta
     * functie de denumirea fiecarui copil in parte a nodului
     * curent.</p>
     *
     * <p>Reapelam functia recursiva writeMe de noul obiect de tip
     * Link child, fisier si tabs+1 pentru a sublinia ca am avansat
     * cu un nivel in adancime.</p>
     *
     * @param root este nodul curent pe care il prelucram
     * @param fileWriter este fisierul in care scriem
     * @param tabs este o valoare intreaga pe care o folosim pentru a
     *             evidentia nivelul de adancime si a indenta corespunzator.
     * @throws IOException
     */

    private void writeMe(Link root, FileWriter fileWriter, int tabs) throws IOException {
        if(root == null){
            return;
        }
        for(int i = 0; i < tabs; i++){
            fileWriter.write('\t');
        }
        if(tabs==0)
        {
            fileWriter.write(root.getLinkName());
            fileWriter.write('\n');
        }

       for(int k=0;k<root.getContent().size();k++) {
           for (int i = 0; i < tabs; i++) {
               fileWriter.write('\t');
           }
           if(tabs==0)
           {
               fileWriter.write('\t');
           }
           fileWriter.write(root.getContent().get(k));
           fileWriter.write('\n');

       }
        for(int m=0;m<root.getChildrenLink().size();m++)
        {
            for (int i = 0; i < tabs; i++) {
                fileWriter.write('\t');
            }
            fileWriter.write('\t');
            fileWriter.write(root.getChildrenLink().get(m));
            fileWriter.write('\n');

            Link child = getChild(root.getChildrenLink().get(m));
            writeMe(child, fileWriter, tabs + 1);
        }

    }

    /**
     * Metoda creeaza un fisier in care va afisa structura dorita.
     *
     * <p>Vom seta un tabs pe care il vom folosi pentru a indenta
     * link-urile si continutul.</p>
     *
     * <p>Luam un obiect de tip Link pe care il setam initial ca
     * null. Parcurgem lista de vizitate si daca gasim un link al
     * carui parinte este null, atunci setam acel obiect ca noul
     * root.</p>
     *
     * <p>Apelam functia recursiva writeMe de root, file si tabs.</p>
     */

    public void createSitemap(){
        try {
            File file = new File(sitemapFilename);
            if (file.createNewFile()) {
                try {
                    FileWriter fileWriter = new FileWriter(sitemapFilename);
                    int tabs = 0;
                    Link root = null;
                    for(int i = 0; i < visitedLinks.size(); i++){
                        if(visitedLinks.get(i).getParrentLink() == null) { // e root-ul sitemapului
                            root = visitedLinks.get(i);
                            break;
                        }
                    }
                    writeMe(root, fileWriter, 0);

                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
