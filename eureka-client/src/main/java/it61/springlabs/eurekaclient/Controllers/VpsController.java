package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.eurekaclient.DTO.VPSResponse;
import it61.springlabs.eurekaclient.DTO.VpsDTO;
import it61.springlabs.eurekaclient.Services.HostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public final class VpsController implements CRUDControllerInterface<VPSResponse, VpsDTO> {

    private HostingService client;
    @Autowired
    public VpsController(HostingService client)
    {
        this.client = client;
    }

    @Override
    @GetMapping("/api/v1/vps")
    @ResponseBody
    public Iterable<VPSResponse> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")  Integer perPage){
        return client.getAllVps(page,perPage);
    }

    @Override
    @GetMapping("/api/v1/vps/{id}")
    @ResponseBody
    public VPSResponse findById(@PathVariable(name = "id") UUID id){
        return client.findVpsById(id);
    }

    @Override
    @PostMapping("/api/v1/vps")
    @ResponseBody
    public VPSResponse create(@ModelAttribute VpsDTO dto){
        return client.createVps(dto);
    }

    @Override
    @PutMapping("/api/v1/vps/{id}")
    @ResponseBody
    public VPSResponse update(@PathVariable(name = "id") UUID id,@ModelAttribute VpsDTO dto){
        return client.updateVps(id,dto);
    }

    @Override
    @DeleteMapping("/api/v1/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id){
        client.deleteVps(id);
    }
}
