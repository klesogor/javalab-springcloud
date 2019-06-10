package it61.springlabs.queueConsumer.entities;

import it61.springlabs.data.dto.log.LogDto;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @Type(type = "uuid-char")
    private UUID id;
    private String data;
    private Date created;

    public Log(String entity, Date created) {
        id = UUID.randomUUID();
        data = entity;
        this.created = created;
    }

    public Log(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public LogDto toDto(){
        return new LogDto(getData(),getCreated());
    }
}
