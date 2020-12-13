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
        fileWriter.write(root.getLinkName());
        fileWriter.write('\n');
        ArrayList<String> childrenNames = root.getChildrenLink(); // url-urile copiilor ca stringuri
        if(childrenNames == null){
            return;
        }
        for(String childName : childrenNames){
            Link child = getChild(childName);
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
