package com.sample.SampleServer.Store;

import com.sample.SampleServer.Entity.IUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SessionStore {
    private static SessionStore single = new SessionStore();

    private Map<String,String> store = new HashMap<>();

    public static SessionStore get(){
        return single;
    }

    /**
     *
     * @return セッション UUID
     */
    public String put(IUser user){
        String session = UUID.randomUUID().toString();
        store.put(session,user.getID());
        return session;
    }

    public Optional<String> getId(String session){
        return Optional.ofNullable(store.get(session));
    }

}
