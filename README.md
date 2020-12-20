WEB CRAWLER

În acest proiect am realizat web crawling, nu și web scrapping, și am afișat o structură de fișiere preluate din url-uri pornind de la un root. Sitemap-ul este afișat corespunzător, dar nu descărcăm și documentele.

How to use:

Pentru a rula iar programul, sitemap.txt trebuie șters.

Comenzile pentru rularea programului din linie de comandă: (se dau din folderul cu proiectul)
Comenzile pentru compilarea claselor:
javac -d "classes" "src/WebCrawler/LogManager.java"

javac -d "classes" "src/WebCrawler/Parser.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/Parser.java" "src/WebCrawler/ParserFile.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/Parser.java" "src/WebCrawler/ParserPage.java"

javac -d "classes" "src/WebCrawler/Link.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/Link.java" "src/WebCrawler/Visited.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/Link.java" "src/WebCrawler/Visited.java" "src/WebCrawler/BuildSitemap.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/Link.java" "src/WebCrawler/ParserPage.java" "src/WebCrawler/Threads.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/ParserFile.java" "src/WebCrawler/UserData.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/ParserFile.java" "src/WebCrawler/UserData.java" "src/WebCrawler/ParserPage.java" "src/WebCrawler/Link.java" "src/WebCrawler/Visited.java" "src/WebCrawler/LogManager.java" "src/WebCrawler/Threads.java" "src/WebCrawler/BuildSitemap.java" "src/WebCrawler/Crawler.java"

javac -d "classes" -classpath "classes" "src/WebCrawler/Crawler.java" "src/WebCrawler/Main.java"

Pentru rularea programului:
java -classpath "classes" WebCrawler.Main