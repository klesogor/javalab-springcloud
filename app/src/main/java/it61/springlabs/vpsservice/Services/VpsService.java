package it61.springlabs.vpsservice.Services;

import it61.springlabs.data.dto.vps.VpsDTO;
import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.data.exceptions.NotFoundException;
import it61.springlabs.data.entities.User;
import it61.springlabs.data.entities.Vps;
import it61.springlabs.vpsservice.Repository.UserRepository;
import it61.springlabs.vpsservice.Repository.VPSRepository;
import it61.springlabs.vpsservice.Services.Contracts.VPSCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class VpsService implements VPSCrudService {
    private VPSRepository repository;
    private UserRepository userRepository;

    @Autowired
    public VpsService(VPSRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Vps FindById(UUID id) throws DomainException {
        return repository.findById(id).orElseThrow(() -> NotFoundException.of(id, "VPS"));
    }

    @Override
    public void Delete(UUID id) throws DomainException {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Vps> Paginated(Integer perPage, Integer page) throws DomainException {
        return repository.findAll(PageRequest.of(page,perPage)).getContent();
    }

    @Override
    public Vps Create(VpsDTO data) throws DomainException {
        User user = data.getOwner() == null
                ? null
                : userRepository.findById(
                        data.getOwner()
        ).orElseThrow(()-> NotFoundException.of(data.getOwner(),"user"));
        Vps vps = new Vps
                (
                        user,
                        data.getOperatingSystem(),
                        data.getCpuCount(),
                        data.getCpuRate(),
                        data.getRam()
                );
        repository.save(vps);

        return vps;
    }

    @Override
    public Vps Update(UUID id, VpsDTO data) throws DomainException {
        Vps vps = FindById(id);
        User user = data.getOwner() == null
                ? null
                : userRepository.findById(
                        data.getOwner()
        ).orElseThrow(()-> NotFoundException.of(data.getOwner(),"user"));
        vps.setOwner(user);
        vps.setOperatingSystem(data.getOperatingSystem());
        vps.setCPUCount(data.getCpuCount());
        vps.setCPURate(data.getCpuRate());
        vps.setRAM(data.getRam());

        repository.save(vps);

        return vps;
    }
}
