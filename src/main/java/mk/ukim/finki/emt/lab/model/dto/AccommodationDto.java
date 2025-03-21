package mk.ukim.finki.emt.lab.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.Host;

@Data
public class AccommodationDto {

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private Long host;
    private Integer numRooms;
    private boolean isRented;

    public AccommodationDto(){}
    public AccommodationDto(String name, Category category, Long host, Integer numRooms, boolean isRented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = isRented;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getHost() {
        return host;
    }

    public void setHost(Long host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
