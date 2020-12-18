package WebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BuildSitemap {
    private String sitemapFilename;
    private ArrayList<Link> visitedLinks;

    public BuildSitemap(String sitemapFilename, Visited visited) {
        this.sitemapFilename = sitemapFilename;
        this.visitedLinks=visited.getListOfNodes();
    }

    private Link getChild(String name){
        for(int i = 0; i < visitedLinks.size(); i++){
            if(visitedLinks.get(i).getLinkName() == name){
                return visitedLinks.get(i);
            }
        }
        return null;
    }

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

    public void createSitemap(){
        try {
            File file = new File(sitemapFilename);
            if (file.createNewFile()) {
                //System.out.println("File created: " + myObj.getName());
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
