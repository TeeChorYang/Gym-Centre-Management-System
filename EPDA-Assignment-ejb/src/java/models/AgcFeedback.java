/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author TheOne
 */
@Entity
public class AgcFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String customerId;
    private String classId;
    private String trainerId;
    private String feedback;
    private Date feedbackDate;

    public AgcFeedback() {
    }

    public AgcFeedback(Integer id, String customerId, String classId, String trainerId, String feedback, Date feedbackDate) {
        this.id = id;
        this.customerId = customerId;
        this.classId = classId;
        this.trainerId = trainerId;
        this.feedback = feedback;
        this.feedbackDate = feedbackDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    
    
    /**
     * Get the value of feedbackDate
     *
     * @return the value of feedbackDate
     */
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * Set the value of feedbackDate
     *
     * @param feedbackDate new value of feedbackDate
     */
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
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
        if (!(object instanceof AgcFeedback)) {
            return false;
        }
        AgcFeedback other = (AgcFeedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AgcFeedback[ id=" + id + " ]";
    }
    
}
