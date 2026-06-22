package co.istad.ecommerce.auditing;


import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EntityAuditorAware implements AuditorAware<String> {
    @Override
    public @NotNull Optional<String> getCurrentAuditor() {
        return Optional.of("kon khmer");
    }
}
