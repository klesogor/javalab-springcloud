package it61.springlabs.vpsservice.Controllers;

import it61.springlabs.vpsservice.DTO.VPSResponse;
import it61.springlabs.data.dto.vps.VpsDTO;
import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.data.exceptions.ValidationException;
import it61.springlabs.data.entities.Vps;
import it61.springlabs.vpsservice.Services.VpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public final class VpsController implements CRUDControllerInterface<VPSResponse, VpsDTO> {

    private VpsService service;

    @Autowired
    public VpsController(VpsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/api/v1/vps")
    @ResponseBody
    public Iterable<VPSResponse> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")  Integer perPage) throws DomainException {
        Iterable<Vps> vpsIterable = service.Paginated(perPage,page - 1);
        ArrayList<VPSResponse> res = new ArrayList<>();
        for (Vps vps: vpsIterable) {
            res.add(vpsToDTO(vps));
        }

        return res;
    }

    @Override
    @GetMapping("/api/v1/vps/{id}")
    @ResponseBody
    public VPSResponse findById(@PathVariable(name = "id") UUID id) throws DomainException {
        return vpsToDTO(service.FindById(id));
    }

    @Override
    @PostMapping("/api/v1/vps")
    @ResponseBody
    public VPSResponse create(@Valid @RequestBody VpsDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return vpsToDTO(service.Create(dto));
    }

    @Override
    @PutMapping("/api/v1/vps/{id}")
    @ResponseBody
    public VPSResponse update(@PathVariable(name = "id") UUID id, @Valid @RequestBody VpsDTO dto, BindingResult binding) throws DomainException {
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return vpsToDTO(service.Update(id,dto));
    }

    @Override
    @DeleteMapping("/api/v1/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id) throws DomainException {
        service.Delete(id);
    }

    private VPSResponse vpsToDTO(Vps vps) {
        return new VPSResponse(vps.getId(), vps.getOperatingSystem(), vps.getCPUCount(), vps.getCPURate(), vps.getRAM());
    }

}
