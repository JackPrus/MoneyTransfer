package by.prus.moneytransfer.dao;

import by.prus.moneytransfer.model.entity.PhoneData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDataDao extends CrudRepository<PhoneData, Long> {
}
