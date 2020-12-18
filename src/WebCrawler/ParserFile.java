package WebCrawler;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ParserFile implements Parser {

    private File configFile;
    private File urlFile;
    ArrayList<String> arguments;
    ArrayList<String> list;
    ArrayList<String> urlList;

    public ParserFile() throws FileNotFoundException {
        this.configFile = new File(Paths.get("").toAbsolutePath().toString() + "\\WebCrawler\\config.txt");
        this.urlFile = new File(Paths.get("").toAbsolutePath().toString() + "\\WebCrawler\\url.txt");
        this.arguments = new ArrayList<>();
        this.list = new ArrayList<>();
    }

    public void parse() throws IOException, URISyntaxException {
        //parse config file
        list = (ArrayList<String>) Files.readAllLines(configFile.toPath(), Charset.defaultCharset());
        for(String line : list){
            String [] val = line.split("=");
            arguments.add(val[1]);
        }

        //parse url file
        urlList = (ArrayList<String>) Files.readAllLines(Paths.get(String.valueOf(urlFile)));
        for (String line : urlList) {
            URL u = new URL(line);
            u.toURI();
            //System.out.println(line);
        }
    }
}
