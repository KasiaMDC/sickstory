package com.kasia.sickstory.sickness;

import com.kasia.sickstory.patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sickness")
public class Sickness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    private String name;
    @Column (name = "start_date")
    LocalDate startDate;
    @Column (name = "end_date")
    LocalDate endDate;
    private String symptoms;
    @Column (name = "doctor_appointment")
    private int doctorAppointment;
    @Column (name = "file_with_recommendations")
    private byte[] fileWithRecommendations;
    @Column (name = "comments_to_the_doc_appointment")
    private String commentsToTheDoctorsAppointment;

    private String medicine;

    @ManyToOne
    private Patient patient;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public int getDoctorAppointment() {
        return doctorAppointment;
    }

    public void setDoctorAppointment(int doctorAppointment) {
        this.doctorAppointment = doctorAppointment;
    }

    public byte[] getFileWithRecommendations() {
        return fileWithRecommendations;
    }

    public void setFileWithRecommendations(byte[] fileWithRecommendations) {
        this.fileWithRecommendations = fileWithRecommendations;
    }

    public String getCommentsToTheDoctorsAppointment() {
        return commentsToTheDoctorsAppointment;
    }

    public void setCommentsToTheDoctorsAppointment(String commentsToTheDoctorsAppointment) {
        this.commentsToTheDoctorsAppointment = commentsToTheDoctorsAppointment;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
