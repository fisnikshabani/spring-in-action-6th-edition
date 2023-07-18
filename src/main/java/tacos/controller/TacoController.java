package tacos.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.model.Taco;
import tacos.repository.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping (path = "/api/tacos", produces = "application/json")
@CrossOrigin (origins = "http://tacocloud:8080")
public class TacoController {

    private TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {

        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        return optionalTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
