package tacos.repository;

import tacos.model.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
