package WebCrawler;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserPage implements Parser {

    private ArrayList<String> linkList;
    private ArrayList<String> linkContent;
    private String url;
    private String urlContent;
    private String filePath;

    public ParserPage(String url) {

        this.linkList = new ArrayList<>();
        this.linkContent=new ArrayList<>();
        this.url = new String();
        this.url = url;
        this.urlContent = new String();
        this.filePath=new String("src/WebCrawler/nume_sugestiv.txt");
    }

    public ArrayList<String> getLinkList() {
       // for (int i = 0; i < linkList.size(); i++) {
       //     System.out.print(linkList.get(i).toString());
       //     System.out.print("\n");
       // //}
        return this.linkList;
    }

    public ArrayList<String> getLinkContent(){
        for(int i=0;i<linkContent.size();i++){
            String [] token=linkContent.get(i).split("/");
            int k= token.length;
            linkContent.set(i,token[k-1]);
         //   System.out.println(linkContent.get(i).toString());
        }
        return this.linkContent;
    }

    public ArrayList<String>init() throws FileNotFoundException {
        ArrayList<String> extension=new ArrayList<>();
        File inputFile=new File(this.filePath);
        Scanner input=new Scanner(inputFile);
        while  (input.hasNext()){
            String line=input.nextLine();
            String [] lineArray=line.split("=");
            for(String s:lineArray){
                s=s+"$";
                extension.add(s);
                //System.out.println(s.toString());
            }
        }
        return extension;
    }

    public void get_urlContent() throws IOException {
        URL obj = new URL(this.url);
        URLConnection con = obj.openConnection();
        InputStream in = con.getInputStream();
        int i;
        do {
            i = in.read();
            if (i != -1) {
                this.urlContent += (char) i;
            }
        } while (i != -1);
        //System.out.print(this.urlContent.toString());
    }

    public void parse() throws IOException {
        this.get_urlContent();

        Pattern pattern = Pattern.compile("href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(urlContent);
        while (matcher.find()) {
            String link = new String();
            link = matcher.group(1);
            if (link.length() > 1) {
                //System.out.println(link.toString());
                int bool0 = link.charAt(0) - 47;
                int bool1 = link.charAt(1) - 47;
                if (bool0 == 0 && bool1 != 0) {
                    String aux = new String();
                    aux = url + link;
                    linkList.add(aux);
                    //System.out.print(aux.toString());
                    //System.out.print("\n");
                } else if (bool0 == 0 && bool1 == 0) {
                    String aux = new String();
                    aux = "https:" + link;
                    linkList.add(aux);
                    //System.out.print(aux.toString());
                    //System.out.print("\n");
                } else if (bool1 != 0) {
                    Pattern pattern1 = Pattern.compile("http");
                    Matcher matcher1 = pattern1.matcher(link);
                    if (matcher1.find()) {
                        linkList.add(link);
                        //System.out.println(link.toString());
                    }
                }
            }
            //System.out.println(matcher.group(1));
        }
        ArrayList<String> extension=new ArrayList<>();
        extension=this.init();
        for(int i=0;i<linkList.size();i++){
            for(int j=0;j<extension.size();j++) {
                Pattern pattern_e = Pattern.compile(extension.get(j));
                Matcher matcher_e = pattern_e.matcher(linkList.get(i));
                if(matcher_e.find()) {
                //System.out.println(linkList.get(i).toString());
                linkContent.add(linkList.get(i));
                linkList.remove(i);
                }
            }
        }
    }

}
