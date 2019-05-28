package it61.springlabs.hostingQueueConsumer.Services.Contracts;

import it61.springlabs.hostingQueueConsumer.DTO.UserDTO;
import Entities.User;

public interface UserCrudServiceInterface extends CrudServiceContract<User, UserDTO> {
}
