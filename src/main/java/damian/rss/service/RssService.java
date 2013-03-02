package damian.rss.service;

import java.io.IOException;
import java.util.List;

import damian.rss.model.Domain;

public interface RssService {
	public List<Domain> rssParseHtml(String urlS) throws IOException;
}
