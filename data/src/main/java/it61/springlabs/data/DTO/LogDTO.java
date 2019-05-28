package it61.springlabs.data.DTO;

public class LogDTO {
    private String Entity;
    private String Created;

    public LogDTO(String entity, String created) {
        Entity = entity;
        Created = created;
    }

    public LogDTO(){}

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
}
