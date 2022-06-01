package hr.tvz.grgat.hardwareapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * H2 database available at:
 * http://localhost:8080/h2-console
 * Database URL: jdbc:h2:mem:0cfb9fcd-e5fa-41f7-ba3d-46e9ff9d3ff4
 */

/***
 * Implemented requests:
 * GET http://localhost:8080/hardware
 * GET http://localhost:8080/hardware/code
 * POST http://localhost:8080/hardware
 * PUT http://localhost:8080/hardware/code
 * DELETE http://localhost:8080/hardware/code
 */

/**
 * Anotacija @RestController eliminira potrebu da se svakoj metodi
 * unutar controllera postavlja anotacija @ ResponseBody
 */
@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {
    private final HardwareService hardwareService;

    /**
     * This is a constructor-based dependency injection
     * @param hardwareService the object or service that is injected into the HardwareController class
     */
    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware() {
        return hardwareService.findAll();
    }

    /**
     *
     * Two different ways to implement GET mapping:
     * With @RequestParam:
     *
     *      GET http://localhost:8080/hardware/?code=AAA
     *
     *      @GetMapping(params = "code")
     *      public HardwareDTO getHardwareByCode(@RequestParam final String code){
     *          return hardwareService.findByCode(code);
     *      }
     *
     * With @PathVariable:
     *
     *      GET http://localhost:8080/hardware/code
     *
     *      @GetMapping("/{code}")
     *      public HardwareDTO getHardwareByCode(@PathVariable final String code){
     *          return hardwareService.findByCode(code);
     *      }
     *
     *
     */
    @GetMapping("/{code}")
    public HardwareDTO getHardwareByCode(@PathVariable final String code){
        return hardwareService.findByCode(code);
    }

    @GetMapping("/{min}/{max}")
    public List<HardwareDTO> getHardwareByInterval(@PathVariable final int min, @PathVariable final int max) {
        return hardwareService.findByInterval(min, max);
    }

    /**
     * @RequestBody is typically used with “create” and “update” operations (POST, PUT, PATCH).
     * For example, when creating a resource using POST or PUT, the request body usually contains the representation
     * of the resource to be created.
     * @param command
     * @return
     */
    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command) {
        return hardwareService.save(command)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }

    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody final HardwareCommand command) {
        System.out.println("[INFO] Entered PutMapping, command:" + command.toString());
        return hardwareService.update(code, command)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        hardwareService.deleteByCode(code);
    }
}
