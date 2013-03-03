package damian.rss.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import damian.rss.model.Domain;

@Service
public class RssServiceImpl implements RssService {

	public List<Domain> createDomainList(String urlS, Integer count,
			Integer timeout) throws IOException {
		List<Domain> elementList = new ArrayList<Domain>();

		Connection connection = Jsoup.connect(urlS);
		connection.timeout(timeout);
		Document doc = connection.get();
		Elements media = doc.select("tr");
		// TODO timeouty na read/connect
		int i = 0;
		for (Element element : media) {
			if (i == count)
				break;
			if (element.select("td").hasClass("a-left")) {
				elementList.add(createDomain(element));
				i++;
			}
		}
		return elementList;
	}

	private Domain createDomain(Element element) {
		String title = null, link = null, description = null;

		title = element.select("td[class=a-left]").select("a").text();
		link = element.select("td[class=a-left]").select("a").attr("href")
				.toString();
		description = element.select("td[class=a-right]").first().text();

		Domain elementRss = new Domain();
		elementRss.setTitle(title);
		elementRss.setUrl(link);
		elementRss.setSummary(description);

		// System.out.println("\n--domain " + i + "--");
		// System.out.println("title: " + title);
		// System.out.println("link: " + link);
		// System.out.println("description: " + description);

		return elementRss;
	}
}
