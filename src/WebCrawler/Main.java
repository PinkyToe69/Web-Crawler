package WebCrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Crawler application = Crawler.getInstance();
        application.run();

        /** Testare BuilSitemap

        ArrayList<String> children_level1=new ArrayList<String>();
        children_level1.add("child1");
        children_level1.add("child2");
        children_level1.add("child3");
        ArrayList<String> content_level1=new ArrayList<String>();
        content_level1.add("content1");
        content_level1.add("content2");
        content_level1.add("content3");
        Link root=new Link(content_level1, "root", children_level1);
        root.setParrentLink(null);

        ArrayList<String> children_level2A=new ArrayList<String>();
        ArrayList<String> children_level2B=new ArrayList<String>();
        ArrayList<String> children_level2C=new ArrayList<String>();
        children_level2A.add("child4");
        children_level2A.add("child5");
        children_level2B.add("child6");
        children_level2B.add("child7");
        children_level2C.add("child8");
        ArrayList<String> content_level2A=new ArrayList<String>();
        ArrayList<String> content_level2B=new ArrayList<String>();
        ArrayList<String> content_level2C=new ArrayList<String>();
        content_level2A.add("contentChild1");
        content_level2B.add("contentChild2");
        content_level2C.add("contentChild3");

        Link child1=new Link(content_level2A, "child1", children_level2A);
        Link child2=new Link(content_level2B, "child2", children_level2B);
        Link child3=new Link(content_level2C, "child3", children_level2C);

        child1.setParrentLink("root");
        child2.setParrentLink("root");
        child3.setParrentLink("root");

        ArrayList<Link> listOfNodes = new ArrayList<Link>();
        listOfNodes.add(root);
        listOfNodes.add(child1);
        listOfNodes.add(child2);
        listOfNodes.add(child3);

        Visited visited = new Visited(listOfNodes);
        BuildSitemap sitemap = new BuildSitemap("sitemap.txt", visited);
        sitemap.createSitemap();

         */
    }
}