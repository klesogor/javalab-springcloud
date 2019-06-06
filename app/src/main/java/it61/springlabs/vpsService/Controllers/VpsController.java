package it61.springlabs.vpsService.Controllers;

import it61.springlabs.data.dto.vps.VpsReadDto;
import it61.springlabs.data.generic.Response;
import it61.springlabs.data.dto.vps.VpsWriteDTO;
import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.vpsService.entities.Vps;
import it61.springlabs.vpsService.Services.VpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public final class VpsController implements CRUDControllerInterface<VpsReadDto, VpsWriteDTO> {

    private VpsService service;

    @Autowired
    public VpsController(VpsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/api/v1/vps")
    @ResponseBody
    public Response<Iterable<VpsReadDto>> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")  Integer perPage) {
        Iterable<Vps> vpsIterable = service.Paginated(perPage,page - 1);

        return Response.Of(StreamSupport.stream(vpsIterable.spliterator(),false)
                .map(v -> v.toDto()).collect(Collectors.toList()));
    }

    @Override
    @GetMapping("/api/v1/vps/{id}")
    @ResponseBody
    public Response<VpsReadDto> findById(@PathVariable(name = "id") UUID id) throws DomainException {
        return Response.Of(service.FindById(id).toDto());
    }

    @Override
    @PostMapping("/api/v1/vps")
    @ResponseBody
    public Response<VpsReadDto> create(@Valid @RequestBody VpsWriteDTO dto, BindingResult binding) throws DomainException {
        return Response.Of(service.Create(dto).toDto());
    }

    @Override
    @PutMapping("/api/v1/vps/{id}")
    @ResponseBody
    public Response<VpsReadDto> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody VpsWriteDTO dto, BindingResult binding) throws DomainException {
        return Response.Of(service.Update(id,dto).toDto());
    }

    @Override
    @DeleteMapping("/api/v1/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id) throws DomainException {
        service.Delete(id);
    }

    @GetMapping(value = "/api/v1/vps/byUser/{id}")
    public Response<Iterable<VpsReadDto>> getForUser(@PathVariable(name = "id") UUID userId){
        return Response.Of(
                StreamSupport.stream(service.getVpsForUser(userId).spliterator(),false)
                        .map(Vps::toDto)
                        .collect(Collectors.toList())
        );
    }
}
