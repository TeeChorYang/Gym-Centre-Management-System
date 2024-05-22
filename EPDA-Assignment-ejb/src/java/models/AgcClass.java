/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author TheOne
 */
@Entity
public class AgcClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String trainerId;
    private Double price;
    private String description;

    

    public AgcClass() {
    }

    public AgcClass(String name, String trainerId, Double price, String description, AgcClassFacade agcClassFacade) {
        String id;
        do {
            id = generateRandomId(6);
        } while (agcClassFacade.idExists(id));
        this.id = id;
        this.name = name;
        this.trainerId = trainerId;
        this.price = price;
        this.description = description;
    }
    
    private String generateRandomId(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            id.append(characters.charAt(random.nextInt(characters.length())));
        }
        return id.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgcClass)) {
            return false;
        }
        AgcClass other = (AgcClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AgcClass[ id=" + id + " ]";
    }
    
}
