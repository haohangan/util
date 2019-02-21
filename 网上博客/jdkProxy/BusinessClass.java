package shardTest.state;

import java.util.logging.Logger;

public class BusinessClass implements BCinterface{

    private void insert(){
        Logger.getGlobal().info("insert");
    }

    void delete(){
        Logger.getGlobal().info("delete");
    }

    protected void update(){
        Logger.getGlobal().info("update");
    }

    public void query(){
        Logger.getGlobal().info("query");
    }

    public void query2(){
        Logger.getGlobal().info("query2");
    }

}
