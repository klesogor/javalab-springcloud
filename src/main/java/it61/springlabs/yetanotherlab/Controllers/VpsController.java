package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.DTO.VpsDTO;
import it61.springlabs.yetanotherlab.Exceptions.DomainException;
import it61.springlabs.yetanotherlab.Exceptions.ValidationException;
import it61.springlabs.yetanotherlab.Models.Vps;
import it61.springlabs.yetanotherlab.Services.VpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/vps")
public final class VpsController implements CRUDControllerInterface<Vps, VpsDTO> {

    private VpsService service;

    @Autowired
    public VpsController(VpsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/")
    @ResponseBody
    public Iterable<Vps> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")  Integer perPage) throws DomainException {
        return service.Paginated(perPage, page - 1);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseBody
    public Vps findById(@PathVariable(name = "id") UUID id) throws DomainException {
        return service.FindById(id);
    }

    @Override
    @PostMapping("/")
    @ResponseBody
    public Vps create(@Valid @ModelAttribute VpsDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return service.Create(dto);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseBody
    public Vps update(@PathVariable(name = "id") UUID id, @Valid @ModelAttribute VpsDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return service.Update(id,dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id) throws DomainException {
        service.Delete(id);
    }
}
