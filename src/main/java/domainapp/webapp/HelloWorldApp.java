package domainapp.webapp;

import domainapp.modules.hello.HelloWorldModule;

import org.apache.isis.config.IsisPresets;
import org.apache.isis.jdo.IsisBootDataNucleus;
import org.apache.isis.runtime.spring.IsisBoot;
import org.apache.isis.security.shiro.IsisBootSecurityShiro;
import org.apache.isis.viewer.restfulobjects.IsisBootViewerRestfulObjects;
import org.apache.isis.viewer.wicket.viewer.IsisBootViewerWicket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;

@SpringBootApplication
@Import({
    HelloWorldApp.AppManifest.class,
})
public class HelloWorldApp extends SpringBootServletInitializer {

    @Configuration
    @PropertySources({
            @PropertySource(IsisPresets.NoTranslations),
            @PropertySource(IsisPresets.DataNucleusAutoCreate),
    })
    @Import({
            IsisBoot.class,
            IsisBootSecurityShiro.class,
            IsisBootDataNucleus.class,
            IsisBootViewerRestfulObjects.class,
            IsisBootViewerWicket.class,
            HelloWorldModule.class
    })
    public static class AppManifest {
    }

    /**
     * @implNote this is to support the <em>Spring Boot Maven Plugin</em>, which auto-detects an
     * entry point by searching for classes having a {@code main(...)}
     */
    public static void main(String[] args) {
        SpringApplication.run(new Class[] { HelloWorldApp.class }, args);
    }

}