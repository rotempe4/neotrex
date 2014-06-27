package metadata;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import database.mongo.MongoManager;
import database.tupple.UrlTransformer;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

/**
 * Created by rotem.perets on 6/22/14.
 */
public class SiteMetadataFactory {
    public static SiteMetadata create(DBObject siteObj) throws UnknownHostException {
        SiteMetadata metadata = new SiteMetadata(
                siteObj.get("name").toString(),
                "neotrex",
                siteObj.get("name").toString().toLowerCase());
        metadata.setSiteSeed(siteObj.get("seed").toString());
        List<BasicDBObject> visitOnce = (List<BasicDBObject>) siteObj.get("visit_once");
        for(BasicDBObject o : visitOnce){
            metadata.getShouldVisitOnceUrls().add(o.get("url").toString());
        }
        List<BasicDBObject> visitMulti = (List<BasicDBObject>) siteObj.get("visit_multi");
        for(BasicDBObject o : visitMulti){
            metadata.getShouldVisitMultiUrls().add(o.get("url").toString());
        }

        Set<String> urls = getUrls(siteObj.get("name").toString().toLowerCase());
        for (String s : urls){
            metadata.getVisitedUrls().add(s);
            metadata.getVisitedUrlHashCodes().add(s.toLowerCase().hashCode());
        }

        return metadata;
    }

    private static Set<String> getUrls(String name) throws UnknownHostException {
        MongoManager manager = new MongoManager();
        DBCollection coll = manager.getCollectionByName("neotrex", name);
        List<DBObject> objs = manager.getDocuments("neotrex", name, "type", "url");
        return new UrlTransformer(objs).toUrlList();
    }
}
