package hr.tvz.grgat.hardwareapp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class HardwareJdbcRepository implements HardwareRepository {

    private static final String SELECT_ALL =
            "SELECT * FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    /**
     * Creates a repository with its own template and inserter. The inserter will insert entries into the table called
     * hardware,using id as the primary key.
     * @param jdbc
     */
    public HardwareJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Set<Hardware> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(final String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * Select all hardware objects in the table whose stock value is within a certain range
     * @param min
     * @param max
     * @return
     */
    @Override
    public Optional<List<Hardware>> findByInterval(final int min, final int max) {
        String intervalQuery = "SELECT * FROM hardware WHERE stock < " + max + "AND stock > " + min;
        try {
            return Optional.of(
                    jdbc.query(intervalQuery, this::mapRowToHardware)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     *
     * @param command
     * @return
     */
    @Override
    public Optional<Hardware> save(final HardwareCommand command) {
        Hardware hardware = command.getHardware();

        try {
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    /**
     * Takes a hardware object and creates a hash map based on the values of the object.
     * The values in the hash map will be inserted into the database table using a generated key, and the same key
     * will then be returned by the function.
     * @param hardware
     * @return
     */
    private long saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();
        values.put("code", hardware.getCode());
        values.put("hardware_name", hardware.getName());            //'name' is already reserved
        values.put("price", hardware.getPrice());
        values.put("hardware_type", hardware.getType().toString());            //'type' is already reserved
        values.put("stock", hardware.getStock());

        return inserter.executeAndReturnKey(values).longValue();
    }

    public Optional<Hardware> update(String code, HardwareCommand command){
        Hardware updatedHardware = command.getHardware();

        int executed = jdbc.update(("UPDATE hardware set" +
                "hardware_name = ?, " +
                "price = ?, " +
                "hardware_type = ?, " +
                "stock = ?" +
                    "WHERE code = ?"),
                updatedHardware.getName(),
                updatedHardware.getPrice(),
                updatedHardware.getType().toString(),
                updatedHardware.getStock()
        );

    if(executed > 0) {
        return Optional.of(updatedHardware);
    } else {
        return Optional.empty();
    }

    }

    public void deleteByCode(String code){
        if (findByCode(code).isPresent()) {
            Hardware hardwareToBeDeleted = findByCode(code).get();
            jdbc.update("DELETE FROM reviews WHERE hardware_id = ?", hardwareToBeDeleted.getId());
        }
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);

    }

    /**
     * Converts a row obtained from the database (a result set) into a hardware object
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getLong("id"),
                rs.getString("code"),
                rs.getString("hardware_name"),
                rs.getDouble("price"),
                Type.valueOf(rs.getString("hardware_type")),
                rs.getInt("stock")
        );
    }
}
