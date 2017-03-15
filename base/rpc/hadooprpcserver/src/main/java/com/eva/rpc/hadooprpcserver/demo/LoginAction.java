package com.eva.rpc.hadooprpcserver.demo;

public interface LoginAction {
    static final long versionID = 1L;
    
    public String saySth(String input);
}
