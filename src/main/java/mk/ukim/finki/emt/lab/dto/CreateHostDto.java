package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Country;

public record CreateHostDto(
        String name,
        String surname,
        Country country
) {
}
