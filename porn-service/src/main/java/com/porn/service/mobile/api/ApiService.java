
package com.porn.service.mobile.api;



import com.porn.client.mobile.dto.CmdRequestDTO;




















 public interface ApiService<R>
         {
       R cmd(CmdRequestDTO paramCmdRequestDTO);

    
    
    default String getApi() {
        /* 28 */
        return "";
        
    }

    
    
    
    
    
    
    default String getVersion() {
        /* 36 */
        return "1.0.0";
        
    }

    
    
    
    
    
    
    default boolean validateToken() {
        /* 44 */
        return true;
        
    }
    
}
