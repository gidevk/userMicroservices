package com.expriment.utils;

import com.expriment.utils.audit.LoggerClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@ConfigurationProperties
@WebListener
public class MyListener implements ServletContextListener{


    @Autowired
    AppProps appProps;


    private static final Log logger = LogFactory.getLog(SpringApplication.class);

    public void contextInitialized(ServletContextEvent event) {
        System.out.println(System.getProperty("${spring.profiles.active}"));
//        String activeProfiles=appProps.getActiveProfile();
        try{
            System.out.println("MyListener hhh");
            LoggerClass.appLayerLogger.info("MyListener ");
           /* if (activeProfiles.equalsIgnoreCase("dev")) {
                LoggerClass.appLayerLogger.info("SIT properties enabled. Active Profiles : {}",activeProfiles);
                SSLContext context = SSLContext.getInstance("TLSv1.2");
                context.init(null, null, null);
//                CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLContext(context).build();
//                httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            }*/
//            logStartupProfileInfo(appProps.getActiveProfile());

        }catch(Exception e){e.printStackTrace();}
    }

    private Class<?> mainApplicationClass;

    protected Log getApplicationLog() {
        return this.mainApplicationClass == null ? logger : LogFactory.getLog(this.mainApplicationClass);
    }

    protected void logStartupProfileInfo(ConfigurableApplicationContext context) {
        Log log = this.getApplicationLog();
        if (log.isInfoEnabled()) {
            String[] activeProfiles = context.getEnvironment().getActiveProfiles();
            if (ObjectUtils.isEmpty(activeProfiles)) {
                String[] defaultProfiles = context.getEnvironment().getDefaultProfiles();
                log.info("No active profile set, falling back to default profiles: " + StringUtils.arrayToCommaDelimitedString(defaultProfiles));
            } else {
                log.info("The following profiles are active: " + StringUtils.arrayToCommaDelimitedString(activeProfiles));
            }
        }

    }
    public void contextDestroyed(ServletContextEvent arg0) {}
}