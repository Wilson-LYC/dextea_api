package com.dextea.server;

import cn.hutool.core.util.IdUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;

import javax.security.auth.message.MessageInfo;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/service/{id}")
public class StoreServiceServer {

//    public static XxService xxService;
//
//    @Autowired
//    public void setXxService(XxService xxService){
//        WebSocketServer.xxService = xxService;
//    }

    //存储客户端session信息
    public static Map<String, Session> clients = new ConcurrentHashMap<>();//记录所有的客户端连接

    //存储把不同用户的客户端session信息集合
    public static Map<String, Set<String>> connection = new ConcurrentHashMap<>();//记录每个用户有哪些客户端连接

    //会话id
    private String sid = null;

    //建立连接的信息
    private String storeId;//店铺id

    @OnOpen//建立连接时执行
    public void onOpen(Session session, @PathParam("id") String storeId){
        this.sid = IdUtil.simpleUUID();
        this.storeId = storeId;
        clients.put(this.sid,session);//给每一个session设置一个唯一的id
        //判断该用户是否存在会话集合，不存在则添加 （判断是否是第一次连接）
        Set<String> clientSet = connection.get(storeId);
        if (clientSet == null){
            clientSet = new HashSet<>();
            connection.put(storeId,clientSet);
        }
        clientSet.add(this.sid);//将该用户的会话id添加到会话集合中
    }

    @OnClose//关闭连接时执行
    public void onClose(){
        clients.remove(this.sid);//关闭时，移除该用户的会话
    }

    @OnMessage//接收到客户端消息时执行
    public void onMessage(String message,Session session) {
    }

    @OnError//连接错误时执行
    public void onError(Throwable error){
    }

    //向指定店铺的所有客户端发送消息
    public static JSONObject sendToStoreBySid(String storeId, JSONObject data){
        JSONObject result = new JSONObject();
        //判断该storeId是否为空
        if (!StringUtils.isEmpty(storeId)) {
            Set<String> clientSet = connection.get(storeId);
            //用户是否存在客户端连接
            if (Objects.nonNull(clientSet)) {
                Iterator<String> iterator = clientSet.iterator();
                while (iterator.hasNext()) {
                    //获取该用户的会话id
                    String sid = iterator.next();
                    //获取该用户的会话
                    Session session = clients.get(sid);
                    //向每个会话发送消息
                    if (Objects.nonNull(session)){
                        try {
                            String jsonString = JSON.toJSONString(data);
                            //同步发送数据，需要等上一个sendText发送完成才执行下一个发送
                            session.getBasicRemote().sendText(jsonString);
                        } catch (IOException e) {
                            result.put("code", 500);
                            result.put("msg", "发送失败");
                            e.printStackTrace();
                            return result;
                        }
                    }
                }
            }else {
                result.put("code", 500);
                result.put("msg", "该店铺没有客户端连接");
                return result;
            }
        }else{
            result.put("code", 500);
            result.put("msg", "storeId为空");
            return result;
        }
        result.put("code", 200);
        result.put("msg", "发送成功");
        return result;
    }
}