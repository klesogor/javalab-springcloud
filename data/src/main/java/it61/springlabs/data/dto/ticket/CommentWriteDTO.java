package it61.springlabs.data.dto.ticket;

import java.util.UUID;

public class CommentWriteDTO {
    private String text;
    private UUID fromId;

    public CommentWriteDTO(String text, UUID fromId) {
        this.text = text;
        this.fromId = fromId;
    }

    public String getText() {
        return text;
    }

    public UUID getFromId() {
        return fromId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFromId(UUID fromId) {
        this.fromId = fromId;
    }
}
