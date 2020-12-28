package com.sample.SampleServer.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatEntity {

    private final String id = UUID.randomUUID().toString();

    private final String imageId;

    private final String user;

    public Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        map.put("id",this.id);
        map.put("image-id",imageId);
        map.put("user",user);
        return map;
    }

    public ChatEntity(String userid,String imageId){
        this.user = userid;
        this.imageId = imageId;
    }
}
