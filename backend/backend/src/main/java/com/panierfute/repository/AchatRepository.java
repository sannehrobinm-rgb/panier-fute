import com.panierfute.model.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AchatRepository extends JpaRepository<Achat, Long> {
    List<Achat> findByShoppingListId(Long listId);
}