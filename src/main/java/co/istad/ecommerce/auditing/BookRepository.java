package co.istad.ecommerce.auditing;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query("""
    UPDATE Book c
    SET c.name = :name,
        c.description = :description
    WHERE c.id = :id
""")
    void updateBook(int id, String name, String description);

}
