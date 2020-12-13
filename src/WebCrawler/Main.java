package WebCrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Crawler application = Crawler.getInstance();
        application.run();

    //    ParserPage p = new ParserPage("https://mta.ro/");
    //    p.parse();
    //    p.getLinkList();
    //    p.getLinkContent();
    }
}