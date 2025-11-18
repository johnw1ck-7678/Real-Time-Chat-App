package com.chatapp.Real_Time_Chat_App.repo;

import com.chatapp.Real_Time_Chat_App.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room,String> {
    //get room using room id
    Room findByRoomId(String roomId);


}
