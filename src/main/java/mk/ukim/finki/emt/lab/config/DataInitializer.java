package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }


    @PostConstruct
    public void init() {
        Country country1 = new Country("Austria", "Europe");
        Country country2 = new Country("UK", "Europe");
        Country country3 = new Country("Canada", "North America");
        Country country4 = new Country("USA", "North America");
        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);
        countryRepository.save(country4);

        Host host1 = new Host("Felix", "Huber", country1);
        Host host2 = new Host("Klara", "Meier", country1);
        Host host3 = new Host("Oliver", "Brown", country2);
        Host host4 = new Host("Charlotte", "Taylor", country2);
        Host host5 = new Host("Liam", "Robinson", country3);
        Host host6 = new Host("Sarah", "Davis", country4);
        Host host7 = new Host("Jessica", "White", country4);
        hostRepository.save(host1);
        hostRepository.save(host2);
        hostRepository.save(host3);
        hostRepository.save(host4);
        hostRepository.save(host5);
        hostRepository.save(host6);
        hostRepository.save(host7);

        Accommodation acc1 = new Accommodation("Sunny Apartments", Category.APARTMENT, host1, 10, false);
        Accommodation acc2 = new Accommodation("Mountain Retreat", Category.HOTEL, host2, 5, true);
        Accommodation acc3 = new Accommodation("Seaside Villa", Category.HOUSE, host3, 3, false);
        Accommodation acc4 = new Accommodation("City Center Loft", Category.FLAT, host4, 2, true);
        Accommodation acc5 = new Accommodation("Lake House", Category.HOUSE, host5, 4, false);
        Accommodation acc6 = new Accommodation("Budget Motel", Category.MOTEL, host6, 20, true);
        Accommodation acc7 = new Accommodation("Luxury Resort", Category.HOTEL, host7, 50, false);
        accommodationRepository.save(acc1);
        accommodationRepository.save(acc2);
        accommodationRepository.save(acc3);
        accommodationRepository.save(acc4);
        accommodationRepository.save(acc5);
        accommodationRepository.save(acc6);
        accommodationRepository.save(acc7);


    }

}
