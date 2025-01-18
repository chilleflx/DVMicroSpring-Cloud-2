package com.clientui.beans;

import lombok.Data;

import java.util.Date;

@Data
public class CommandeBean {

    private int id;

    private Integer productId;

    private Date dateCommande;

    private Integer quantite;

    private Boolean commandePayee;

    private double montant;

    private String description;
}