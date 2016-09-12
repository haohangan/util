package intfs.demo;

public interface HelloWorld {
    void initServer();
    
    void initServer(String name);
    
    void call(int id);
    
    String value();
}
