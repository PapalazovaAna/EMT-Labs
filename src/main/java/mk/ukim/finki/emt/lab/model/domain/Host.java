package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.Guest;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Country country;

    @ManyToMany
    @JoinTable(name = "hosts_guests", joinColumns = @JoinColumn(name = "host_id"), inverseJoinColumns = @JoinColumn(name = "guest_id"))
    private List<Guest> guests;

    public Host(){
        guests = new ArrayList<>();
    }

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Guest guest) {
        this.guests.add(guest);
    }
}
