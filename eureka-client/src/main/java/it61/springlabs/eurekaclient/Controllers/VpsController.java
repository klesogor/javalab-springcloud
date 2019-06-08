package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.vps.VpsReadDto;
import it61.springlabs.data.dto.vps.VpsWriteDTO;
import it61.springlabs.data.exceptions.AuthException;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Auth.JwtTokenDetails;
import it61.springlabs.eurekaclient.Services.VpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Response<Iterable<VpsReadDto>> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "100")  Integer perPage){
        if(!isAuthorized()) throw AuthException.of("Unauthorized!");
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
        if(!isAuthorized()) throw AuthException.of("Unauthorized!");
        return client.createVps(dto);
    }

    @Override
    @PutMapping("/api/v1/vps/{id}")
    @ResponseBody
    public Response<VpsReadDto> update(@PathVariable(name = "id") UUID id, @RequestBody VpsWriteDTO dto){
        if(!isAuthorized()) throw AuthException.of("Unauthorized!");
        return client.updateVps(id,dto);
    }

    @Override
    @DeleteMapping("/api/v1/vps/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") UUID id){
        if(!isAuthorized()) throw AuthException.of("Unauthorized!");
        client.deleteVps(id);
    }

    @GetMapping("/api/v1/vps/me")
    @ResponseBody
    public Response<Iterable<VpsReadDto>> myVps(){
        JwtTokenDetails details = (JwtTokenDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return client.findVpsByUser(details.getUserId());
    }

    private boolean isAuthorized(){
        return ((JwtTokenDetails)SecurityContextHolder.getContext().getAuthentication().getDetails()).isAdmin();
    }
}
