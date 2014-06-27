package crawler;

import database.mongo.MongoManager;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import metadata.SiteMetadata;

/**
 * Created by rotem.perets on 6/10/14.
 */
public class CrawlerItem implements Runnable{
    SiteMetadata siteMetadata;
    CrawlController controller;

    public CrawlerItem(SiteMetadata siteMetadata){
        try{
            this.siteMetadata = siteMetadata;
            SetCrawlerConfiguration();
        } catch (Exception e){

        }
    }

    private void SetCrawlerConfiguration() throws Exception {
        CrawlerConfiguration config = new CrawlerConfiguration(new MongoManager(), siteMetadata);
        config.setCrawlStorageFolder(CrawlingDefaults.crawlStorageFolder);
        config.setPolitenessDelay(CrawlingDefaults.politenessDelay);
        config.setMaxDepthOfCrawling(CrawlingDefaults.maxDepthOfCrawling);
        config.setMaxPagesToFetch(CrawlingDefaults.maxPagesToFetch);
        config.setResumableCrawling(CrawlingDefaults.resumableCrawling);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        try{
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
            controller.addSeed(siteMetadata.getSiteSeed());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try{
            if(controller != null){
                controller.start(BasicCrawler.class, siteMetadata.getNumberOfCrawlers());
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
