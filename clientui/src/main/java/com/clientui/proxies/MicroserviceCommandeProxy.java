package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server-commandes", url = "localhost:9004/microservice-commandes/")
public interface MicroserviceCommandeProxy {

    @PostMapping("/commandes")
    void ajouterCommande(@RequestBody CommandeBean commande);

    @GetMapping("/commandes")
    List<CommandeBean> afficherToutesCommandes();

    @GetMapping("/commandes/{id}")
    CommandeBean recupererUneCommande(@PathVariable("id") int idCommande);

    @DeleteMapping("/commandes/{id}")
    void supprimerCommande(@PathVariable("id") int idCommande);

    @PutMapping("/commandes/{id}")
    void updateCommande(@PathVariable("id") int idCommande, @RequestBody CommandeBean commande);
}