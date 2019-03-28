package it61.springlabs.yetanotherlab.Services;

import it61.springlabs.yetanotherlab.DTO.UserDTO;
import it61.springlabs.yetanotherlab.Exceptions.DomainException;
import it61.springlabs.yetanotherlab.Exceptions.NotFoundException;
import it61.springlabs.yetanotherlab.Models.User;
import it61.springlabs.yetanotherlab.Models.Vps;
import it61.springlabs.yetanotherlab.Repository.UserRepository;
import it61.springlabs.yetanotherlab.Repository.VPSRepository;
import it61.springlabs.yetanotherlab.Services.Contracts.UserCrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public final class UserService implements UserCrudServiceInterface {

    private UserRepository userRepository;
    private VPSRepository vpsRepository;

    @Autowired
    public UserService(UserRepository userRepo, VPSRepository vpsRepo) {
        userRepository = userRepo;
        vpsRepository = vpsRepo;
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

        return user;
    }
}
