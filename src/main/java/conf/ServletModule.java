package conf;

import com.google.appengine.api.utils.SystemProperty;

import ninja.servlet.NinjaServletDispatcher;

public class ServletModule extends com.google.inject.servlet.ServletModule {

    @Override
    protected void configureServlets() {

        bind(NinjaServletDispatcher.class).asEagerSingleton();

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
                
            serve("/*").with(NinjaServletDispatcher.class);
            
        } else {
            // do not serve admin stuff like _ah and so on...
            // allows to call /_ah/admin and so on
            serveRegex("/(?!_ah).*").with(NinjaServletDispatcher.class);
        }
 
        
    }

}