package crawler.helper;

import model.Site;

/**
 * Created by rotem.perets on 5/29/14.
 */
public class DecisionManager {
    private Site site;

    public DecisionManager(Site site){
        this.site = site;
    }

    public boolean shouldVisit(String url){

        return true;
    }

    public void reportVisited(String url){

    }
}
