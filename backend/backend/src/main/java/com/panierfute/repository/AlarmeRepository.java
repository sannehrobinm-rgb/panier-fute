import com.panierfute.model.Alarme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlarmeRepository extends JpaRepository<Alarme, Long> {
    List<Alarme> findByUserId(Long userId);
    List<Alarme> findByUserIdAndActive(Long userId, Boolean active);
}