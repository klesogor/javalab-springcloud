package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.DTO.UserDTO;
import it61.springlabs.yetanotherlab.DTO.UserResponse;
import it61.springlabs.yetanotherlab.DTO.VPSResponse;
import it61.springlabs.yetanotherlab.Exceptions.DomainException;
import it61.springlabs.yetanotherlab.Exceptions.ValidationException;
import it61.springlabs.yetanotherlab.Models.User;
import it61.springlabs.yetanotherlab.Models.Vps;
import it61.springlabs.yetanotherlab.Services.Contracts.UserCrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public final class UserController implements CRUDControllerInterface<UserResponse, UserDTO> {

    private UserCrudServiceInterface service;

    @Autowired
    public UserController(UserCrudServiceInterface service) {
        this.service = service;
    }

    @Override
    @GetMapping("/api/v1/users")
    @ResponseBody
    public Iterable<UserResponse> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer perPage) throws DomainException {
        Iterable<User> users = service.Paginated(perPage, page - 1);
        ArrayList<UserResponse> res = new ArrayList<>();
        for (User user: users) {
            res.add(this.userToDTO(user));
        }

        return res;
    }

    @Override
    @GetMapping("/api/v1/users/{id}")
    @ResponseBody
    public UserResponse findById(@PathVariable UUID id) throws DomainException {
        return this.userToDTO(service.FindById(id));
    }

    @Override
    @PostMapping("/api/v1/users")
    @ResponseBody
    public UserResponse create(@Valid @ModelAttribute UserDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return this.userToDTO(service.Create(dto));
    }

    @Override
    @PutMapping("/api/v1/users/{id}")
    @ResponseBody
    public UserResponse update(@PathVariable UUID id, @Valid @ModelAttribute UserDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return this.userToDTO(service.Update(id, dto));
    }

    @DeleteMapping("/api/v1/users/{id}")
    @Override
    public void delete(@PathVariable UUID id) throws DomainException {
        service.Delete(id);
    }

    private UserResponse userToDTO(User user){
        ArrayList<VPSResponse> vpsArr = new ArrayList<>();
        if(user.getServers() != null) {
            for (Vps vps : user.getServers()) {
                vpsArr.add(new VPSResponse(vps.getId(), vps.getOperatingSystem()));
            }
        }

        return new UserResponse(user.getId(),user.getUsername(),user.getDescription(), vpsArr);
    }
}
