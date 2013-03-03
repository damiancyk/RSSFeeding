package damian.rss;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
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

	Integer tests;
	Integer timeout;

	@Before
	public void prepare() {
		tests = 10;
		timeout = 3000;
	}

	@Ignore
	@Test
	public void testAllOk() throws IOException {
		assertEquals(null,rssService
				.createDomainList(
						"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
						10, 3000));
	}

	@Test
	public void testLowTimeout() throws IOException {
		rssService
				.createDomainList(
						"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
						10, 500);
	}

	@Test
	public void testZeroDomain() throws IOException {
		Integer domainCount;
		Boolean success = (domainCount = rssService
				.createDomainList(
						"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
						0, 3000).size()) != null;
		System.out.println("\n ===testZeroDomain===\n==domains created: "
				+ domainCount + ", success:" + success + "");
	}

	@Test
	public void testManyDomain() throws IOException {
		Integer domainCount;
		Boolean success = (domainCount = rssService
				.createDomainList(
						"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
						1000, 3000).size()) != null;
		System.out.println("\n ===testManyDomain===\n==domains created: "
				+ domainCount + ", success:" + success + "");
	}

	@Test
	public void testInvalidUrlString() throws IOException {
		rssService.createDomainList("rtdbgrdg", 10, 3000);
	}

	@Test
	public void testInvalidUrlHost() throws IOException {
		rssService.createDomainList("http://codility.com/", 10, 3000);
	}

	@Test
	public void testInvalidUrlCommand() throws IOException {
		rssService
				.createDomainList(
						"http://www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatniooo",
						10, 3000);
	}

	@Test
	public void testInvalidUrlHttp() throws IOException {
		rssService
				.createDomainList(
						"www.nazwa.pl/gielda-domen/najciekawsze-domeny/ostatnio-dodane",
						10, 3000);
	}

}
