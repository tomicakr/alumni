package hr.petsonly.web.api;

import hr.petsonly.model.details.ServiceDetails;
import hr.petsonly.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/services")
public class ServiceAPI {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceAPI(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @ResponseBody
    @GetMapping
    public List<ServiceDetails> getServices() {
        return serviceRepository.findAll().stream().map(ServiceDetails::new).collect(Collectors.toList());
    }

}
