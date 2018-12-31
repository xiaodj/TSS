package com.tssweb.websocket;

import com.alibaba.fastjson.JSONObject;
import com.tssweb.dto.RealDataDto;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.RecordEntity;
import com.tssweb.netty.CacheData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.tssweb.netty.DataEngine.dataMap;
import static com.tssweb.netty.DataEngine.uid;

/**
 * Created by xiaodj on 2018/11/27.
 */
@Component
public class MessageHandler implements WebSocketHandler {
    private static final Map<Integer, WebSocketSession> userMap;  //保存用户ID 对应 session
    static {
        userMap = new ConcurrentHashMap<Integer, WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.print("连接成功");
        Integer uid = (Integer) session.getAttributes().get("uid");
        userMap.put(uid, session);

        List<RealDataDto> realDataDtoList = new ArrayList<RealDataDto>();
        synchronized (dataMap) {
            Iterator iter = dataMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String tagid = (String) entry.getKey();
                CacheData cacheData = (CacheData) entry.getValue();
                if (cacheData.getStartdate() == null)
                    continue;
                RealDataDto realDataDto = new RealDataDto();
                realDataDto.setUsername(cacheData.getUsername());
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                realDataDto.setIntime(timeFormat.format(cacheData.getStartdate()));
                for (LicenceEntity licenceEntity : cacheData.getLcList()) {
                    if (licenceEntity.getLCNAME().equals("绿卡"))
                        realDataDto.setLc1(licenceEntity.getLCDATE());
                    else if (licenceEntity.getLCNAME().equals("密卡"))
                        realDataDto.setLc2(licenceEntity.getLCDATE());
                    else if (licenceEntity.getLCNAME().equals("CP"))
                        realDataDto.setLc3(licenceEntity.getLCDATE());
                }
                realDataDtoList.add(realDataDto);
            }
        }
        Collections.sort(realDataDtoList);
        MessageHandler messageHandler = new MessageHandler();
        messageHandler.sendMessageToUser(uid, JSONObject.toJSONString(realDataDtoList));
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        String msg = message.toString();
//        String userId = this.getUserId(session);
//        System.err.println("该"+userId+"用户发送的消息是："+msg);
//        message = new TextMessage("服务端已经接收到消息，msg="+msg);
//        session.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
        WebSocketMessage<String> message = new TextMessage("异常信息："+e.getMessage());
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.print("连接关闭");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUser(Integer uid, String msg) {
        WebSocketSession session = userMap.get(uid);
        if(session !=null && session.isOpen()) {
            try {
                TextMessage message = new TextMessage(msg);
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
