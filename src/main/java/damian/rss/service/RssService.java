package damian.rss.service;

import java.io.IOException;
import java.util.List;

import damian.rss.model.Domain;

public interface RssService {

	/**
	 * Tworzy liste domen na podstawie adresu url
	 * 
	 * @param count
	 *            maksymalna ilosc domen
	 * @param timeout
	 *            timeout dla polaczenia
	 * @param urlS
	 *            adres url
	 * @return lista domen
	 */
	public List<Domain> createDomainList(String urlS, Integer count,
			Integer timeout) throws IOException;
}
