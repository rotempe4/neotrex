package tester.mongo;

import database.mongo.MongoManager;
import metadata.SiteMetadata;

import java.net.UnknownHostException;

/**
 * Created by rotem.perets on 6/22/14.
 */
public class SiteMetadataTester {
    public static void main(String[] args) throws UnknownHostException {
        SiteMetadata siteMetadata = new SiteMetadata("americanpregnancy", "neotrex", "metadata");
        siteMetadata.setType("site");
        siteMetadata.setSiteSeed("http://americanpregnancy.org/forums/forum.php/");
        siteMetadata.getShouldVisitMultiUrls().add("http://americanpregnancy.org/forums/forumdisplay.php?");
        siteMetadata.getShouldVisitOnceUrls().add("http://americanpregnancy.org/forums/showthread.php?");
        siteMetadata.getShouldVisitOnceUrls().add("http://americanpregnancy.org/forums/member.php?");


        MongoManager manager = new MongoManager();
        manager.insertMetadata(siteMetadata);
    }
}
