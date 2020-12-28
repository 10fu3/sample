package com.sample.SampleServer.Entity;

import com.sample.SampleServer.Store.IDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Room {
    private List<ChatEntity> chat = new ArrayList<>();
    private List<IUser> user = new ArrayList<>();
    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public boolean contains(String id){
        return user.stream().anyMatch(u -> u.getID().equalsIgnoreCase(id));
    }

    public List<ChatEntity> getChats(){
        return this.chat;
    }

    public boolean addUser(String userId){
        Optional<IUser> user = IDatabase.get().findById(userId);
        if(!user.isPresent()){
            return false;
        }
        this.user.add(user.get());
        return true;
    }
}
