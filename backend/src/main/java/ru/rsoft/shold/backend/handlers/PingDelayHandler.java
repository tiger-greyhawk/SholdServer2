package ru.rsoft.shold.backend.handlers;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Date;

/**
 * Created by Admin on 16.07.2016.
 */

@Component
@Path("/ping")
public class PingDelayHandler {
    @Path("/")
    @GET
    public Date ping() {
        return new Date();
    }

    @Path("/{time}")
    @GET
    public long pingDelay(@PathParam("time") long time) {
        long date = new Date().getTime();
        return date - time ;
    }
}
