package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ClientController {


    @Autowired
    private MicroserviceProduitsProxy produitsProxy;

    @Autowired
    private MicroserviceCommandeProxy commandesProxy;

    /*
     * Opération qui récupère la liste des produits et les affiche dans la page d'accueil.
     * Les produits sont récupérés grâce à produitsProxy.
     * */
    @RequestMapping("/")
    public String accueil(Model model) {
        // Retrieve the list of products
        List<ProductBean> produits = produitsProxy.listeDesProduits();

        // Pass the list of products to the model for rendering
        model.addAttribute("produits", produits);

        return "Accueil";  // Thymeleaf template
    }



    /*
     * Opération qui récupère et affiche toutes les commandes existantes.
     * Affiche toutes les commandes avec l'aide du microservice commande.
     * */
    @RequestMapping(value ="/toutes-commandes")
    public String Commandes(Model model) {
        // Fetch the list of all orders
        List<CommandeBean> toutesCommandes = commandesProxy.afficherToutesCommandes();

        // Add the list of orders to the model
        model.addAttribute("commandes", toutesCommandes);

        return "ListeCommandes";  // Thymeleaf template for the list of orders
    }

    /*
     * Opération qui récupère les détails d'un produit.
     * Affiche les informations du produit avec l'aide du microservice produit.
     * */
    @RequestMapping("/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model) {
        // Retrieve the product details
        ProductBean produit = produitsProxy.recupererUnProduit(id);

        // Pass the product details to the model
        model.addAttribute("produit", produit);

        return "FicheProduit";  // Thymeleaf template for product details
    }

    /*
     * Opération qui crée une commande en appelant le microservice des commandes pour passer une commande.
     * On crée une commande et on redirige vers la page de la liste des commandes.
     * */
    @RequestMapping(value = "/commander-produit/{idProduit}/{montant}", method = RequestMethod.GET)
    public String passerCommande(@PathVariable int idProduit,
                                 @PathVariable Double montant,
                                 @RequestParam int quantite,
                                 @RequestParam String Description,  // This will now be populated from produit.titre
                                 Model model) {
        // Validate input parameters
        if (idProduit <= 0 || montant <= 0 || quantite <= 0) {
            throw new IllegalArgumentException("Invalid product ID, amount, or quantity.");
        }

        CommandeBean commande = new CommandeBean();
        commande.setProductId(idProduit);
        commande.setQuantite(quantite); // Set quantity from the request parameter
        commande.setDateCommande(new Date());
        commande.setDescription(Description); // Set description from the request parameter (produit.titre)
        commande.setMontant(montant);

        try {
            // Call the microservice to create the order
            commandesProxy.ajouterCommande(commande);
            return "redirect:/"; // Redirect to the list of orders
        } catch (Exception e) {
            model.addAttribute("error", "Unable to process the order.");
            return "ErrorPage"; // A template for showing errors
        }
    }



    /*
     * Opération qui permet de supprimer une commande.
     * On fait appel au microservice pour supprimer une commande spécifique.
     * */
    @RequestMapping(value = "/supprimer-commande/{idCommande}")
    public String supprimerCommande(@PathVariable int idCommande, Model model) {
        try {
            // Call the microservice to delete the order
            commandesProxy.supprimerCommande(idCommande);
            return "redirect:/toutes-commandes"; // Redirect to the list of orders after deletion
        } catch (Exception e) {
            model.addAttribute("error", "Unable to delete the order.");
            return "ErrorPage"; // A template for showing errors
        }
    }

    /*
     * Opération qui permet de mettre à jour une commande.
     * On fait appel au microservice pour mettre à jour une commande spécifique.
     * */
    @RequestMapping(value = "/modifier-commande/{id}/{productId}", method = RequestMethod.GET)
    public String afficherFormulaireModification(@PathVariable int id,
                                                 @PathVariable int productId,
                                                 Model model) {
        // Retrieve the order details by id
        CommandeBean commande = commandesProxy.recupererUneCommande(id);

        // Retrieve the associated product for the order by productId
        ProductBean produit = produitsProxy.recupererUnProduit(productId);  // Adjust this based on your actual object structure

        // Pass the order details and product details to the model
        model.addAttribute("commande", commande);
        model.addAttribute("produit", produit);  // Adding the product details to the model

        return "Update";  // Thymeleaf template for updating order
    }


    @RequestMapping(value = "/mettre-a-jour-commande", method = RequestMethod.POST)
    public String mettreAJourCommande(@RequestParam int idCommande,
                                      @RequestParam int quantite,
                                      @RequestParam String description,
                                      Model model) {
        // Retrieve the order details
        CommandeBean commande = commandesProxy.recupererUneCommande(idCommande);

        // Update the quantity in the order
        if (quantite > 0) {
            commande.setQuantite(quantite);
        } else {
            model.addAttribute("error", "Quantity must be greater than 0.");
            return "ErrorPage"; // Error page if quantity is invalid
        }

            commande.setDescription(description);

        try {
            // Update the order in the system
            commandesProxy.updateCommande(commande.getId(), commande);
            return "redirect:/toutes-commandes"; // Redirect to the list of orders
        } catch (Exception e) {
            model.addAttribute("error", "Unable to update the order.");
            return "ErrorPage"; // Error page in case of failure
        }
    }



}