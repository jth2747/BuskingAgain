package com.ssafy.api.controller;

import com.ssafy.vo.SocketVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @MessageMapping("/receive")
    
    @SendTo("/send")
    
    public SocketVO SocketHandler(SocketVO socketVO) {
        String userName = socketVO.getUserName();
        String content = socketVO.getContent();

        SocketVO result = new SocketVO(userName, content);
        // 반환
        return result;
    }
}
