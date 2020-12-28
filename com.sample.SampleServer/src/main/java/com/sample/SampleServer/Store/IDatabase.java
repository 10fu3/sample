package com.sample.SampleServer.Store;

import com.sample.SampleServer.Entity.IUser;

import java.util.Optional;

public interface IDatabase {

    static IDatabase get(){
        return MySQL.get();
    }

    Optional<IUser> findById(String id);
    IUser update(IUser user);
    void deleteById(String id);
    void create(IUser user);
}
