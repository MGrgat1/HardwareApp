package hr.tvz.grgat.hardwareapp;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
