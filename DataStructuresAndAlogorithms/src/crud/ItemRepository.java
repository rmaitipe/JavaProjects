package crud;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
//public interface ItemRepository extends JpaRepository<Item,Long> {
public interface ItemRepository extends CrudRepository<Item,Long> {
/*
https://springjava.com/spring-boot/response-entity-in-rest-api-crud-example-spring-boot/
https://medium.com/@piyumisudusinghe/error-handling-in-rest-api-4fa7bdbe379a
https://www.forestadmin.com/blog/an-experts-guide-to-crud-apis-designing-a-robust-one/
https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring
    ListCrudRepository

Spring Data’s method derivation, the query is generated from the method name and signature.
    https://www.baeldung.com/spring-data-derived-queries
    Spring Data JPA supports find, read, query, count and get
    We can also use Distinct, First or Top to remove duplicates or limit our result set:
        List<User> findTop3ByAge()

    https://www.baeldung.com/spring-data-sorting
    Sorting With the OrderBy Method Keyword
    All we need to do is include the keyword OrderBy in our method name, along with the property name(s) and direction (Asc or Desc) by which we want to sort.
        List<Passenger> findByOrderBySeatNumberAsc();
        List<Passenger> findByLastNameOrderBySeatNumberAsc(String lastName);
    Alternate second option is to include a Sort parameter specifying the property name(s) and direction by sort is desired
        List<Passenger> passengers = repository.findAll(Sort.by(Sort.Direction.ASC, "seatNumber"));
 */

    List<Item> findByItemDesc(String itemDesc);

    //Custom Query
    @Query("SELECT b FROM Item b WHERE b.orderDate > :date")
    List<Item> findByOrderedDateAfter(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM ITEM WHERE ID = ?1", nativeQuery = true)
    Item getItemByItemID(String itemId);

    @Query(value = "SELECT * FROM ITEM WHERE ID = ?1 AND DESC = ?2", nativeQuery = true)
    Item getUserByUserIDAndAddress(String itemId, String desc);
}
