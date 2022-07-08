package by.prus.moneytransfer.dao;

import by.prus.moneytransfer.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByName(String name);

    Optional<User> findById(Long id);

    @Query(value="select * from accountsdata.public.users u where u.name like %:namePart%" ,nativeQuery=true)
    List<User> searchByNamePart(@Param("namePart")String line);

    @Query(value="select * from accountsdata.public.users u inner join phone_data p on p.user_id = u.id  where p.phone =?1", nativeQuery=true)
    List<User> searchByPhone(Long phone);

    @Query(value="select * from accountsdata.public.users u inner join email_data e on e.user_id = u.id where e.email =:email" ,nativeQuery=true)
    List<User> searchByEmail(@Param("email") String email);

    List<User> findAllByDateOfBirthAfter(LocalDate date);
}
