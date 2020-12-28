package com.sample.SampleServer;

import com.sample.SampleServer.Entity.ChatEntity;
import com.sample.SampleServer.Entity.IUser;
import com.sample.SampleServer.Entity.Room;
import com.sample.SampleServer.Entity.UserBuilder;
import com.sample.SampleServer.Store.IDatabase;
import com.sample.SampleServer.Store.RoomStore;
import com.sample.SampleServer.Store.SessionStore;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class URLController {
    private static URLController single = new URLController();

    private Javalin app;

    public static void setup(){
        single.app = Javalin.create().start(80);

        single.app.get("/room/:id",(ctx)->{
            String roomID = ctx.pathParam("id");
            String session = ctx.formParam("session");
            Optional<Room> room = RoomStore.get().getRoom(roomID);
            Optional<IUser> user = IDatabase.get().findById(SessionStore.get().getId(session).orElse(""));
            if(room.isPresent() && user.isPresent() && room.get().contains(user.get().getID())){
                ctx.status(200).json(room.get().getChats().stream().map(ChatEntity::toMap));
            }else{
                ctx.status(404);
            }
        });

        single.app.post("/room/:id/join",(ctx)->{
            String roomID = ctx.pathParam("id");
            String session = ctx.formParam("session");
            Optional<Room> room = RoomStore.get().getRoom(roomID);
            Optional<IUser> user = IDatabase.get().findById(SessionStore.get().getId(session).orElse(""));
            if(room.isPresent() && user.isPresent()){
                if(room.get().addUser(user.get().getID())){
                    ctx.status(200);
                }else{
                    ctx.status(401);
                }
            }else{
                ctx.status(404);
            }
        });

        single.app.get("/room",(ctx)->{
            ctx.json(RoomStore.get().getRoomsID());
        });

        single.app.post("/room",(ctx)->{

        });

        single.app.post("/lobby",(ctx)->{

        });

        single.app.post("/test",(ctx)->{
            Optional<IUser> user = IDatabase.get().findById(SessionStore.get().getId(ctx.formParam("session")).orElse(""));
            if(user.isPresent()){
                ctx.status(200).result(user.get().getID());
            }else{
                ctx.status(404);
            }
        });

        single.app.post("/login",(ctx)->{
            ctx.formParam("mail");
            ctx.formParam("pass");

            Optional<IUser> user = IDatabase.get().findById(ctx.formParam("mail"));
            if(user.isPresent() && user.get().getPass().equalsIgnoreCase(ctx.formParam("pass"))){
                Map<String,String> result = new HashMap<>();
                result.put("session",SessionStore.get().put(user.get()));
                ctx.status(200).json(result);
            }else{
                ctx.status(404);
            }
        });

        single.app.post("/entry",(ctx)->{
            IUser user = UserBuilder
                    .create()
                    .setId(ctx.formParam("mail"))
                    .setPass(ctx.formParam("pass"))
                    .build();

            IDatabase.get().create(user);
            ctx.status(200);
        });

    }
}
