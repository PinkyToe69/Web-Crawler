package WebCrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import java.util.concurrent.Semaphore;

/**
 * Această clasă implementează metodele necesare pentru a rula aplicația,
 * și este de tip Singleton (poate avea o singură instanță a clasei
 * la un moment dat)
 * @author Frîncu Andreea (lines 1-65, 187-207)
 * @author Floriștean Liviu (lines 67-185)
 */
public class Crawler {

    /**
     * Descrierea membrilor clasei
     * <b>instance</b> este un membru static de tip Crawler
     * cand vom apela metoda getInstance() se returneaza aceasta variabila
     * in loc sa se instantieze din nou clasa
     *
     * <b>arguments</b> este un membru de tip UserData care contine toate
     * argumentele parsate din fisierul config.txt
     *
     * <b>parser</b> este un membru de tip ParserFile care populeaza obiectul
     * <b>arguments</b> de tip UserData
     *
     * <b>multithread</b> este un obiect de tip Threads
     *
     * <b>linkList</b> este un membru care contine lista de url-uri
     * care trebuie vizitate (din fisierul url.txt)
     *
     * <b>visitedLinks</b> este un membru care contine lista de url-uri vizitate
     *
     * <b>limitSemaphore</b> este un obiect de tip Semaphore care asigura ca se
     * pastreaza numarul de thread-uri cu care trebuie rulata aplicatia
     *
     * <b>node</b> este un obiect de tip Link in care se salveaza toate
     * datele obtinute in urma parsarii
     *
     * <b>logger</b> este un obiect de tip LogManager care va scrie înregistrări
     * diferite în funcție de logLevel într-un fișier
     */
    private static Crawler instance = null;
    private UserData arguments;
    private ParserFile parser;
    private Threads multithread;
    private ArrayList<String> linkList;
    private Visited visitedLinks;
    private Semaphore limitSemaphore;
    private Link node;
    private LogManager logger;

    /**
     * Constructorul clasei <b>Crawler</b>
     * @throws FileNotFoundException
     */
    private Crawler() throws FileNotFoundException {
        this.linkList = new ArrayList<String>();
        this.visitedLinks = new Visited();
        this.parser = new ParserFile();
    }

    /**
     * Metoda care va rula aplicatia
     *
     * <p>Va prelua link-uril primite prin fisierul de intrare si adancimea
     * in functie de care va continua sa parseze link-urile. Functia foloseste
     * un semafor pentru a porni maxim n (unde n este dat in fisierul de configurare)
     * thread-uri pentru executia in paralel a programului</p>
     *
     * <p>Aplicatia va lua fiecare url dat in fisierul de configurare si va
     * parsa toate link-urile pe care le gaseste in continutul acestuia.
     * Acest lucru se va repeta pana cand se atinge nivelul de adancime permis.</p>
     *
     * <p>Parcurgerea se va face multithreaded, in functie de parametrul primit.
     * Pentru radacina se apeleaza start apoi join succesiv deoarece se stie ca
     * este nodul radacina si are un singur url. In cazul copiilor, se va crea o
     * lista de thread-uri, se vor porni, iar executia acestora se va face dupa ce
     * s-a atins numarul maxim de thread-uri in lista. Copiii returnati vor suprascrie
     * lista initiala de url-uri, astfel incat dupa ce se parcurg toate url-urile de
     * nivelul curent, se va avea formata toata lisat de pe nivelul urmator.</p>
     *
     * <p>Obiectul logger va afisa mesaje catre untilizator cu fiecare actiune
     * intreprinsa de aplicatie.</p>
     *
     * <p>Dupa fiecare parsare se va lua un obiect de tip Link in care s-au salvat
     * toate datele despre url-ul parsat. Acel obiect va fi folosit de obiectul
     * visitedLinks pentru a valida unicitatea nodului.</p>
     *
     * <p>In final, se foloseste obiectul buildSitemap pentru crearea sitemap-ului.</p>
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */

    public Crawler run() throws IOException, URISyntaxException, InterruptedException {
        parser.parse();
        arguments = new UserData(parser);
        this.linkList = arguments.getUrlList();
        this.logger = new LogManager(arguments.getLogLevel());


        int nrOfLinksPassed = linkList.size();
        int depth = arguments.getDepthLevel();
        limitSemaphore=new Semaphore(arguments.getNoOfThreads());

        System.out.println("The depth is " + depth);
        System.out.println(nrOfLinksPassed + " links had been passed");

        for(int link = 0; link < nrOfLinksPassed; link++)       // luam fiecare link dat ca parametru
        {
            ArrayList<String> linksOnTheCurrentLevel = new ArrayList<String>();
            ArrayList<String> linksOnThePreviousLevel = new ArrayList<String>();
            ArrayList<Link> listOfNodes = new ArrayList<Link>();

            for (int i = 0; i < depth; i++) {
                if (i == 0)                                                     // daca suntem pe primul nivel
                {
                    limitSemaphore.acquire();                                   // blockeaza (se ia un permit)
                    linksOnTheCurrentLevel.add(linkList.get(link));                // luam primul link dat ca argument
                    multithread = new Threads(linksOnTheCurrentLevel.get(i));   // se va lua doar primul link pentru ca stim ca e radacina
                    multithread.start();
                    System.out.println("Starting parsing " + multithread.getCurrentLink() + " on thread " + multithread.getId());
                    multithread.join();
                    node = multithread.getNode();
                    visitedLinks.appendList(node);

                    linksOnTheCurrentLevel.clear();                             // rescriem lista cu copiii gasiti de radacina
                    linksOnTheCurrentLevel.addAll(multithread.getFinalList());
                    limitSemaphore.release();                                   // permite continuarea executiei (se pune un permit)
                } else {
                    int nrOfNecessaryThreads;                                   // numarul de thread-uri necesare (un thread / un link)
                    ArrayList<Threads> listOfThreads = new ArrayList<Threads>();
                    nrOfNecessaryThreads = linksOnTheCurrentLevel.size();

                    int activeThreads = 0;

                    linksOnThePreviousLevel.addAll(linksOnTheCurrentLevel);

                    for (int j = 0; j < nrOfNecessaryThreads; j++) {
                        if (limitSemaphore.availablePermits() > 0) {
                            limitSemaphore.acquire();                           // blockeaza (se ia un permit)
                            multithread = new Threads(linksOnThePreviousLevel.get(j));
                            listOfThreads.add(multithread);
                            multithread.start();
                            activeThreads++;
                            logger.writeMessage(multithread.getCurrentLink());
                            //   System.out.println("Starting parsing " + multithread.getCurrentLink() + " on thread " + multithread.getId());
                        } else {
                            for (int k = 0; k < activeThreads; k++) {
                                logger.writeMessage(listOfThreads.get(k).getCurrentLink());
                                //    System.out.println("Parsing " + listOfThreads.get(k).getCurrentLink() + " on thread " + listOfThreads.get(k).getId());
                                listOfThreads.get(k).join();
                                node = listOfThreads.get(k).getNode();
                                listOfNodes = visitedLinks.getListOfNodes();

                                for (int o = 0; o < listOfNodes.size(); o++) {
                                    if (node != null) {
                                        if (listOfNodes.get(o).getLinkName() != node.getParrentLink()) {
                                            visitedLinks.appendList(node);
                                        }
                                    }
                                }

                                linksOnThePreviousLevel.addAll(listOfThreads.get(k).getFinalList());  // extended list
                                limitSemaphore.release();                          // permite continuarea executiei (se pune un permit)
                            }
                            listOfThreads.clear();
                            activeThreads = 0;
                        }
                    }
                }
            }
        }

        BuildSitemap buildSitemap = new BuildSitemap("sitemap.txt", visitedLinks);
        buildSitemap.createSitemap();
        return null;
    }

    /**
     * Obține numărul de thread-uri din Crawler
     * @return
     */
    public int getNoOfThreads() {
        return arguments.getNoOfThreads();
    }

    /**
     * Creează un obiect al clasei cu numele instance
     * și îl returnează variabilei
     * @return <b>instance</b>
     * @throws FileNotFoundException
     */
    public static Crawler getInstance() throws FileNotFoundException {
        if (instance == null)
            instance = new Crawler();

        return instance;
    }
}
