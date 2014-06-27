package database.tupple;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rotem.perets on 6/17/14.
 */
public class UrlTransformer {
    List<DBObject> items;

    public UrlTransformer(List<DBObject> items){
        this.items = items;
    }

    public Set<String> toUrlList(){
        Set<String> urls = new HashSet<>();
        for(DBObject o : items){
            urls.add(o.get("url").toString());
        }
        return urls;
    }

    public Set<String> toUrlHashList(){
        Set<String> urls = new HashSet<>();
        for(DBObject o : items){
            urls.add(o.get("url_hash").toString());
        }
        return urls;
    }
}
