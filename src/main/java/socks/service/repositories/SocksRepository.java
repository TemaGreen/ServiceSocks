package socks.service.repositories;

import socks.service.entity.SocksEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SocksRepository extends CrudRepository<SocksEntity, Long> {

    @Modifying
    @Query("update SocksEntity set amount = case when (:amount <= 0 and amount > abs(:amount)) or :amount > 0 then amount + :amount else 0 end where color = :color and cotton = :cotton")
    Integer incomeSocks(@Param("color") Integer color, @Param("cotton") Integer cotton, @Param("amount") Long amount);

    @Modifying
    @Query("update SocksEntity set amount = case when (:amount >= 0 and amount > abs(:amount)) or :amount < 0 then amount - :amount else 0 end where color = :color and cotton = :cotton")
    Integer outcomeSocks(@Param("color") Integer color, @Param("cotton") Integer cotton, @Param("amount") Long amount);

    @Query("select s from SocksEntity s where (:color is null or s.color = :color) " +
            "and (:moreThan is null or s.amount > :moreThan) " +
            "and (:lessThan is null or s.amount < :lessThan) " +
            "and (:equal is null or s.amount = :equal) " +
            "and (:cottonMoreThen is null or  :cottonMoreThen < s.cotton) " +
            "and (:cottonLessThen is null or s.cotton < :cottonLessThen) order by s.id")
    Iterable<SocksEntity> findAll(@Param("color") Integer color,
                                  @Param("moreThan") Integer moreThan,
                                  @Param("lessThan") Integer lessThan,
                                  @Param("equal") Integer equal,
                                  @Param("cottonMoreThen") Integer cottonMoreThen,
                                  @Param("cottonLessThen") Integer cottonLessThen);


    boolean existsSocksEntityByColorAndCotton(Integer color, Integer cotton);
}
