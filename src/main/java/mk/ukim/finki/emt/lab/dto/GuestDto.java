package mk.ukim.finki.emt.lab.dto;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.Host;

import java.util.List;

@Data
public class GuestDto {
    private String name;
    private String surname;
    @ManyToOne
    private Country country;
    @ManyToMany(mappedBy = "guests")
    private List<Host> hosts;

    public GuestDto(){}

    public GuestDto(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
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

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }
}
