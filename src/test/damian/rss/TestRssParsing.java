package damian.rss;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import damian.rss.service.RssService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
public class TestRssParsing {

	@Autowired
	private RssService rssService;

	@Test
	public void testElementNull() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Method mCreateDomain = rssService.getClass().getDeclaredMethod(
				"createDomain", Element.class);
		mCreateDomain.setAccessible(true);

		Element element = null;
		mCreateDomain.invoke(rssService, element);
	}
}
