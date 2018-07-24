package cn.luckydeer.websocket.socket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

/**
 * 用户信息获取
 * 
 * @author yuanxx
 * @version $Id: UserInfoMessange.java, v 0.1 2018年7月24日 上午10:46:52 yuanxx Exp $
 */
@ServerEndpoint(value = "/websocket/{userId}")
public class UserInfoMessange {

    private static int                                         onlieCount        = 0;

    private static ConcurrentHashMap<String, UserInfoMessange> userInfoMessanges = new ConcurrentHashMap<>();
    //用户会话
    private Session                                            session;

    private String                                             userId;

    /**
     * 
     * 注解：连接成功
     * @param session
     * @author yuanxx @date 2018年7月24日
     * @throws IOException 
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session)
                                                                                   throws IOException {
        this.session = session;
        System.out.println("连接ok");
        this.userId = userId;
        userInfoMessanges.put(userId, this);
        addOnlineCount();

        if (!CollectionUtils.isEmpty(userInfoMessanges)) {
            for (Map.Entry<String, UserInfoMessange> entry : userInfoMessanges.entrySet()) {
                entry.getValue().session.getBasicRemote().sendText(this.userId + "上线了");
            }
        }

        System.out.println(userId + "加入，在线人数为" + onlieCount + "人");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String toUserId = JSON.parseObject(message).getString("toUserId");
        boolean flag = userInfoMessanges.containsKey(toUserId);

        if (!flag) {
            this.session.getBasicRemote().sendText("您选择的用户不在线！");
            return;
        }

        System.out.println(this.userId + "发送信息给" + toUserId);
        UserInfoMessange obj = (UserInfoMessange) userInfoMessanges.get(toUserId);
        obj.session.getBasicRemote().sendText(
            this.userId + "说:" + JSON.parseObject(message).getString("message"));
    }

    /**
     * 
     * 注解：发生错误时调用
     * @param session
     * @param error
     * @author yuanxx @date 2018年7月24日
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 
     * 注解：
     * @author yuanxx @date 2018年7月24日
     * @throws IOException 
     */
    @OnClose
    public void onClose() throws IOException {
        String userId = this.userId;
        System.out.println(userId + "下线");
        if (!CollectionUtils.isEmpty(userInfoMessanges)) {
            for (Map.Entry<String, UserInfoMessange> entry : userInfoMessanges.entrySet()) {
                if (userId != entry.getKey()) {
                    entry.getValue().session.getBasicRemote().sendText(userId + "下线了");
                }
            }
        }
        userInfoMessanges.remove(userId);
        subOnlineCount(); //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    private synchronized void subOnlineCount() {
        onlieCount -= 1;
    }

    /**
     * 
     * 注解：发送信息
     * @param message
     * @throws IOException
     * @author yuanxx @date 2018年7月24日
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 
     * 注解：在线人数加一
     * @author yuanxx @date 2018年7月24日
     */
    private synchronized void addOnlineCount() {
        UserInfoMessange.onlieCount++;
    }

    /**
     * 
     * 注解：获取当前在线人数
     * @return
     * @author yuanxx @date 2018年7月24日
     */
    private synchronized int getOnlineCount() {
        return onlieCount;
    }

}
