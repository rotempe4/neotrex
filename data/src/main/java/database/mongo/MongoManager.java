package database.mongo;

import com.mongodb.*;
import database.datamodel.CrawledDocument;
import database.datamodel.CrawledUrlDocument;
import database.datamodel.ICrawledDocument;
import database.IDataBaseManager;
import metadata.SiteMetadata;
import sun.management.resources.agent;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by rotem.perets on 6/11/14.
 */
public class MongoManager implements IDataBaseManager {
    MongoClient mongoClient;

    public MongoManager() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
    }

    public List<String> getDatabaseNames() {

        return mongoClient.getDatabaseNames();
    }

    public DB getDatabaseByName(String databaseName){
        return mongoClient.getDB(databaseName);
    }

    public Set<String> getDatabaseCollectionNames(String databaseName) {
        DB db = getDatabaseByName(databaseName);
        return db.getCollectionNames();
    }

    public DBCollection getCollectionByName(String databaseName, String collectionName){
        DB db = getDatabaseByName(databaseName);
        DBCollection collection = db.getCollection(collectionName);
        return collection;
    }

    public WriteResult insertDocument(CrawledDocument document, String databaseName, String collectionName){
        DBCollection bdCollection = getCollectionByName(databaseName, collectionName);
        BasicDBObject doc = new BasicDBObject("type", document.getType())
                .append("id", document.getId())
                .append("url", document.getUrl())
                .append("parent_url", document.getParentUrl())
                .append("page_text", document.getPageText());
        return bdCollection.insert(doc);
    }

    public WriteResult insertDocument(CrawledUrlDocument document, String databaseName, String collectionName){
        DBCollection bdCollection = getCollectionByName(databaseName, collectionName);
        BasicDBObject doc = new BasicDBObject("type", document.getType())
                .append("url", document.getUrl())
                .append("url_hash", document.getUrlHash())
                .append("site", document.getSite());
        return bdCollection.insert(doc);
    }

    public WriteResult insertMetadata(SiteMetadata metadata){
        return insertMetadata(metadata, metadata.getDatabaseName(), metadata.getCollectionName());
    }

    public WriteResult insertMetadata(SiteMetadata metadata, String databaseName, String collectionName){
        DBCollection bdCollection = getCollectionByName(databaseName, collectionName);
        BasicDBObject doc = new BasicDBObject("type", metadata.getType())
                .append("seed", metadata.getSiteSeed())
                .append("name", metadata.getSiteName());

        List<BasicDBObject> visitOnce = new ArrayList<>();
        for(String url : metadata.getShouldVisitOnceUrls()){
            visitOnce.add(new BasicDBObject("url", url));
        }
        doc.put("visit_once", visitOnce);

        List<BasicDBObject> visitMulti = new ArrayList<>();
        for(String url : metadata.getShouldVisitMultiUrls()){
            visitMulti.add(new BasicDBObject("url", url));
        }
        doc.put("visit_multi", visitMulti);

        return bdCollection.insert(doc);
    }

    public DB createDatabase(String name){
        return mongoClient.getDB(name);
    }

    public DBCollection createCollection(String dbName, String name){
        return mongoClient.getDB(dbName).getCollection(name);
    }

    public List<DBObject> getDocuments(String dbName, String collName, String key, String val){
        return getCollectionByName(dbName, collName).find(new BasicDBObject(key, val)).toArray();
    }
}
