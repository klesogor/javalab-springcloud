package DTO;

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
}
