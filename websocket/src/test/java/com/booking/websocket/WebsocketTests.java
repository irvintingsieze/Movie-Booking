package com.booking.websocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebsocketTests {
    @LocalServerPort
    private Integer port;

    private WebSocketStompClient webSocketStompClient;
    private StompSession stompSession;
    private ArrayBlockingQueue arrayBlockingQueue;

    @BeforeEach
    public void setup() {

        try{
            this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(
                    Arrays.asList(new WebSocketTransport(new StandardWebSocketClient()))));
            arrayBlockingQueue = new ArrayBlockingQueue(10);
            webSocketStompClient.setMessageConverter(new StringMessageConverter());
            stompSession = webSocketStompClient
                    .connect(getWsPath(), new StompSessionHandlerAdapter() {})
                    .get(20, SECONDS);
            stompSession.subscribe("/topic/seatid", new StompFrameHandler() {
                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return String.class;
                }
                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    //System.out.println("Received message: " + payload);
                    arrayBlockingQueue.add(payload);
                }
            });
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @Test
    public void testVerifyAdditionToList() throws Exception {

        stompSession.send("/app/seat_booking", "1");
        List<Integer> seatList = new ArrayList<>();
        seatList.add(1);
        assertEquals(seatList.toString(), arrayBlockingQueue.poll(1, SECONDS));
    }

    @Test
    public void testVerifyRemovalFromList() throws Exception {
        Thread newThread1 = new Thread(() -> {
            try{
                stompSession.send("/app/seat_booking", "1");
                assertEquals("[1]", arrayBlockingQueue.poll(1, SECONDS));
                stompSession.send("/app/seat_booking", "1");
                assertEquals("[]", arrayBlockingQueue.poll(1, SECONDS));
            }catch (Exception e){
                System.out.println(e);
            }

        });
        newThread1.start();
    }




    @Test
    public void testVerifyRemovalFromListDisconnect() throws Exception {

        Thread newThread2 = new Thread(() -> {
            try{
                stompSession.send("/app/seat_booking", "5");
                assertEquals("[5]", arrayBlockingQueue.poll(1, SECONDS));
                stompSession.send("/app/seat_booking", "6");
                assertEquals("[5, 6]", arrayBlockingQueue.poll(1, SECONDS));
                stompSession.send("/app/seat_booking", "7");
                assertEquals("[5, 6, 7]", arrayBlockingQueue.poll(1, SECONDS));
                stompSession.send("/app/disconnect", "5,6");
                assertEquals("[7]", arrayBlockingQueue.poll(1, SECONDS));
            }catch (Exception e){
                System.out.println(e);
            }
        });
        newThread2.start();
    }

    private String getWsPath() {
        return String.format("ws://localhost:%d/stomp", port);
    }


}
