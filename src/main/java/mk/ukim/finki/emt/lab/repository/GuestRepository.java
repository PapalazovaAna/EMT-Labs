package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
