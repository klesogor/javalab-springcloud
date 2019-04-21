package it61.springlabs.eurekaclient.DTO;

public class InboundMessageDto<T> {
    private T data;
    private String messageType;

    public InboundMessageDto(T data, String messageType) {
        this.data = data;
        this.messageType = messageType;
    }

    public T getData() {
        return data;
    }

    public String getMessageType() {
        return messageType;
    }
}
