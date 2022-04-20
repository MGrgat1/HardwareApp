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
        add(new Hardware("AAA", "INTEL Pentium Gold", 533.07,CPU, 25));
        add(new Hardware("AAB", "Intel Core i3", 969.00, CPU, 500));
        add(new Hardware("AAC", "Intel Core i3",1234.00, CPU, 25));
        add(new Hardware("ABA", "Kingston FURY Beast", 1999.0, LAPTOP, 320));
        add(new Hardware("ABB", "ASUS E210MA-GJ208TS", 1899.0, LAPTOP, 120));
        add(new Hardware("ABC", "ACER Aspire 3",  2649.0, LAPTOP, 150));
        add(new Hardware("ACA","ACER Swift 1",2699.0, LAPTOP, 90));
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
    public Optional<Hardware> save(final HardwareCommand command) {
        Hardware hardwareToAdd = command.getHardware();

        // if hardware to add is already present, then return empty, else add it to the hardware list
        if (findByCode(hardwareToAdd.getCode()).isPresent()){
            return Optional.empty();
        } else {
            hardware.add(hardwareToAdd);
            return Optional.of(hardwareToAdd);
        }
    }

    public void deleteByCode(String code){
        Optional<Hardware> hardwareToDelete = findByCode(code);
        hardwareToDelete.ifPresent(object -> hardware.remove(object));
    }

    public Optional<Hardware> update(String code, HardwareCommand command){
        Hardware updatedHardware = command.getHardware();
        Optional<Hardware> hardwareToBeUpdatedOptional = findByCode(code);

        // if the hardware that needs to be updated is present, update it, else insert the update at the end of the list
        if (hardwareToBeUpdatedOptional.isPresent()){
            Hardware hardwareToBeUpdated = hardwareToBeUpdatedOptional.get();
            hardwareToBeUpdated.setPrice(updatedHardware.getPrice());       //update the price
            hardware.add(hardware.indexOf(hardwareToBeUpdated), hardwareToBeUpdated);       //insert the updated hardware back into its position in the list
            return Optional.of(hardwareToBeUpdated);
        } else {
            return Optional.empty();
        }

    }
}
