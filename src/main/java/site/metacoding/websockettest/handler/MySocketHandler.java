package site.metacoding.websockettest.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class MySocketHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> socketSession = new ArrayList<WebSocketSession>();

    // 메세지를 보낼 때 실행되는 메서드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        for (WebSocketSession sess : socketSession) {
            sess.sendMessage(message);
        }
    }

    // 클라이언트 접속 시 실행되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        socketSession.add(session);

        log.info(session + " 클라이언트 접속");
    }

    // 클라이언트 접속 해제시 실행되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " 클라이언트 접속 해제");
        socketSession.remove(session);
    }
}
