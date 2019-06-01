package it61.springlabs.vpsservice.Domain.Log;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @Type(type = "uuid-char")
    private UUID id;
    private String Entity;
    private String Created;

    public Log(String entity, String created) {
        id = UUID.randomUUID();
        Entity = entity;
        Created = created;
    }

    public Log(){}

    public String getEntity() {
        return Entity;
    }

    public void setEntity(String entity) {
        Entity = entity;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public UUID getId() {
        return id;
    }
}
