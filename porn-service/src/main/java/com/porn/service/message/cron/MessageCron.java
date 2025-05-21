
package com.porn.service.message.cron;



import com.porn.service.message.impl.MessageServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;










@Component
 public class MessageCron
         {
    /* 14 */   private static final Logger log = LoggerFactory.getLogger(MessageCron.class);
    
    
    
    
    @Autowired
     private MessageServer messageServer;

    
    
    
    
    @Scheduled(cron = "0 0 1 * * ?")
     public void doCompare() {
        /* 25 */
        this.messageServer.getMsgs();
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/message/cron/MessageCron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */