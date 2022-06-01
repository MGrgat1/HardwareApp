package hr.tvz.grgat.hardwareapp;

import hr.tvz.grgat.hardwareapp.HardwareCommand;
import hr.tvz.grgat.hardwareapp.HardwareDTO;

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
