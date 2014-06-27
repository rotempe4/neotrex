package manager;

import crawler.CrawlerItem;
import metadata.SiteMetadata;

/**
 * Created by rotem.perets on 6/22/14.
 */
public class CrawlerRunnable implements Runnable {
    SiteMetadata metadata;

    public CrawlerRunnable(SiteMetadata metadata){
        this.metadata = metadata;
    }

    @Override
    public void run() {
        CrawlerItem crawler = new CrawlerItem(metadata);
        crawler.run();
    }
}
