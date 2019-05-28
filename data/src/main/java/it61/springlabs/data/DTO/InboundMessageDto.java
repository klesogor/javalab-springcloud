package it61.springlabs.data.DTO;

public class InboundMessageDto<T> {
    private T data;
    private String messageType;

    public InboundMessageDto(T data, String messageType) {
        this.data = data;
        this.messageType = messageType;
    }

    public InboundMessageDto() {
    }

    public T getData() {
        return data;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
