package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsoft.shold.core.entity.AdminMessage;
import ru.rsoft.shold.core.repository.AdminMessageRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Admin on 29.06.2016.
 */
@Component
@Path("/message")
public class AdminMessageHandler {

    private final AdminMessageRepository adminMessageRepository;

    @Autowired
    public AdminMessageHandler(AdminMessageRepository adminMessageRepository) {
        this.adminMessageRepository = adminMessageRepository;
    }

    @Path("/")
    @GET
    public List<AdminMessage> list() {
        return adminMessageRepository.findAll();
    }
}
