package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.DTO.VPSResponse;
import it61.springlabs.yetanotherlab.DTO.VpsDTO;
import it61.springlabs.yetanotherlab.Exceptions.DomainException;
import it61.springlabs.yetanotherlab.Exceptions.ValidationException;
import it61.springlabs.yetanotherlab.Models.Vps;
import it61.springlabs.yetanotherlab.Services.VpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public final class VpsController implements CRUDControllerInterface<VPSResponse, VpsDTO> {

    private VpsService service;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${app-name}")
    private String appName;

    @Autowired
    public VpsController(VpsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/api/v1/vps")
    @ResponseBody
    public Iterable<VPSResponse> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")  Integer perPage) throws DomainException {
        logger.info("Request incoming to app: " + appName);
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
        logger.info("Request incoming to app: " + appName);
        return vpsToDTO(service.FindById(id));
    }

    @Override
    @PostMapping("/api/v1/vps")
    @ResponseBody
    public VPSResponse create(@Valid @RequestBody VpsDTO dto, BindingResult binding) throws DomainException {
        logger.info("Request incoming to app: " + appName);
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return vpsToDTO(service.Create(dto));
    }

    @Override
    @PutMapping("/api/v1/vps/{id}")
    @ResponseBody
    public VPSResponse update(@PathVariable(name = "id") UUID id, @Valid @RequestBody VpsDTO dto, BindingResult binding) throws DomainException {
        logger.info("Request incoming to app: " + appName);
        if(binding.hasErrors()){
            throw ValidationException.of(binding);
        }

        return vpsToDTO(service.Update(id,dto));
    }

    @Override
    @DeleteMapping("/api/v1/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id) throws DomainException {
        logger.info("Request incoming to app: " + appName);
        service.Delete(id);
    }

    private VPSResponse vpsToDTO(Vps vps) {
        return new VPSResponse(vps.getId(), vps.getOperatingSystem(), vps.getCPUCount(), vps.getCPURate(), vps.getRAM());
    }

}