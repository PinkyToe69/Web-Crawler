package WebCrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Crawler application = Crawler.getInstance();
        application.run();
        /*
        ArrayList<String> rootChildren = new ArrayList<>();
        rootChildren.add("docs");
        rootChildren.add("js");
        Link l1 = new Link(null,"mta.ro",null, rootChildren);
        ArrayList<String> docsChildren = new ArrayList<>();
        docsChildren.add("2019");
        docsChildren.add("2020");
        Link l2 = new Link(null, "docs", "root", docsChildren);
        Link l3 = new Link(null, "2019", "docs", null);
        ArrayList<String> _2020children = new ArrayList<>();
        _2020children.add("img1.jpg");
        _2020children.add("prezentare.html");
        Link l7 = new Link(null, "img1.jpg", "2019", null);
        Link l8 = new Link(null, "prezentare.html", "2019", null);
        Link l4 = new Link(null, "2020", "docs", _2020children);
        ArrayList<String> jsChildren = new ArrayList<>();
        jsChildren.add("index.js");
        Link l5 = new Link(null, "js", "root", jsChildren);
        Link l6 = new Link(null, "index.js", "js", null);

        ArrayList<Link> vis = new ArrayList<>();
        vis.add(l1);
        vis.add(l2);
        vis.add(l3);
        vis.add(l4);
        vis.add(l5);
        vis.add(l6);
        vis.add(l7);
        vis.add(l8);
        Visited visited = new Visited(vis);

        BuildSitemap buildSitemap = new BuildSitemap("sitemap.txt", visited);

        buildSitemap.createSitemap();*/
        //ParserPage p = new ParserPage("https://mta.ro/");
       // p.parse();
        //p.getLinkList();
       // p.getLinkContent();
    }
}