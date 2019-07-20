package org.example.helloword.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.base.IServer;

import java.io.IOException;
import java.util.logging.Logger;

public class HelloWorldServer implements IServer {
    private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

    private Server server;
    private int port;

    public HelloWorldServer(int port) {
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        logger.info("server started,listening on "+port);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                logger.severe("*** shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                logger.severe("*** server shut down");
            }
        });
    }

    @Override
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloWorldServer server = new HelloWorldServer(8080);
        server.start();
        server.blockUntilShutdown();
    }
}
