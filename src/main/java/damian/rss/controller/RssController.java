package damian.rss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import damian.rss.model.SampleContent;

@Controller
public class RssController {

	List<SampleContent> elementList;

	@RequestMapping(value = "/gielda-domen.rss")
	public ModelAndView rssPage() {

		try {
			rssParseHtml("http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane");
		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewer");
		mav.addObject("feedContent", elementList);

		return mav;
	}

	private void rssParseHtml(String urlS) throws IOException {
		Document doc = Jsoup.connect(urlS).get();
		Elements media = doc.select("tr");
		elementList = new ArrayList<SampleContent>();
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

				SampleContent elementRss = new SampleContent();
				elementRss.setTitle(title);
				elementRss.setUrl(link);
				elementRss.setSummary(description);
				elementList.add(elementRss);

				// System.out.println("\n--domena " + i + "--");
				// System.out.println("title: " + title);
				// System.out.println("link: " + link);
				// System.out.println("description: " + description);

				if (i == 10)
					break;
				i++;
			}

		}
	}
}
