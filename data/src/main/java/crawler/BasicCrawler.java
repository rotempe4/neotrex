package crawler;

import database.datamodel.CrawledDocument;
import database.datamodel.CrawledUrlDocument;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

/**
 * Created by rotem.perets on 5/31/14.
 */
public class BasicCrawler extends WebCrawler {

    private final static Pattern FILTERS =
            Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" +
                    "|png|tiff?|mid|mp2|mp3|mp4" +
                    "|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    @Override
    public boolean shouldVisit(WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean shouldVisit = !FILTERS.matcher(href).matches() &&
                ((CrawlerConfiguration)this.getMyController().getConfig()).getSiteMetadata().shouldVisit(href);

        return shouldVisit;
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        CrawledDocument document = new CrawledDocument();
        try{
            document.setId(((Integer)page.getWebURL().getDocid()).toString());
            document.setUrl(page.getWebURL().getURL());
            document.setParentUrl(page.getWebURL().getParentUrl());
            if (page.getParseData() instanceof HtmlParseData) {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                document.setPageText(htmlParseData.getText());
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        }

        try{
            CrawlerConfiguration crawlerConfiguration = (CrawlerConfiguration)this.getMyController().getConfig();
            crawlerConfiguration.getDbManager().insertDocument(
                    document,
                    crawlerConfiguration.getSiteMetadata().getDatabaseName(),
                    crawlerConfiguration.getSiteMetadata().getCollectionName());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        }

        try{
            String visitedUrl = page.getWebURL().getURL();
            CrawlerConfiguration crawlerConfiguration = (CrawlerConfiguration)this.getMyController().getConfig();

            if(!crawlerConfiguration.getSiteMetadata().getVisitedUrlHashCodes().contains(visitedUrl.toLowerCase().hashCode())){

                crawlerConfiguration.getSiteMetadata().addVisitedUrl(visitedUrl);

                CrawledUrlDocument urlDocument = new CrawledUrlDocument();
                urlDocument.setUrl(visitedUrl);
                urlDocument.setUrlHash(((Integer)urlDocument.getUrl().toLowerCase().hashCode()).toString());
                urlDocument.setSite(crawlerConfiguration.getSiteMetadata().getCollectionName());
                crawlerConfiguration.getDbManager().insertDocument(
                        urlDocument,
                        crawlerConfiguration.getSiteMetadata().getDatabaseName(),
                        crawlerConfiguration.getSiteMetadata().getCollectionName());
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        }
    }
}
