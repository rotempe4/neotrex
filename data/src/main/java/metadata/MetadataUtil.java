package metadata;

/**
 * Created by rotem.perets on 6/10/14.
 */
public class MetadataUtil {
    SiteMetadata siteMetadata;
    public MetadataUtil(SiteMetadata siteMetadata){
        this.siteMetadata = siteMetadata;
    }

    public void reportVisitedUrl(String url){
        siteMetadata.setVisitedUrl(url);
        siteMetadata.setVisitedUrlHashCode(url.toLowerCase().hashCode());
    }

    public boolean isUrlVisited(String url){
        return false;
    }

    public boolean isMatchingUrlPattern(String url){
        return false;
    }
}
