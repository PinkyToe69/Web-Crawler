package WebCrawler;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Această interfață este construită pentru implementarea claselor ParserFile
 * și ParserPage care utilizează metoda parse() în moduri diferite
 * @author Frîncu Andreea
 * @see ParserFile
 * @see ParserPage
 */
public interface Parser {

    /**
     * Semnătura metodei parse()
     * <p>Va fi construit corpul functiei in clasele care implementeaza
     * interfata Parser</p>
     * @throws IOException
     * @throws URISyntaxException
     */
    public void parse() throws IOException, URISyntaxException;
}
