package hr.tvz.grgat.hardwareapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    /**
     * HardwareRepository is not created within HardwareService. Instead it's injected through the constructor
     */
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

    @Override
    public List<HardwareDTO> findByInterval(final int min, final int max) {
        Optional<List<Hardware>> optionalList = hardwareRepository.findByInterval(min, max);
        if(optionalList.isPresent()) {
            List<Hardware> intervalList = optionalList.get();
            return intervalList.stream().map(this::mapToDTO).toList();
        } else {
            return null;
        }
    }

    private HardwareDTO mapToDTO(final Hardware hardware) {
        return new HardwareDTO(hardware.getCode(), hardware.getName(), hardware.getPrice());
    }

    public Optional<HardwareDTO> save(final HardwareCommand command) {
        return hardwareRepository.save(command).map(this::mapToDTO);
    }

    public Optional<HardwareDTO> update(String code, HardwareCommand command){
        return hardwareRepository.update(code, command).map(this::mapToDTO);
    }

    public void deleteByCode(String code) {
        hardwareRepository.deleteByCode(code);
    }
}
