
package com.porn.service.minio.config;



import io.minio.MinioClient;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class MinioConfig implements Serializable {
       public static final String DEFAULT_BUCKET = "attachs";
       public static final String USERMETA_FILENAME = "filename";
       public static final String USERMETA_HEADERFILENAME = "x-amz-meta-filename";
    
    @ApiModelProperty("url")
     private String url;
    @ApiModelProperty("账号, 这个值在minio控制台生成")
     private String accessKey;
    @ApiModelProperty("秘钥, 这个值在minio控制台生成")
     private String secretKey;
    @ApiModelProperty("桶名称")
    private String bucketName;
    @ApiModelProperty("cdn地址信息")
    private String cdnUrl;
    @ApiModelProperty("url")
    private String url1;
    @ApiModelProperty("账号, 这个值在minio控制台生成")
    private String accessKey1;
    @ApiModelProperty("秘钥, 这个值在minio控制台生成")
    private String secretKey1;
    @ApiModelProperty("桶名称")
    private String bucketName1;


    @Bean({"minioClient"})
     public MinioClient minioClient() {
        /* 69 */
        return MinioClient.builder()
/* 70 */.endpoint(getUrl())
/* 71 */.credentials(getAccessKey(), getSecretKey())
/* 72 */.build();
        
    }

    
    
    @Bean({"minioClient1"})
     public MinioClient minioClient1() {
        /* 77 */
        return MinioClient.builder()
/* 78 */.endpoint(getUrl1())
/* 79 */.credentials(getAccessKey1(), getSecretKey1())
/* 80 */.build();
        
    }
    
}

