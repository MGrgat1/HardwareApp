package hr.tvz.grgat.hardwareapp;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;

import static hr.tvz.grgat.hardwareapp.Type.CPU;
import static hr.tvz.grgat.hardwareapp.Type.LAPTOP;

@Repository
public class HardwareRepositoryImpl implements HardwareRepository {

    private List<Hardware> hardware = new ArrayList<Hardware>(){{
        add(new Hardware("INTEL Pentium Gold", "G6405", 533.07,CPU, 25));
        add(new Hardware("Intel Core i3", "10100", 969.00, CPU, 500));
        add(new Hardware("Intel Core i3", "10300", 1234.00, CPU, 25));
        add(new Hardware("Kingston FURY Beast", "KNGF4567", 1999.0, LAPTOP, 320));
        add(new Hardware("ASUS E210MA-GJ208TS", "ASE210", 1899.0, LAPTOP, 120));
        add(new Hardware("ACER Aspire 3", "ACEN400", 2649.0, LAPTOP, 150));
        add(new Hardware("ACER Swift 1", "ACES500", 2699.0, LAPTOP, 90));
    }};

    @Override
    public List<Hardware> findAll() {
        return hardware;
    }

    @Override
    public Optional<Hardware> findByCode(final String code) {
        return hardware.stream().filter(hardware -> Objects.equals(hardware.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> save(@Valid @RequestBody final HardwareCommand command) {
        Hardware hardwareToAdd = command.getHardware();

        if (findByCode(hardwareToAdd.getCode()).isPresent()){
            return Optional.empty();
        } else {
            hardware.add(hardwareToAdd);
            return Optional.of(hardwareToAdd);
        }
    }

}
