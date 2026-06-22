package co.istad.ecommerce;

import co.istad.ecommerce.auditing.Book;
import co.istad.ecommerce.auditing.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class IteEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IteEcommerceApplication.class, args);
    }

//    private final BookRepository bookRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        Book book = new Book();
//        book.setName("ពេលព្ដ៏អស្ចារ្យ");
//        book.setDescription("Wonderful morning<3");
//        bookRepository.save(book);
//    }
}
