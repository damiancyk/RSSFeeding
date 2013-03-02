package damian.rss;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import damian.rss.controller.RssController;
import damian.rss.service.RssService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestRss {
	Class c = RssController.class;

	@Autowired
	private RssService rssService;

	@Test
	public void testAllOk() throws IOException {
		rssService
				.rssParseHtml("http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane");
	}

	// @Test
	// public void testInvalidUrl() throws NoSuchMethodException,
	// SecurityException, IllegalAccessException,
	// IllegalArgumentException, InvocationTargetException {
	// Method rssParseHtml = c.getDeclaredMethod("rssParseHtml", String.class);
	// rssParseHtml.setAccessible(true);
	// rssParseHtml.invoke(rssService, new String("ewf"));
	// }

}
