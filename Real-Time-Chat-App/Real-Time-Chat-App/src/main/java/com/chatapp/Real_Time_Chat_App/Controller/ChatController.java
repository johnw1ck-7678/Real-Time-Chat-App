package com.chatapp.Real_Time_Chat_App.Controller;

import com.chatapp.Real_Time_Chat_App.entities.Message;
import com.chatapp.Real_Time_Chat_App.entities.Room;
import com.chatapp.Real_Time_Chat_App.repo.RoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.*;
import java.time.LocalDateTime;

@Controller
@CrossOrigin("http://localhost:5177")
public class ChatController {
    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    //for sending and receiveing messages
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@RequestBody MessageRequest request, @DestinationVariable String roomId){
        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());
        if(room!=null){
            room.getMessages().add(message);
            roomRepository.save(room);
        }else {
            throw new RuntimeException("No room found");
        }
        return message;
    }
}
