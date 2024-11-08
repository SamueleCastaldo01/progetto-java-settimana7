package castaldosamuele.progetto_java_settimana7.security;

import castaldosamuele.progetto_java_settimana7.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTCheckerFilter {

    @Autowired
    private JWT jwt;

}
