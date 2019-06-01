package it61.springlabs.vpsservice.Services.Contracts;

import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.data.entities.User;

public interface UserCrudServiceInterface extends CrudServiceContract<User, UserRegistrationDTO> {
}
