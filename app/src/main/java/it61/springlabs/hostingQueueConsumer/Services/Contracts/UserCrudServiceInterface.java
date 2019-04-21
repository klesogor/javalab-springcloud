package it61.springlabs.hostingQueueConsumer.Services.Contracts;

import it61.springlabs.hostingQueueConsumer.DTO.UserDTO;
import it61.springlabs.hostingQueueConsumer.Models.User;

public interface UserCrudServiceInterface extends CrudServiceContract<User, UserDTO> {
}
