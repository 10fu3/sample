package com.sample.SampleServer.Store;

import com.sample.SampleServer.Entity.IUser;

import java.util.*;

public class MySQL implements IDatabase{
    private static MySQL single = new MySQL();

    Map<String,IUser> users = new HashMap<>();

    public static IDatabase get(){
        return single;
    }

    @Override
    public Optional<IUser> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public IUser update(IUser user) {
        users.put(user.getID(),user);
        return user;
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public void create(IUser user) {
        users.put(user.getID(),user);
    }
}
