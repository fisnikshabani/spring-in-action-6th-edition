package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.model.TacoOrder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}
