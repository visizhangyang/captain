
package com.porn.service.message.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;











@Configuration
 public class MessageConfig
         {

    @Bean
     public ServerEndpointExporter serverEndpointExporter() {
        /* 17 */
        return new ServerEndpointExporter();

    }

}


