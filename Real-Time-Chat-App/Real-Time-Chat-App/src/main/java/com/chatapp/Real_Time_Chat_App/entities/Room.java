package com.chatapp.Real_Time_Chat_App.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection= "rooms") // treats code as document and maps to mongoDb
public class Room {
    @Id
    private String id; //MongoDb unique identifier
    private String roomId;
    private List<Message>messages= new ArrayList<>();

}
