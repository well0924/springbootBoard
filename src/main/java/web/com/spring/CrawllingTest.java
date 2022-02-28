package web.com.spring;

import java.util.Iterator;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawllingTest {
	public static void main(String[] args) {
		String url ="http://www.card09.com/search_result.php?search=total&searchstring=%BF%A4%B8%AE%B8%E0%C6%B2+%C8%F7%BE%EE%B7%CE";
		Document doc =null;
		
		try {
			doc = Jsoup.connect(url).get();
			Elements element = doc.select("td");
			
			System.out.println("====================================");
			Iterator<Element>ie1 = element.select("font").iterator();
			
			while(ie1.hasNext()) {
				System.out.println(ie1.next().text());
			System.out.println("========================================");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
