package metadata;

import java.util.HashSet;

/**
 * Created by rotem.perets on 6/10/14.
 */
public class SiteMetadata {
    String type = "site";
    String databaseName;
    String collectionName;
    String siteName;
    Integer numberOfCrawlers;
    String siteSeed;
    HashSet<String> visitedUrls = new HashSet<>();
    HashSet<Integer> visitedUrlHashCodes = new HashSet<>();
    HashSet<String> shouldVisitOnceUrls = new HashSet<>();
    HashSet<String> shouldVisitMultiUrls = new HashSet<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public SiteMetadata(String name, String dbName, String collName){
        this.siteName = name;
        this.databaseName = dbName;
        this.collectionName = collName;
        this.numberOfCrawlers = 3;
    }

    public HashSet<String> getShouldVisitMultiUrls() {
        return shouldVisitMultiUrls;
    }

    public void setShouldVisitMultiUrls(HashSet<String> shouldVisitMultiUrls) {
        this.shouldVisitMultiUrls = shouldVisitMultiUrls;
    }

    public String getSiteSeed(){
        return siteSeed;
    }

    public void setSiteSeed(String siteSeed){
        this.siteSeed = siteSeed;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getNumberOfCrawlers() {
        return numberOfCrawlers;
    }

    public void setNumberOfCrawlers(Integer numberOfCrawlers) {
        this.numberOfCrawlers = numberOfCrawlers;
    }

    public HashSet<String> getVisitedUrls() {
        return visitedUrls;
    }

    public void setVisitedUrls(HashSet<String> visitedUrls) {
        this.visitedUrls = visitedUrls;
    }

    public void setVisitedUrl(String visitedUrl) {
        this.visitedUrls.add(visitedUrl);
    }

    public HashSet<Integer> getVisitedUrlHashCodes() {
        return visitedUrlHashCodes;
    }

    public void setVisitedUrlHashCodes(HashSet<Integer> visitedUrlHashCodes) {
        this.visitedUrlHashCodes = visitedUrlHashCodes;
    }

    public void setVisitedUrlHashCode(Integer visitedUrlHashCode) {
        this.visitedUrlHashCodes.add(visitedUrlHashCode);
    }

    public HashSet<String> getShouldVisitOnceUrls() {
        return shouldVisitOnceUrls;
    }

    public void setShouldVisitOnceUrls(HashSet<String> shouldVisitOnceUrls) {
        this.shouldVisitOnceUrls = shouldVisitOnceUrls;
    }

    public void setShouldVisitUrl(String shouldVisitUrl) {
        this.shouldVisitOnceUrls.add(shouldVisitUrl);
    }

    public boolean shouldVisit(String url){

        for(String u : shouldVisitMultiUrls){
            if(url.toLowerCase().startsWith(u.toLowerCase())){
                return true;
            }
        }

        for(String u : shouldVisitOnceUrls){
            if(url.toLowerCase().startsWith(u.toLowerCase())){
                for(Integer o : visitedUrlHashCodes){
                    if(o == url.toLowerCase().hashCode()){
                        System.out.println("Url already visited " + url);
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }

    public void addVisitedUrl(String url){
        visitedUrls.add(url);
        visitedUrlHashCodes.add(url.toLowerCase().hashCode());
    }
}
