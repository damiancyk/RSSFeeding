package damian.rss.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Domain {

	String title;
	String url;
	String summary;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}