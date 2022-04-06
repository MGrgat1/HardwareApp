package hr.tvz.grgat.hardwareapp;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public HardwareDTO findByCode(final String code) {
        return hardwareRepository.findByCode(code).map(this::mapToDTO).orElse(null);
    }

    private HardwareDTO mapToDTO(final Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getPrice());
    }

    public Optional<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command) {
        System.out.println("[INFO] Service layer: Command:");
        System.out.println(command.toString());
        return hardwareRepository.save(command).map(this::mapToDTO);
    }
}
