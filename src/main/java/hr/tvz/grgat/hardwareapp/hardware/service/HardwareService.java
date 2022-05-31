package hr.tvz.grgat.hardwareapp.hardware.service;

import hr.tvz.grgat.hardwareapp.hardware.command.HardwareCommand;
import hr.tvz.grgat.hardwareapp.hardware.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    HardwareDTO findByCode(String code);

    List<HardwareDTO> findByInterval(int min, int max);

    Optional<HardwareDTO> save(HardwareCommand command);

    void deleteByCode(String code);

    Optional<HardwareDTO> update(String code, HardwareCommand command);
}
