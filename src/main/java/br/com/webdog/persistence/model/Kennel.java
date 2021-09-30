package br.com.webdog.persistence.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Kennel implements Comparable<Object>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private int price;
    private String kennel_number;

    @ManyToOne
    private KennelType type;

    @ManyToMany
    private Host host;

    private Map<Date, Long> days_reserved = new HashMap<>();

    @ManyToMany
    private Set<Booking> bookings = new HashSet<>();

    public Kennel() {
    }

    public Kennel(int price, String kennel_number, KennelType type, Host host) {
        this.price = price;
        this.kennel_number = kennel_number;
        this.type = type;
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getKennel_number() {
        return kennel_number;
    }

    public void setKennel_number(String kennel_number) {
        this.kennel_number = kennel_number;
    }

    public KennelType getType() {
        return type;
    }

    public void setType(KennelType type) {
        this.type = type;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Map<Date, Long> getDays_reserved() {
        return days_reserved;
    }

    public void setDays_reserved(Map<Date, Long> days_reserved) {
        this.days_reserved = days_reserved;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public int compareTo(Object o){
        return getKennel_number().compareTo(((Kennel) o).getKennel_number());
    }
}
