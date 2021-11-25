package com.tech.bookMyShow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("inside CustomLoginSuccessHandler-->handle()");
        String targetUrl = this.determineTargetUrl(authentication);
        if (response.isCommitted()) {
            //logger.debug(LogMessage.format("Did not redirect to %s since response already committed.", targetUrl));
            return;
        }
        //} else {
            RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }

    protected String determineTargetUrl(Authentication authentication){
        System.out.println("inside determineTargetUrl ");
        String url="/login?error=true";
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a:authorities){
            roles.add(a.getAuthority());
        }
        if(roles.contains("ADMIN")){
            url="/admin";
        }else if(roles.contains("USER")){
            System.out.println("customHandler");
            url="/home";
        }
        return url;
    }
}
