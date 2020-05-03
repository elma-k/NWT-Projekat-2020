package com.system.systemevents;

import com.system.systemevents.db.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import com.system.systemevents.EventsServiceGrpc;

@Service
public class EventsServiceImpl extends EventsServiceGrpc.EventsServiceImplBase {
    private Logger logger = LogManager.getLogger(getClass());

    private final EventRequestRepository eventRequestRepository;

    public EventsServiceImpl(
            EventRequestRepository eventRequestRepository
    ) throws IOException {
        this.eventRequestRepository = eventRequestRepository;

        Server server = ServerBuilder.forPort(8089)
                .addService(this)
                .build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!");
    }


    @Override
    public void hello(
            com.system.systemevents.EventRequest request, StreamObserver<EventResponse> responseObserver
    ) {
        logger.info(String.format(
                "New event received: %s %s %s %s %s %s",
                request.getActionTimestamp(),
                request.getServiceName(),
                request.getUserId(),
                request.getActionType(),
                request.getResourceName(),
                request.getResponseType()
        ));

        EventResponse response = EventResponse.newBuilder()
                .setEventResponseText("OkiDoki") // todo nigdje nije definisan odgovor RPCa?
                .build();

        com.system.systemevents.db.entity.EventRequest dbEntity = new com.system.systemevents.db.entity.EventRequest(
                null,
                Timestamp.from(Instant.ofEpochSecond(request.getActionTimestamp().getSeconds())),
                request.getServiceName(),
                request.getUserId(),
                Optional.ofNullable(request.getActionType()).map(Enum::name).orElse(null),
                request.getResourceName(),
                Optional.ofNullable(request.getResponseType()).map(Enum::name).orElse(null)
        );

        eventRequestRepository.save(dbEntity);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
