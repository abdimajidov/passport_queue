package uz.sardor.passportQueueSystems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.sardor.passportQueueSystems.entity.AutoQueue;

import java.util.Optional;

@Repository
public interface AutoQueueRepository extends JpaRepository<AutoQueue,Long> {
    @Query(value = "select a from AutoQueue a where a.typeQueue=?1 order by a.created desc")
    Optional<AutoQueue> getAutoQueueByCreated(String type);
    @Query(value = "select * from auto_queue a order by a.created limit 1",nativeQuery = true)
    Optional<AutoQueue> getLastQueue();

    @Query(value = "select count(*) from auto_queue",nativeQuery = true)
    long count();
}
