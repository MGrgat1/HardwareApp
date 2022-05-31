package hr.tvz.grgat.hardwareapp.hardware.repository;

import hr.tvz.grgat.hardwareapp.hardware.hardware.Hardware;
import hr.tvz.grgat.hardwareapp.hardware.command.HardwareCommand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HardwareRepository {

    Set<Hardware> findAll();
    Optional<Hardware> findByCode(String code);

    Optional<List<Hardware>> findByInterval(int min, int max);

    Optional<Hardware> save(HardwareCommand command);
    void deleteByCode(String code);
    Optional<Hardware> update(String code, HardwareCommand command);
}
