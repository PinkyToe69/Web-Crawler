package WebCrawler;

import java.util.*;
import java.io.*;
import java.net.*;

public class ParserPage implements Parser {

    private ArrayList<String> linkList;
    private String url;
    private String urlContent;

    public ParserPage(String url) {

        this.linkList = new ArrayList<>();
        this.url=new String();
        this.url=url;
        this.urlContent=new String();
    }

    public ArrayList<String> getLinkList() {

        return linkList;
    }

    public void get_urlContent() throws IOException {
        URL obj = new URL(url);
        URLConnection con=obj.openConnection();
        InputStream in=con.getInputStream();

        int i;
        //int k=0;
        do {
            i=in.read();
            //k++;
            if(i!=-1){
                urlContent+=(char)i;
            }
        }while(i!=-1);
        System.out.print(urlContent.toString());
        //System.out.print(k);
    }

    public void parse(){

    }
}
