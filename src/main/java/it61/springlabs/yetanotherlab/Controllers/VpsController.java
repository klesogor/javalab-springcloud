package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.DTO.VpsDTO;
import it61.springlabs.yetanotherlab.Exceptions.DomainException;
import it61.springlabs.yetanotherlab.Models.Vps;
import it61.springlabs.yetanotherlab.Services.VpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public final class VpsController extends AbstractCRUDController<Vps, VpsDTO> {

    private VpsService service;

    @Autowired
    public VpsController(VpsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/vps")
    @ResponseBody
    public Iterable<Vps> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")  Integer perPage) throws DomainException {
        return service.Paginated(perPage, page - 1);
    }

    @Override
    @GetMapping("/vps/{id}")
    @ResponseBody
    public Vps findById(@PathVariable(name = "id") UUID id) throws DomainException {
        return service.FindById(id);
    }

    @Override
    @PostMapping("/vps")
    @ResponseBody
    public Vps create(@ModelAttribute VpsDTO dto) throws DomainException {
        return service.Create(dto);
    }

    @Override
    @PutMapping("/vps/{id}")
    @ResponseBody
    public Vps update(@PathVariable(name = "id") UUID id, @ModelAttribute VpsDTO dto) throws DomainException {
        return service.Update(id,dto);
    }

    @Override
    @DeleteMapping("/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id) throws DomainException {
        service.Delete(id);
    }
}
