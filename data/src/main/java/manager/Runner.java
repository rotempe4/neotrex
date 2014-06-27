package manager;

import com.mongodb.DBObject;
import database.mongo.MongoManager;
import metadata.SiteMetadata;
import metadata.SiteMetadataFactory;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rotem.perets on 5/31/14.
 */
public class Runner {
    static HashSet<String> siteInitialized = new HashSet<>();
    public static void main(String[] args) throws Exception {
        while(true){
            Thread.sleep(3000);
            getSitesToRun();
        }
    }

    private static void getSitesToRun() throws UnknownHostException {
        MongoManager manager = new MongoManager();
        List<DBObject> items = manager.getDocuments("neotrex", "metadata", "type", "site");
        for(DBObject o : items){
            if(!siteInitialized.contains(o.get("name").toString())){
                SiteMetadata metadata = SiteMetadataFactory.create(o);
                new CrawlerRunnable(metadata).run();
                siteInitialized.add(o.get("name").toString());
            }
        }
    }
}
