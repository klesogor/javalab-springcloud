package it61.springlabs.vpsService.Services.Contracts;

import it61.springlabs.data.dto.vps.VpsWriteDTO;
import it61.springlabs.vpsService.entities.Vps;

public interface VPSCrudService extends CrudServiceContract<Vps, VpsWriteDTO> {
}
