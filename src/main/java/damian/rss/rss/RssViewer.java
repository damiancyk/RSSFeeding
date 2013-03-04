package damian.rss.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;

import damian.rss.model.Domain;

public class RssViewer extends AbstractRssFeedView {

	@Override
	public void buildFeedMetadata(Map<String, Object> model, Channel feed,
			HttpServletRequest request) {

		feed.setTitle("Gie³da domen");
		feed.setDescription("Ostatnio dodane");
		feed.setLink("http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane");

		super.buildFeedMetadata(model, feed, request);
	}

	@Override
	public List<Item> buildFeedItems(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		@SuppressWarnings("unchecked")
		List<Domain> listContent = (List<Domain>) model.get("feedContent");
		List<Item> items = new ArrayList<Item>(listContent.size());

		for (Domain tempContent : listContent) {

			Item item = new Item();

			Content content = new Content();
			content.setValue(tempContent.getSummary());
			item.setContent(content);

			item.setTitle(tempContent.getTitle());
			item.setLink(tempContent.getUrl());

			items.add(item);
		}

		return items;
	}

}