package damian.rss.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import damian.rss.model.Domain;
import damian.rss.service.RssService;

@Controller
public class RssController {

	private List<Domain> elementList;

	@Autowired
	private RssService rssService;

	@RequestMapping(value = "/gielda-domen.rss")
	public ModelAndView rssPage() {
		
		try {
			elementList = rssService
					.createDomainList(
							"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane", 10, 3000);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewer");
		mav.addObject("feedContent", elementList);

		return mav;
	}

}
