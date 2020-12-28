package com.sample.SampleServer.Entity;

class User implements IUser{
    String id;
    String pass;

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getPass() {
        return this.pass;
    }
}
