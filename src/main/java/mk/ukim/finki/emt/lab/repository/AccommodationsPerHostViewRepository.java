package mk.ukim.finki.emt.lab.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.lab.model.views.AccommodationsByHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationsPerHostViewRepository extends JpaRepository<AccommodationsByHostView, Long> {
    AccommodationsByHostView findByHostId(Long hostId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_per_host", nativeQuery = true)
    void refreshMaterializedView();
}
