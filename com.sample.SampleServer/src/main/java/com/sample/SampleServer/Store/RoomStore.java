package com.sample.SampleServer.Store;

import com.sample.SampleServer.Entity.Room;

import java.util.*;
import java.util.stream.Collectors;

public class RoomStore {
    Map<String,Room> rooms = new HashMap<>();
    private static RoomStore single = new RoomStore();
    public static RoomStore get(){
        return single;
    }

    public List<String> getRoomsID(){
        return new ArrayList<>(rooms.keySet());
    }

    public String createRoom(){
        Room room = new Room();
        rooms.put(room.getId(),room);
        return room.getId();
    }

    public Optional<Room> getRoom(String id){
        return Optional.ofNullable(rooms.get(id));
    }
}
