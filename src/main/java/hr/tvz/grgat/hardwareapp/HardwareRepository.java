package hr.tvz.grgat.hardwareapp;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(HardwareCommand command);

    void deleteByCode(String code);

    Optional<Hardware> update(String code, HardwareCommand command);
}
