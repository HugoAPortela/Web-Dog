package br.com.webdog.persistence.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String addressNumber;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;
    private int score;
    private boolean status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User manager;

    @OneToMany(fetch = FetchType.EAGER)
    private Map<Long, Kennel> kennels = new HashMap<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Map<Long, Comment> comments = new HashMap<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Map<Long, Image> images = new HashMap<>();

    public Host() {
    }

    public Host(String name, String address, String addressNumber, String neighborhood,
                String postalCode, String city, String state, int score, boolean status) {
        this.name = name;
        this.address = address;
        this.addressNumber = addressNumber;
        this.neighborhood = neighborhood;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.score = score;
        this.status = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Map<Long, Kennel> getKennels() {
        return kennels;
    }

    public void setKennels(Map<Long, Kennel> kennels) {
        this.kennels = kennels;
    }

    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public Map<Long, Image> getImages() {
        return images;
    }

    public void setImages(Map<Long, Image> images) {
        this.images = images;
    }
}
