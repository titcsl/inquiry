package space.titcsl.inquiries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.titcsl.inquiries.entity.OutreCemtity;

@Repository
public interface OutCrumpyRepo extends JpaRepository<OutreCemtity, String > {
}
