package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.DTO.UserDTO;
import it61.springlabs.yetanotherlab.Exceptions.DomainException;
import it61.springlabs.yetanotherlab.Exceptions.ValidationException;
import it61.springlabs.yetanotherlab.Models.User;
import it61.springlabs.yetanotherlab.Services.Contracts.UserCrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements CRUDControllerInterface<User, UserDTO> {

    UserCrudServiceInterface service;

    @Autowired
    public UserController(UserCrudServiceInterface service) {
        this.service = service;
    }

    @Override
    @GetMapping("/")
    @ResponseBody
    public Iterable<User> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer perPage) throws DomainException {
        return service.Paginated(perPage, page - 1);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseBody
    public User findById(@PathVariable UUID id) throws DomainException {
        return service.FindById(id);
    }

    @Override
    @PostMapping("/")
    @ResponseBody
    public User create(@Valid @ModelAttribute UserDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return service.Create(dto);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseBody
    public User update(@PathVariable UUID id, @Valid @ModelAttribute UserDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return service.Update(id, dto);
    }

    @Override
    public void delete(UUID id) throws DomainException {
        service.Delete(id);
    }
}
