package database;

import com.mongodb.WriteResult;
import database.datamodel.CrawledDocument;
import database.datamodel.CrawledUrlDocument;
import database.datamodel.ICrawledDocument;

/**
 * Created by rotem.perets on 6/12/14.
 */
public interface IDataBaseManager {
    WriteResult insertDocument(CrawledDocument crawledDocument, String databaseName, String collectionName);
    WriteResult insertDocument(CrawledUrlDocument crawledDocument, String databaseName, String collectionName);
}
