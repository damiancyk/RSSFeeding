package damian.rss.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import damian.rss.model.Domain;

@Service
public class RssServiceImpl implements RssService{

	public List<Domain> rssParseHtml(String urlS) throws IOException {
		List<Domain> elementList;
		Document doc = Jsoup.connect(urlS).get();
		Elements media = doc.select("tr");
		elementList = new ArrayList<Domain>();
		// TODO timeouty na read/connect
		int i = 1;
		for (Element element : media) {
			String title = null, link = null, description = null;
			if (element.select("td").hasClass("a-left")) {
				title = element.select("td[class=a-left]").select("a").text();
				link = element.select("td[class=a-left]").select("a")
						.attr("href").toString();
				description = element.select("td[class=a-right]").first()
						.text();

				Domain elementRss = new Domain();
				elementRss.setTitle(title);
				elementRss.setUrl(link);
				elementRss.setSummary(description);
				elementList.add(elementRss);

				// System.out.println("\n--domain " + i + "--");
				// System.out.println("title: " + title);
				// System.out.println("link: " + link);
				// System.out.println("description: " + description);

				if (i == 10)
					break;
				i++;
			}

		}

		return elementList;
	}
}
