package com.example.hotel.rest;

import com.example.hotel.dto.*;
import com.example.hotel.exception.*;
import com.example.hotel.models.*;
import com.example.hotel.repository.*;
import com.example.hotel.security.*;
import com.example.hotel.service.*;
import com.example.hotel.validation.*;
import io.swagger.annotations.*;
import lombok.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;


@AllArgsConstructor
@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

    public static final String topicExchangeName = "spring-boot-exchange";
    private RequestValidation requestValidation;

    private KorisnikService korisnikService;

    private KorisnikRepository userRepository;

    private RezervacijaClient rezervacijaClient;

    private RacunClient racunClient;
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "Create User", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping()
    public String create(@Valid  @RequestBody KorisnikRequest korisnik) {
        return korisnikService.create(korisnik);
    }

    @ApiOperation(value = "Get All Users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/all")

    List<Korisnik> all() {
        return korisnikService.findAll();
    }

    @ApiOperation(value = "Get User By Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{id}")
    Korisnik findById(@PathVariable Long id) {
        return korisnikService.findById(id);
    }

    @ApiOperation(value = "Update User By Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping()
    String update(@Valid  @RequestBody Korisnik korisnik) {
        return korisnikService.updateKorisnik( korisnik);

    }

    @ApiOperation(value = "Delete User By Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/{id}")
    String deleteKorisnik(@PathVariable Long id) {
        return korisnikService.deleteById(id);
    }

    @ApiOperation(value = "Get All Bills made By User with Id ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{userId}/bill")
    Racun findRacunByUserId(@PathVariable Long userId) {
        return korisnikService.findByUserId(userId);
    }

    @ApiOperation(value = "Get All Bills created By Employee with Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{userId}/employee/bill")


    List<RacunEntity> findRacunByUserIdEmployee(@PathVariable Long userId) {
        //requestValidation.validateId(userId);
        return korisnikService.findByUserIdEmployee(userId);
    }

    @ApiOperation(value = "Pay bills with ids", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/employee/bill/pay")
    String pay(List<Long> ids) {
        return korisnikService.pay(ids);
    }

    @ApiOperation(value = "Get All Reservations by User with Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/reservation/{id}")
    List<Rezervacija> getGuestsReservations(@PathVariable Long id) {
        return korisnikService.allByUserId(id);
    }

    @ApiOperation(value = "Get All Reservations created By Employee with Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/reservation/employee/{id}")
    List<Rezervacija> getEmployeeReservations(@PathVariable Long id) {
        //requestValidation.validateEmployee(id);
        return korisnikService.allByCreatedBy(id);
    }

    @ApiOperation(value = "Login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/login")
    String login(@RequestBody KorisnikLoginRequest korisnikLoginRequest) {
        //requestValidation.validateLogin(korisnikLoginRequest);
        return "Successfully logged in!";
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public KorisnikEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    @ApiOperation(value = "Create Reservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/{userId}/create/reservation")
    String createReservation(@PathVariable Long userId,@RequestBody Rezervacija rezervacija) {
        rezervacija.setCreatedBy(userId);
        rezervacijaClient.create(rezervacija);
        return "Successfully created Reservation!";
    }
@GetMapping("/racun/all")
    List<RacunEntity> allBills(){
        return racunClient.all();
}
@PostMapping("/racun/create")
    String  createRacun( @RequestBody  RacunRequest racunRequest) {
   return  racunClient.create(racunRequest);
}

@PostMapping("/pay/bill/{racunId}")
    String payBill(@PathVariable Long racunId){
        //poslati poruku na racun i na rezervacija servis da je placeno
    rabbitTemplate.convertAndSend(topicExchangeName,
            "foo.bar.baz", racunId);
    return "We have sent a message! :" + racunId;
}
    @DeleteMapping("/rezervacija/{id}")
    String deleteRezervacija(@PathVariable Long id) {
        return rezervacijaClient.deleteRezervacija(id);
    }
    @GetMapping("/rezervacija/{id}")
    Rezervacija getRezervacija(@PathVariable Long id) {
        return rezervacijaClient.findById(id);
    }
    @PutMapping("/rezervacija/{id}")
    String update(@PathVariable Long id, @RequestBody Rezervacija rezervacija) {
        return rezervacijaClient.update(id,rezervacija);
    }

    @DeleteMapping("/rezervacija/soba/{id}")
    String deleteSoba(@PathVariable Long id){
        return rezervacijaClient.deleteSoba(id);
    }

    @PutMapping("/rezervacija/soba/{id}")
    String update(@PathVariable Long id, @RequestBody Soba soba){
        return rezervacijaClient.update(id,soba);
    }

    @GetMapping("/rezervacija/soba/all")
    List<SobaEntity> allRooms(){
        return rezervacijaClient.all();
    }

    @PostMapping("/rezervacija/soba")
    public String create(@RequestBody Soba soba){
        return rezervacijaClient.create(soba);
    }
    @GetMapping("/rezervacija/soba/{id}")
    Soba findRoomById(@PathVariable Long id) {
        return rezervacijaClient.findRoomById(id);
    }
    @DeleteMapping("/racun/delete/{id}")
    String deleteRacun(@PathVariable Long id){
        return racunClient.deleteRacun(id);
    }



    @GetMapping("/racun/{id}")
    Racun findByIdRacun(@PathVariable Long id){
        return racunClient.findById(id);
    }

    @PutMapping("/racun/update/{id}")
    String update(@Valid  @RequestBody RacunRequest racun,@PathVariable Long id){
        return racunClient.update(racun,id);
    }
    @PostMapping("/rezervacija/sala")
    public String create(@RequestBody Sala sala){
        return rezervacijaClient.create(sala);
    }

    @GetMapping("/rezervacija/sala/all")
    List<SalaEntity> allHalls(){
        return rezervacijaClient.allHalls();
    }

    @PutMapping("/rezervacija/sala/{id}")
    String update(@PathVariable Long id, @RequestBody Sala sala){
        return rezervacijaClient.update(id, sala);
    }

    @DeleteMapping("/rezervacija/sala/{id}")
    String deleteSala(@PathVariable Long id){
        return rezervacijaClient.deleteSala(id);
    }

    @GetMapping("/rezervacija/sala/{id}")
    Sala findHallById(@PathVariable Long id){
        return rezervacijaClient.findHallById(id);
    }
}