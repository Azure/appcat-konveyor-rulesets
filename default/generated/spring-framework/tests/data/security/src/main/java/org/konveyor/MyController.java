package org.konveyor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.DeferredSecurityContext;
import org.springframework.security.web.context.SecurityContextRepository;

@Controller
@SessionAttributes("user")
public class MyController {
}

public class CustomSecurityContextRepository implements SecurityContextRepository {
    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
        return new DeferredSecurityContext() {
            private SecurityContext securityContext;
            private boolean isGenerated;

            @Override
            public SecurityContext get() {
                if (this.securityContext == null) {
                    this.securityContext = getContextOrNull(request);
                    if (this.securityContext == null) {
                        SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();
                        this.securityContext = strategy.createEmptyContext();
                        this.isGenerated = true;
                    }
                }
                return this.securityContext;
            }

            @Override
            public boolean isGenerated() {
                get();
                return this.isGenerated;
            }
        };
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }

    private SecurityContext getContextOrNull(HttpServletRequest request) {
        return null;
    }
}
