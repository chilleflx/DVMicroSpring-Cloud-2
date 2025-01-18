package com.mcommandes.web.controller;


import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@EnableCircuitBreaker
@Configuration
@EnableHystrixDashboard
@RestController
public class CommandeController {

    @Autowired
    CommandesDao commandesDao;

    @GetMapping("/myMessage")

    @HystrixCommand(fallbackMethod = "myHistrixbuildFallbackMessage",
            commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")},
            threadPoolKey = "messageThreadPool")

//    @HystrixCommand(fallbackMethod = "myHistrixbuildFallbackMessage",
//	commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
//
    public String getMessage() throws InterruptedException {
        System.out.println("Message from EmployeeController.getMessage(): Begin To sleep for 3 scondes ");
        Thread.sleep(3000);
        return "Message from EmployeeController.getMessage(): End from sleep for 3 scondes ";
    }

    private String myHistrixbuildFallbackMessage() {
        return "Message from myHistrixbuildFallbackMessage() : Hystrix Fallback message ( after timeout : 1 second )";
    }

    // Create a new order
    @PostMapping(value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande) {
        System.out.println("Commande received: " + commande);
        Commande nouvelleCommande = commandesDao.save(commande);
        System.out.println("Saved Commande: " + nouvelleCommande);
        if (nouvelleCommande == null)
            throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<>(nouvelleCommande, HttpStatus.CREATED);
    }

    // Retrieve an order by ID
    @GetMapping(value = "/commandes/{id}")
    public ResponseEntity<Commande> recupererUneCommande(@PathVariable int id) {
        Optional<Commande> commande = commandesDao.findById(id);

        if (!commande.isPresent())
            throw new CommandeNotFoundException("Cette commande n'existe pas");

        return new ResponseEntity<>(commande.get(), HttpStatus.OK);
    }

    // Retrieve all orders
    @GetMapping(value = "/commandes")
    public ResponseEntity<List<Commande>> afficherToutesCommandes() {
        List<Commande> toutesCommandes = commandesDao.findAll();

        if (toutesCommandes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(toutesCommandes, HttpStatus.OK);
    }

    // Update an existing order
    @PutMapping(value = "/commandes/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable int id, @RequestBody Commande commandeDetails) {
        Optional<Commande> optionalCommande = commandesDao.findById(id);

        if (!optionalCommande.isPresent())
            throw new CommandeNotFoundException("Commande avec l'ID " + id + " introuvable.");

        Commande existingCommande = optionalCommande.get();
        existingCommande.setDateCommande(commandeDetails.getDateCommande());
        existingCommande.setProductId(commandeDetails.getProductId());
        existingCommande.setQuantite(commandeDetails.getQuantite());
        existingCommande.setMontant(commandeDetails.getMontant());
        existingCommande.setDescription(commandeDetails.getDescription());

        Commande updatedCommande = commandesDao.save(existingCommande);
        return new ResponseEntity<>(updatedCommande, HttpStatus.OK);
    }

    // Delete an order by ID
    @DeleteMapping(value = "/commandes/{id}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable int id) {
        Optional<Commande> commande = commandesDao.findById(id);

        if (!commande.isPresent())
            throw new CommandeNotFoundException("Commande avec l'ID " + id + " introuvable.");

        commandesDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}