package com.eva.rpc.hadooprpcserver;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import com.eva.rpc.hadooprpcserver.demo.AddAction;
import com.eva.rpc.hadooprpcserver.demo.LoginAction;
import com.eva.rpc.hadooprpcserver.impl.AddActionImpl;
import com.eva.rpc.hadooprpcserver.impl.LoginActionImpl;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static final int port = 10000;
	public static final String host = "10.1.2.249";
	
    public static void main( String[] args ) throws IOException
    {
    	Configuration conf =  new Configuration(); 
    	RPC.Builder builder = new RPC.Builder(conf);
    	builder.setBindAddress(host).setPort(port);
    	
    	builder.setInstance(new AddActionImpl()).setProtocol(AddAction.class);
    	builder.setInstance(new LoginActionImpl()).setProtocol(LoginAction.class);
    	Server server = builder.build();
    	server.start();
        System.out.println( "启动完成!" );
    }
}
