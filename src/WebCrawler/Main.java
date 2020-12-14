package WebCrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Crawler application = Crawler.getInstance();
        application.run();

    }
}