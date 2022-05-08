package hr.tvz.grgat.hardwareapp;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
