package it61.springlabs.hostingQueueConsumer.Domain.Tickets;

import it61.springlabs.data.TicketReadDto;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public final class Ticket {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;
    private UUID userId;
    private UUID vpsId;
    private String description;
    private Date openedAt;
    private Date closedAt;

    public Ticket(UUID userId, UUID vpsId, String description, Date openedAt) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.vpsId = vpsId;
        this.description = description;
        this.openedAt = openedAt;
    }

    protected Ticket(){}

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getVpsId() {
        return vpsId;
    }

    public String getDescription() {
        return description;
    }

    public Date getOpenedAt() {
        return openedAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void Close(Date closedAt) {
        this.closedAt = closedAt;
    }

    public  TicketReadDto ToDto(){
        return new TicketReadDto(
                getId(),
                getVpsId(),
                getDescription(),
                getOpenedAt(),
                getClosedAt(),
                new ArrayList<>()
        );
    }
}
