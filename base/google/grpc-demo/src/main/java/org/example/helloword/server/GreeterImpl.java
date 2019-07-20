package org.example.helloword.server;

import io.grpc.stub.StreamObserver;
import org.example.helloword.proto.GreeterGrpc;
import org.example.helloword.proto.HelloReply;
import org.example.helloword.proto.HelloRequest;

import java.util.logging.Logger;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase{
    private static final Logger logger = Logger.getLogger(GreeterImpl.class.getName());
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        logger.info("get request from "+request.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
