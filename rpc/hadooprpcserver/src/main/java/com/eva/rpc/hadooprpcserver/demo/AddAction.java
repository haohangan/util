package com.eva.rpc.hadooprpcserver.demo;

public interface AddAction {
   static final long versionID = 1L;
   
   public int add(int a,int b);
}
