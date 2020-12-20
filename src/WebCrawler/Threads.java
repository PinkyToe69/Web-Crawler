package WebCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Aceasta clasa este alcatuita doar din membrii privati care
 * alcatuiesc continutul unui url. Mosteneste clasa Thread pentru
 * functionalitatea multithreading a aplicatiei.
 *
 * <p>Se suprascrie functia run, unde se apeleaza un metodele
 * din clasa ParserPage. Auxiliar, se foloseste un mutex pentru
 * a evita situatiile de conflict intre thread-uri</p>.
 *
 * <p>Dupa ce se parseaza pagina, toate datele sunt salvate intr-un
 * obiect de tip Link</p>.
 *
 * @author Liviu Floristean
 * @see ParserPage
 * @see Link
 */

public class Threads extends Thread {
    /**
     * <b>currentLink</b> reprezinta numele url-ului care se parseaza acum.
     *
     * <b>parsedList</b> este un obiect de tip ParserPage care parseaza
     * pagina cu url-ul primit.
     *
     * <b>node</b> este un obiect de tip Link in care se salveaza toate
     * datele obtinute in urma parsarii.
     *
     * <b>childLinks</b>> este o lista de obiecte de tip String cu care
     * se salveaza toate link-urile gasite in url-ul curent.
     *
     * <b>executionMutex</b> reprezinta un mutex prin care se asigura
     * excluziunea mutuala in functia run().
     *
     */
    private String currentLink;
    private ParserPage parsedList;
    private Link node;
    private ArrayList<String> childLinks;
    private final Lock executionMutex;

    /**

     * Constructorul clasei Threads.
     * @param linkPassed este link-ul care trebuie parsat.
     */
=======
            * Constructorul clasei Threads.
     * @param linkPassed este link-ul care trebuie parsat.
            */


    public Threads(String linkPassed) {
        currentLink = linkPassed;
        parsedList = new ParserPage(currentLink);
        childLinks = new ArrayList<String>();
        executionMutex = new ReentrantLock(false);
    }

    /**
     * Aceasta metoda este folosita pentru a lua link-ul
     * care este parsat acum.
     * @return va returna numele link-ului.
     */

=======

    public String getCurrentLink() {
        return currentLink;
    }

    /**
     * Aceasta metoda este folosita pentru a se lua lista
     * de link-uri copii in urma parsarii.
     * @return va da o lista care va contine fiecare copil.
     */

    public ArrayList<String> getFinalList() {
        return childLinks;
    }

    /**
     * Aceasta metoda este folosita pentru a lua nodul obtinut
     * cu elementele fiecarui link.
     * @return va returna un obiect de tip Link.
     */

    public Link getNode() {
        return node;
    }

    /**
     * Aceasta metoda este suprascrisa pentru a putea fi folosita
     * prin thread-uri.
     *
     * <p>Se foloseste mutexul pentru excluziunea mutuala. Fiecare
     * thread va apela metodele obiectului ParserPage, dupa care isi
     * va salva lista de copii si continutul gasit.</p>
     *
     * <p>Prin datele obtinute din obiectul ParserPage, thread-ul va
     * construi un obiect de tip Link in care va salva tot ce a gasit
     * in link-ul transmis ca parametru.</p>
     *
     * <p>Metoda abordeaza mecanisme de tratare a exceptiilor.</p>
     */

=======


    @Override
    public void run() {
        ArrayList<String> content = new ArrayList<String>();

        try {
            executionMutex.lock();
            parsedList.parse();
            childLinks.addAll(parsedList.getLinkList());
            content.addAll(parsedList.getLinkContent());

            node=new Link(content, currentLink, childLinks);

            executionMutex.unlock();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}