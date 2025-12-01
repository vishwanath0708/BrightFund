package BrightFund.com.Auditing;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAwarelml")
public class AuditAwarelml implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(new SecurityContextHolder()
                .getContext().getAuthentication().getName()
        );
    }


}
