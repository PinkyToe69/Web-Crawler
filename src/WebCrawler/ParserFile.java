package WebCrawler;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Această clasă implmentează interfața Parser
 *
 * <p>Implementează metoda parse(), necesară
 * pentru extragerea datelor necesare din fișerele
 * de configurare</p>
 * @author Frîncu Andreea
 * @see Parser
 */

public class ParserFile implements Parser {

    /**
     * Descrierea membrilor clasei
     * <b>configFile</b> este un obiect de tip File care ia calea fisierului
     *  config.txt pentru extragerea argumentelor
     *
     *  <b>configFile</b> este un obiect de tip File care ia calea fisierului
     *  url.txt pentru extragerea url-urilor
     *
     *  <b>arguments</b> este o lista care va fi poulata cu argumentele extrase
     *  din config.txt
     *
     *  <b>list</b> este un membru de tip lista care ajuta la retinerea liniilor
     *  din fisierle in proces de parsare
     *
     *  <b>urlList</b> este o lista care va fi poulata cu url-urile extrase
     *  url.txt
     */
    private File configFile;
    private File urlFile;
    ArrayList<String> arguments;
    ArrayList<String> list;
    ArrayList<String> urlList;

    /**
     * Constructorul clasei ParserFile
     * @throws FileNotFoundException
     */
    public ParserFile() throws FileNotFoundException {
        this.configFile = new File(Paths.get("").toAbsolutePath().toString() + "\\WebCrawler\\config.txt");
        this.urlFile = new File(Paths.get("").toAbsolutePath().toString() + "\\WebCrawler\\url.txt");
        this.arguments = new ArrayList<>();
        this.list = new ArrayList<>();
    }

    /**
     * Parsează fișierul de configurare și fișierul url.txt
     * Pentru fișierul de config.txt se iau string-urile după "="
     * și se adaugă în lista de argumente pentru a putea fi transmise mai departe
     * Pentru fișierul de url.txt de ia fiecare linie și se adaugă în lista
     * de url-uri. Totodată se verifică ficare url cu ajutorul funcției toURI
     * Această funcție aruncă URISyntaxException dacă această adresă URL nu este
     * formatată strict conform RFC2396 și nu poate fi convertită într-un URI.
     * @throws IOException
     * @throws URISyntaxException
     */
    public void parse() throws IOException, URISyntaxException {
        //parse config file
        list = (ArrayList<String>) Files.readAllLines(configFile.toPath(), Charset.defaultCharset());
        for(String line : list){
            String [] val = line.split("=");
            arguments.add(val[1]);
        }

        //parse url file
        urlList = (ArrayList<String>) Files.readAllLines(Paths.get(String.valueOf(urlFile)));
        for (String line : urlList) {
            URL u = new URL(line);
            u.toURI();
            //System.out.println(line);
        }
    }
}
