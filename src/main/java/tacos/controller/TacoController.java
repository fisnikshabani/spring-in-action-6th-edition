package tacos.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.model.Taco;
import tacos.repository.TacoRepository;

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
}
