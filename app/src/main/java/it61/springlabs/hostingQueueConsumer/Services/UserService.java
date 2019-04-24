package it61.springlabs.hostingQueueConsumer.Services;

import it61.springlabs.data.LogDTO;
import it61.springlabs.hostingQueueConsumer.DTO.UserDTO;
import it61.springlabs.hostingQueueConsumer.Exceptions.DomainException;
import it61.springlabs.hostingQueueConsumer.Exceptions.NotFoundException;
import it61.springlabs.hostingQueueConsumer.Models.User;
import it61.springlabs.hostingQueueConsumer.Models.Vps;
import it61.springlabs.hostingQueueConsumer.Repository.UserRepository;
import it61.springlabs.hostingQueueConsumer.Repository.VPSRepository;
import it61.springlabs.hostingQueueConsumer.Services.Contracts.UserCrudServiceInterface;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class UserService implements UserCrudServiceInterface {

    private UserRepository userRepository;
    private VPSRepository vpsRepository;
    private AmqpTemplate template;

    @Autowired
    public UserService(UserRepository userRepo, VPSRepository vpsRepo, AmqpTemplate template) {
        userRepository = userRepo;
        vpsRepository = vpsRepo;
        this.template = template;
    }

    @Override
    public User FindById(UUID id) throws DomainException {
        return userRepository.findById(id).orElseThrow(() -> NotFoundException.of(id, "User"));
    }

    @Override
    public void Delete(UUID id) throws DomainException {
        User user = userRepository.findById(id).orElseThrow(() -> NotFoundException.of(id, "User"));
        for (Vps vps: user.getServers()) {
            vps.setIsDeleted(true);
            vpsRepository.save(vps);
        }
        userRepository.save(user.setIs_deleted(true));
        template.convertAndSend("logs", new LogDTO("User","Deleted with id: "+id.toString()));
    }

    @Override
    public Iterable<User> Paginated(Integer perPage, Integer page) throws DomainException {
        return userRepository.findAll(PageRequest.of(page, perPage)).getContent();
    }

    @Override
    public User Create(UserDTO dto) throws DomainException{
        User user = new User
                (
                    dto.getUsername(),
                    dto.getDescription(),
                    dto.getPhone(),
                    dto.getSecret()
                );
        userRepository.save(user);
        template.convertAndSend("logs", new LogDTO("User","Deleted with id: "+user.getId().toString()));

        return user;
    }

    @Override
    public User Update(UUID id, UserDTO dto) throws DomainException {
        User user = FindById(id);
        user.setUsername(dto.getUsername());
        user.setDescription(dto.getDescription());
        user.setSecret(dto.getSecret());
        user.setPhone(dto.getPhone());
        userRepository.save(user);
        template.convertAndSend("logs", new LogDTO("User","Updated with id: "+id.toString()));

        return user;
    }
}
