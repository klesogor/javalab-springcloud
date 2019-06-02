package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.vps.VpsReadDto;
import it61.springlabs.data.dto.vps.VpsWriteDTO;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Services.VpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public final class VpsController implements CRUDControllerInterface<VpsReadDto, VpsWriteDTO> {

    private VpsService client;
    @Autowired
    public VpsController(VpsService client)
    {
        this.client = client;
    }

    @Override
    @GetMapping("/api/v1/vps")
    @ResponseBody
    public Response<Iterable<VpsReadDto>> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")  Integer perPage){
        return client.getAllVps(page,perPage);
    }

    @Override
    @GetMapping("/api/v1/vps/{id}")
    @ResponseBody
    public Response<VpsReadDto> findById(@PathVariable(name = "id") UUID id){
        return client.findVpsById(id);
    }

    @Override
    @PostMapping("/api/v1/vps")
    @ResponseBody
    public Response<VpsReadDto> create(@RequestBody VpsWriteDTO dto){
        return client.createVps(dto);
    }

    @Override
    @PutMapping("/api/v1/vps/{id}")
    @ResponseBody
    public Response<VpsReadDto> update(@PathVariable(name = "id") UUID id, @RequestBody VpsWriteDTO dto){
        return client.updateVps(id,dto);
    }

    @Override
    @DeleteMapping("/api/v1/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id){
        client.deleteVps(id);
    }
}
