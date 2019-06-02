package it61.springlabs.vpsService.Services;

import it61.springlabs.data.dto.vps.VpsWriteDTO;
import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.data.exceptions.NotFoundException;
import it61.springlabs.vpsService.entities.Vps;
import it61.springlabs.vpsService.Services.Contracts.VPSCrudService;
import it61.springlabs.vpsService.dal.VPSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class VpsService implements VPSCrudService {
    private VPSRepository repository;

    @Autowired
    public VpsService(VPSRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vps FindById(UUID id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.of(id, "VPS"));
    }

    @Override
    public void Delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Vps> Paginated(Integer perPage, Integer page) throws DomainException {
        return repository.findAll(PageRequest.of(page,perPage)).getContent();
    }

    @Override
    public Vps Create(VpsWriteDTO data) {
        Vps vps = new Vps
                (
                        data.getOwner(),
                        data.getOperatingSystem(),
                        data.getCpuCount(),
                        data.getCpuRate(),
                        data.getRam()
                );
        repository.save(vps);

        return vps;
    }

    @Override
    public Vps Update(UUID id, VpsWriteDTO data) {
        Vps vps = FindById(id);
        vps.setOperatingSystem(data.getOperatingSystem());
        vps.setCPUCount(data.getCpuCount());
        vps.setCPURate(data.getCpuRate());
        vps.setRAM(data.getRam());

        repository.save(vps);

        return vps;
    }
}
