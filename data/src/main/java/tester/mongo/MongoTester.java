package tester.mongo;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import database.datamodel.CrawledDocument;
import database.mongo.MongoManager;
import database.tupple.UrlTransformer;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

/**
 * Created by rotem.perets on 6/15/14.
 */
public class MongoTester {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        MongoManager manager = new MongoManager();

        Set<String> names = manager.getDatabaseCollectionNames("neotrex");
        for(String n : names){
            DBCursor cur = manager.getCollectionByName("neotrex", n).find();
            System.out.println(cur.count() + " items in " + n);
//            for(DBObject o : cur){
//                System.out.println(o.toString());
//            }
        }

//        List<DBObject> items = manager.getDocuments("neotrex", "metadata", "type", "site");
//        for(DBObject o : items){
//            System.out.println(o.toString());
//        }


        //manager.getCollectionByName("neotrex","yoledet").drop();
        //testGetAll(manager);
        //testBasic(manager);
        //testGet(manager);
    }

    private static void testGetAll(MongoManager manager) {
        DBCollection coll = manager.getCollectionByName("neotrex", "yoldot");
        List<DBObject> objs = coll.find().toArray();
        for(DBObject o : objs){
            System.out.println(o.toString());
        }
    }

    private static void testGet(MongoManager manager) {
        DBCollection coll = manager.getCollectionByName("neotrex", "yoldot");
        List<DBObject> objs = manager.getDocuments("neotrex", "yoldot", "parent_url", null);
        Set<String> urls = new UrlTransformer(objs).toUrlList();
        for(String o : urls){
            System.out.println(o);
        }
    }

    private static void testBasic(MongoManager manager) {
        if(1 == 2){
            CrawledDocument doc = new CrawledDocument();
            doc.setId("JHGFR65TF");
            doc.setUrl("https://google.com");
            doc.setParentUrl("https://gmail.com");
            doc.setPageText("dfsdfdsfdsfds\ndsfsdfdsfdsfdsfs dsfsd  fdsdf sfd\n fsdfsdf dsf dssd.");

            WriteResult db = manager.insertDocument(doc, "neotrex", "yoldot");
            System.out.println(db.toString());
        }

        if(1 == 2){

            List<String> names = manager.getDatabaseNames();
            for (String name : names){
                System.out.println(name);
            }

            for (String name : names){
                Set<String> collNames = manager.getDatabaseCollectionNames(name);
                for(String collName : collNames){
                    System.out.println(name + " - " + collName);
                }
            }
        }
        if(1 == 1){
            List<String> names = manager.getDatabaseNames();
            for (String name : names){
                System.out.println(name);
                if(name.equals("neotrex")){
                    Set<String> collNames = manager.getDatabaseCollectionNames(name);
                    System.out.println("Found " + collNames.size() + " items");
                    for(String collName : collNames){
                        System.out.println(collName);
                        DBCollection collection = manager.getCollectionByName(name, collName);
                        DBCursor cursor = collection.find();
                        while(cursor.hasNext()){
                            DBObject object = cursor.next();
                            System.out.println(object.toString());
                        }
                    }
                }
            }
        }
    }
}
