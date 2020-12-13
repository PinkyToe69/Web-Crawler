package WebCrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Crawler application = Crawler.getInstance();
        //application.run();

        ArrayList<String> rootChildren = new ArrayList<>();
        rootChildren.add("docs");
        rootChildren.add("js");
        Link l1 = new Link("c","mta.ro",null, rootChildren);
        ArrayList<String> docsChildren = new ArrayList<>();
        docsChildren.add("2019");
        docsChildren.add("2020");
        Link l2 = new Link("c", "docs", "root", docsChildren);
        Link l3 = new Link("c", "2019", "docs", null);
        ArrayList<String> _2020children = new ArrayList<>();
        _2020children.add("img1.jpg");
        _2020children.add("prezentare.html");
        Link l4 = new Link("c", "2020", "docs", _2020children);
        ArrayList<String> jsChildren = new ArrayList<>();
        jsChildren.add("index.js");
        Link l5 = new Link("c", "js", "root", jsChildren);
        Link l6 = new Link("c", "index.js", "js", null);

        ArrayList<Link> vis = new ArrayList<>();
        vis.add(l1);
        vis.add(l2);
        vis.add(l3);
        vis.add(l4);
        vis.add(l5);
        vis.add(l6);
        Visited visited = new Visited(vis);

        BuildSitemap buildSitemap = new BuildSitemap("sitemap.txt", visited);

        buildSitemap.createSitemap();
    //    ParserPage p = new ParserPage("https://mta.ro/");
    //    p.parse();
    //    p.getLinkList();
    //    p.getLinkContent();
    }
}