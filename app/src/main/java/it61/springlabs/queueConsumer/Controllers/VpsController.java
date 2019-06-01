package it61.springlabs.queueConsumer.Controllers;

import it61.springlabs.data.generic.Response;
import it61.springlabs.data.dto.vps.VpsDTO;
import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.data.entities.Vps;
import it61.springlabs.queueConsumer.Services.VpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public final class VpsController implements CRUDControllerInterface<Vps, VpsDTO> {

    private VpsService service;

    @Autowired
    public VpsController(VpsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/api/v1/vps")
    @ResponseBody
    public Response<Iterable<Vps>> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")  Integer perPage) {
        Iterable<Vps> vpsIterable = service.Paginated(perPage,page - 1);

        return Response.Of(vpsIterable);
    }

    @Override
    @GetMapping("/api/v1/vps/{id}")
    @ResponseBody
    public Response<Vps> findById(@PathVariable(name = "id") UUID id) throws DomainException {
        return Response.Of(service.FindById(id));
    }

    @Override
    @PostMapping("/api/v1/vps")
    @ResponseBody
    public Response<Vps> create(@Valid @RequestBody VpsDTO dto, BindingResult binding) throws DomainException {
        return Response.Of(service.Create(dto));
    }

    @Override
    @PutMapping("/api/v1/vps/{id}")
    @ResponseBody
    public Response<Vps> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody VpsDTO dto, BindingResult binding) throws DomainException {
        return Response.Of(service.Update(id,dto));
    }

    @Override
    @DeleteMapping("/api/v1/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id) throws DomainException {
        service.Delete(id);
    }
}
