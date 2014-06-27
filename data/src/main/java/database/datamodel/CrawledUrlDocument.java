package database.datamodel;

/**
 * Created by rotem.perets on 6/11/14.
 */
public class CrawledUrlDocument implements ICrawledDocument {
    String type = "url";
    String url;
    String urlHash;
    String site;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = (type == null) ? "" : type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = (url == null) ? "" : url;
    }

    public String getUrlHash() {
        return urlHash;
    }

    public void setUrlHash(String urlHash) {
        this.urlHash = (urlHash == null) ? "" : urlHash;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = (site == null) ? "" : site;
    }
}
