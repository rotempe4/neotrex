package crawler;

import database.IDataBaseManager;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import metadata.SiteMetadata;

/**
 * Created by rotem.perets on 6/12/14.
 */
public class CrawlerConfiguration extends CrawlConfig {
    IDataBaseManager dbManager;
    SiteMetadata siteMetadata;

    public CrawlerConfiguration(IDataBaseManager dbManager, SiteMetadata siteMetadata){
        this.dbManager = dbManager;
        this.siteMetadata = siteMetadata;
    }

    public IDataBaseManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(IDataBaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public SiteMetadata getSiteMetadata() {
        return siteMetadata;
    }

    public void setSiteMetadata(SiteMetadata siteMetadata) {
        this.siteMetadata = siteMetadata;
    }
}
