package tacos.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.model.Taco;
import tacos.model.TacoOrder;
import tacos.repository.OrderRepository;
import tacos.repository.TacoRepository;


@RestController
@RequestMapping (path = "/api/tacos", produces = "application/json")
@CrossOrigin (origins = "http://tacocloud:8080")
public class TacoController {

    private TacoRepository tacoRepository;

    private OrderRepository orderRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Flux<Taco> recentTacos() {
        return tacoRepository.findAll().take(12);
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") Long id) {
        return tacoRepository.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Mono<Taco> tacoMono) {
        return tacoMono.flatMap(tacoRepository::save);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {

        TacoOrder tacoOrder = orderRepository.findById(orderId).get();

        if (patch.getDeliveryName() != null) {
            tacoOrder.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            tacoOrder.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            tacoOrder.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            tacoOrder.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            tacoOrder.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            tacoOrder.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            tacoOrder.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            tacoOrder.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(tacoOrder);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            tacoRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
