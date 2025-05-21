
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/message/config/MessageConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */