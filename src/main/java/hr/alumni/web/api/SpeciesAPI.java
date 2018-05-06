package hr.alumni.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.alumni.model.Species;
import hr.alumni.repository.SpeciesRepository;

import java.util.List;

@Controller
@RequestMapping("api/species")
public class SpeciesAPI {

    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesAPI(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @ResponseBody
    @GetMapping
    public List<Species> getSpecies() {
        return speciesRepository.findAll();
    }
}
