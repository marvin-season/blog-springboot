package marvin.ink.blogboot.service;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.io.IOException;

public interface WebSocketService {
    /**
     * 连接建立成功调用的方法
     */
    void onOpen(Session session, @PathParam("userName") String userName);

    /**
     * 连接关闭调用的方法
     */
    void onClose();

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    void onMessage(String message, Session session);

    void onError(Session session, Throwable error);

    /**
     * 连接服务器成功后主动推送
     */
    void sendMessage(String message) throws IOException;

    /**
     * 向指定客户端发送消息
     *
     * @param userName
     * @param message
     */
    void sendMessage(String userName, String message);


}
