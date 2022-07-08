package by.prus.moneytransfer.dao;

import by.prus.moneytransfer.model.entity.EmailData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataDao extends CrudRepository<EmailData, Long> {
}
