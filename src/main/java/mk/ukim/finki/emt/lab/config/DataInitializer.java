package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.model.domain.*;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.model.enumerations.Role;
import mk.ukim.finki.emt.lab.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository, GuestRepository guestRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


//    @PostConstruct
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

        hostRepository.save(host1);
        hostRepository.save(host2);
        hostRepository.save(host3);
        hostRepository.save(host4);

        List<Host> guest1Hosts = List.of(host1, host2);
        List<Host> guest2Hosts = List.of(host1, host3);
        List<Host> guest3Hosts = List.of(host4);
        List<Host> guest4Hosts = List.of(host2, host3);

        Guest guest1 = new Guest("Anna", "Black", country1, guest1Hosts);
        Guest guest2 = new Guest("Mike", "Patterson", country2, guest2Hosts);
        Guest guest3 = new Guest("Jack", "Stone", country3, guest3Hosts);
        Guest guest4 = new Guest("Harry", "Swift", country4, guest4Hosts);

        guestRepository.save(guest1);
        guestRepository.save(guest2);
        guestRepository.save(guest3);
        guestRepository.save(guest4);

        Accommodation acc1 = new Accommodation("Sunny Apartments", Category.APARTMENT, host1, 10, false);
        Accommodation acc2 = new Accommodation("Mountain Retreat", Category.HOTEL, host2, 5, true);
        Accommodation acc3 = new Accommodation("Seaside Villa", Category.HOUSE, host3, 3, false);
        Accommodation acc4 = new Accommodation("City Center Loft", Category.FLAT, host1, 2, true);
        Accommodation acc5 = new Accommodation("Lake House", Category.HOUSE, host1, 4, false);
        Accommodation acc6 = new Accommodation("Budget Motel", Category.MOTEL, host2, 20, true);
        Accommodation acc7 = new Accommodation("Luxury Resort", Category.HOTEL, host3, 50, false);
        accommodationRepository.save(acc1);
        accommodationRepository.save(acc2);
        accommodationRepository.save(acc3);
        accommodationRepository.save(acc4);
        accommodationRepository.save(acc5);
        accommodationRepository.save(acc6);
        accommodationRepository.save(acc7);


        userRepository.save(new User("ap", passwordEncoder.encode("ap"), "Ana", "Papalazova", Role.ROLE_USER));
        userRepository.save(new User("dp", passwordEncoder.encode("dp"), "Darko", "Popov", Role.ROLE_HOST));
    }

}
