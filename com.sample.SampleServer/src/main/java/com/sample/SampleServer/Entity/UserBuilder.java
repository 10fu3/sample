package com.sample.SampleServer.Entity;

public class UserBuilder {
    String id;
    String pass;

    public static UserBuilder create(){
        return new UserBuilder();
    }

    public UserBuilder setId(String id){
        this.id = id;
        return this;
    }

    public UserBuilder setPass(String pass){
        this.pass = pass;
        return this;
    }

    public IUser build(){
        User user = new User();
        user.id = id;
        user.pass = pass;
        return user;
    }
}
