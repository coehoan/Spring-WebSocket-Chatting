package site.metacoding.websockettest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;
import site.metacoding.websockettest.handler.MySocketHandler;

@RequiredArgsConstructor
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    private final MySocketHandler mySocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // /chat으로 웹소켓 서버에 접속하고, CORS *로 모든 외부서버에서 접근이 가능하게 해준다.
        registry.addHandler(mySocketHandler, "ws/chat").setAllowedOrigins("*");
    }

}
