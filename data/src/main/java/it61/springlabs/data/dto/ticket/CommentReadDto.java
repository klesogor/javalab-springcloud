package it61.springlabs.data.dto.ticket;

import java.util.UUID;

public class CommentReadDto {
    private UUID id;
    private String text;
    private UUID fromId;

    public CommentReadDto(UUID id, String text, UUID fromId) {
        this.id = id;
        this.text = text;
        this.fromId = fromId;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public UUID getFromId() {
        return fromId;
    }
}
