package co.istad.ecommerce.feature.file;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String caption;

    private Long size;
    private String mediaType;
}
