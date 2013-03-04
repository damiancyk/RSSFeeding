package damian.rss;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import damian.rss.service.RssService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
public class TestRssConnection {

	@Autowired
	private RssService rssService;

	private static int tests;
	private static int timeout;

	@BeforeClass
	public static void initialize() {
		tests = 10;
		timeout = 3000;
	}

	@Test
	public void testAllOk() throws IOException {
		assertEquals(
				"testAllOk",
				tests,
				rssService
						.createDomainList(
								"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
								tests, timeout).size());
	}

	@Test
	public void testLowTimeout() throws IOException {
		assertNotNull("testLowTimeout", rssService
				.createDomainList(
						"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
						tests, 500));
	}

	@Test
	public void testZeroDomain() throws IOException {
		int count = 0;
		assertEquals(
				"testZeroDomain",
				count,
				rssService
						.createDomainList(
								"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
								count, timeout).size());
	}

	@Test
	public void testManyDomain() throws IOException {
		int count = 1000;
		assertEquals(
				"",
				count,
				rssService
						.createDomainList(
								"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
								count, timeout).size());
	}

	@Test
	public void testInvalidUrlString() throws IOException {
		rssService.createDomainList("rtdbgrdg", tests, timeout);
	}

	@Test
	public void testInvalidUrlHost() throws IOException {
		rssService.createDomainList("http://codility.com/", tests, timeout);
	}

	@Test
	public void testInvalidUrlCommand() throws IOException {
		rssService
				.createDomainList(
						"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatniooo",
						tests, timeout);
	}

	@Test
	public void testInvalidUrlHttp() throws IOException {
		rssService
				.createDomainList(
						"www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
						tests, timeout);
	}

}
