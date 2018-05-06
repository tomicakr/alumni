package hr.alumni.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import hr.alumni.model.Pet;
import hr.alumni.model.Reservation;
import hr.alumni.model.Service;
import hr.alumni.model.User;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.model.details.PetDetails;
import hr.alumni.model.details.ReservationDetails;
import hr.alumni.model.details.ServiceDetails;
import hr.alumni.model.details.UserDetailsBasic;
import hr.alumni.model.details.UserDetailsMore;
import hr.alumni.model.form.AddReservationForm;
import hr.alumni.model.form.EditReservationForm;
import hr.alumni.model.form.EditUserForm;
import hr.alumni.repository.PetRepository;
import hr.alumni.repository.ReservationRepository;
import hr.alumni.repository.ServiceRepository;
import hr.alumni.repository.UserRepository;
import hr.alumni.service.FormFactory;
import hr.alumni.service.ReservationService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@PreAuthorize("@webSecurityConfig.checkUserId(authentication, #uid)")
@RequestMapping(value = "users/{uid}/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final ServiceRepository serviceRepository;
    private final FormFactory formFactory;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, UserRepository userRepository, PetRepository petRepository, ServiceRepository serviceRepository, FormFactory formFactory) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.serviceRepository = serviceRepository;
        this.formFactory = formFactory;
    }

    @ResponseBody
    @GetMapping
    public List<ReservationDetails> showAllReservationsOfAUser(@PathVariable UUID uid) {

        User user = userRepository.findOne(uid);
        List<Reservation> userReservations = reservationRepository.findAllByUser(user);
        List<ReservationDetails> reservationDetails = new ArrayList<>();

        userReservations.forEach(reservation -> {
            reservationDetails.add(new ReservationDetails(reservation));
        });

        return reservationDetails;
    }

    @GetMapping(value = "/new")
    public String showReservationForm(Model model, @PathVariable UUID uid) {

        List<Pet> pets = petRepository.findByOwnerId(uid.toString());
        List<PetDetails> petDetails = new ArrayList<>();
        pets.forEach(pet -> petDetails.add(new PetDetails(pet)));

        List<User> employees = userRepository.findAllEmployees();
        List<UserDetailsBasic> employeeDetails = new ArrayList<>();
        employees.forEach(employee -> employeeDetails.add(new UserDetailsBasic(employee)));

        List<Service> services = serviceRepository.findAll();
        List<ServiceDetails> serviceDetails = new ArrayList<>();
        services.forEach(service -> serviceDetails.add(new ServiceDetails(service)));

        model.addAttribute("userId", uid);
        model.addAttribute("pets", petDetails);
        model.addAttribute("employees", employeeDetails);
        model.addAttribute("services", serviceDetails);

        return "newReservation";
    }

    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDetails> createReservation(@RequestBody @Valid AddReservationForm reservationForm,
                                                                BindingResult result,
                                                                @PathVariable UUID uid) {
        if (result.hasErrors()) {System.out.println(result);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reservationForm.setOwner(uid);
        Reservation reservation = formFactory.createReservationFromForm(reservationForm);

        reservation = reservationRepository.save(reservation);
        ReservationDetails reservationDetails = new ReservationDetails(reservation);

        return new ResponseEntity<>(reservationDetails, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public String showSelectedReservation(Model model, @PathVariable UUID id, @PathVariable UUID uid) {

        Reservation reservation = reservationRepository.getOne(id);
        ReservationDetails reservationDetails = new ReservationDetails(reservation);

        model.addAttribute("reservation", reservationDetails);
        return "reservation";
    }

    @GetMapping(value = "/{id}/edit")
    public String showReservationEditForm(Model model, @PathVariable UUID id, @PathVariable UUID uid) {

        return "editReservation";
    }

    
    
    @PutMapping(value = "/{id}")
    public String saveReservation(Model model, @PathVariable UUID uid, @PathVariable UUID id,
                                  @Valid AddReservationForm reservation, BindingResult result) {

        if (result.hasErrors()) {

            model.addAttribute("errorMessage", result.toString());
            return "editReservation";
        }

        User user = userRepository.getOne(uid);

        Reservation res = reservationRepository.findOne(id);

        /*if (formFactory.editReservationFromForm(res, reservation)) {
            model.addAttribute("errorMessage", "Nema promjena.");
            return String.format("redirect:/users/%s/reservations", uid.toString());
        }*/

        userRepository.save(user);

        return String.format("redirect:/users/%s/reservations", uid.toString());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable UUID id, @PathVariable UUID uid) {

        reservationRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    

}
