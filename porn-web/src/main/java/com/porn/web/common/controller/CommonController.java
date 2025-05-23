package com.porn.web.common.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.common.entity.PairPlus;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.MinioDownloadDTO;
import com.porn.client.minio.dto.MinioUploadDTO;
import com.porn.client.minio.vo.MinioDownloadVo;
import com.porn.client.minio.vo.MinioUploadVo;
import com.porn.client.mobile.api.MobileApiService;
import com.porn.client.mobile.dto.MobileDTO;
import com.porn.client.mobile.dto.MobileExtDTO;
import com.porn.client.nickname.api.NickNameApiService;
import com.porn.client.nickname.dto.NickNameUploadDTO;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeCacheItemDTO;
import com.porn.client.richtext.api.RichTextApiService;
import com.porn.client.richtext.dto.RichTextQueryDTO;
import com.porn.client.richtext.vo.RichTextVo;
import com.porn.client.server.dto.GenCaptchaDTO;
import com.porn.client.user.api.UserApiService;
import com.porn.client.user.dto.UserLoginDTO;
import com.porn.client.user.dto.UserLogoutDTO;
import com.porn.client.user.vo.CaptchaVo;
import com.porn.client.user.vo.UserLoginVo;
import com.porn.client.user.vo.UserLogoutVo;
import com.porn.service.message.impl.MessageServer;
import com.porn.web.common.msg.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Api(tags = {"公共请求"})
@RestController
@RequestMapping({"/common"})
public class CommonController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);


    @Autowired
    private UserApiService userApiService;


    @Autowired
    private MinioApiService minioApiService;


    @Autowired
    private MobileApiService mobileApiService;


    @Autowired
    private NickNameApiService nickNameApiService;

    @Autowired
    private RichTextApiService richTextApiService;

    @Autowired
    private MessageServer messageServer;

    @Autowired
    private RechargeApiService rechargeApiService;


    @ApiOperation("验证码")
    @PostMapping({"/captcha"})
    public Msg<CaptchaVo> captcha(@RequestBody GenCaptchaDTO genCaptchaDTO) {
        getResponse().setHeader("Pragma", "no-cache");
        getResponse().setHeader("Cache-Control", "no-cache");
        getResponse().setDateHeader("Expires", 0L);
        Objects.requireNonNull(this.userApiService);
        return Msg.<GenCaptchaDTO, CaptchaVo>executeService(genCaptchaDTO, this.userApiService::genCaptcha);
    }

    @ApiOperation("批量上传文件")
    @PostMapping({"/batchUpload"})
    public Msg<List<MinioUploadVo>> batchUpload(MultipartFile[] files) {
        if (ObjectUtil.isEmpty(files)) {
            return Msg.success(Collections.EMPTY_LIST);
        }
        List<MinioUploadDTO> uploadList = new ArrayList<>();
        for (MultipartFile file : files) {


            try {


                MinioUploadDTO upload = MinioUploadDTO.builder().fileName(file.getOriginalFilename()).contentType(file.getContentType()).fileSize(Long.valueOf(file.getSize())).fileBytes(IoUtil.readBytes(file.getInputStream())).build();
                uploadList.add(upload);
            } catch (Exception e) {
                return Msg.error(e);
            }
        }
        Objects.requireNonNull(this.minioApiService);
        return Msg.executeSimpleService(uploadList, this.minioApiService::batchUpload);
    }

    @ApiOperation("下载文件")
    @PostMapping({"/download"})
    public void captcha(@RequestBody MinioDownloadDTO minioDownloadDTO) {
        try {
            MinioDownloadVo minioDownloadVo = this.minioApiService.download(minioDownloadDTO);
            downloadFile(minioDownloadVo.getFileName(), minioDownloadVo.getFileBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @ApiOperation("文件预览")
    @GetMapping({"/prev"})
    public void prev(@Param("filePath") String filePath) {
        try {
            MinioDownloadVo minioDownloadVo = this.minioApiService.download(MinioDownloadDTO.builder().filePath(filePath).build());
            doPrev(minioDownloadVo.getContentType(), minioDownloadVo.getFileBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @ApiOperation("用户登录")
    @PostMapping({"/login"})
    public Msg<UserLoginVo> login(@RequestBody UserLoginDTO userLoginDTO) {
        userLoginDTO.setLoginIp(getRemoteIP());
        Objects.requireNonNull(this.userApiService);
        return Msg.<UserLoginDTO, UserLoginVo>executeService(userLoginDTO, this.userApiService::login);
    }

    @ApiOperation("用户登出")
    @PostMapping({"/logout"})
    public Msg<UserLogoutVo> logout(@RequestBody UserLogoutDTO userLogoutDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.<UserLogoutDTO, UserLogoutVo>executeService(userLogoutDTO, this.userApiService::logout);
    }


    @ApiOperation("手机端入口")
    @PostMapping({"/mobile"})
    public Msg<String> mobile(@RequestBody MobileDTO mobileDTO) {
        MobileExtDTO mobileExtDTO = MobileExtDTO.builder().remoteIP(getRemoteIP()).userAgent(getRequest().getHeader("User-Agent")).build();
        mobileDTO.setMobileExtDTO(mobileExtDTO);
        Objects.requireNonNull(this.mobileApiService);
        return Msg.executeSimpleService(mobileDTO, this.mobileApiService::request);
    }

    @ApiOperation("批量上传文件")
    @PostMapping({"/upload"})
    public Msg<Boolean> upload(@RequestParam("file") MultipartFile file) throws Exception {
        List<String> lines = ListUtil.list(false);
        IoUtil.readLines(file.getInputStream(), Charset.forName("UTF-8"), lines);
        NickNameUploadDTO nickNameUploadDTO = NickNameUploadDTO.builder().lines(lines).build();
        Objects.requireNonNull(this.nickNameApiService);
        return Msg.<NickNameUploadDTO, Boolean>executeService(nickNameUploadDTO, this.nickNameApiService::upload);
    }

    @ApiOperation("查询语言列表")
    @PostMapping({"/queryLanguageList"})
    public Msg<List<PairPlus>> queryLanguageList() {
        return Msg.success(Arrays.<LangTypeEnum>stream(LangTypeEnum.values()).map(lt -> PairPlus.of(lt.name().toLowerCase(), lt.getDescription(), lt.getType())).collect(Collectors.toList()));
    }

    @ApiOperation("查询")
    @PostMapping({"/queryRichText"})
    public Msg<RichTextVo> queryRichText(@RequestBody RichTextQueryDTO richTextQueryDTO) {
        if (ObjectUtil.isEmpty(richTextQueryDTO.getLangType())) {
            if (ObjectUtil.isEmpty(richTextQueryDTO.getLangTypeName())) {

                richTextQueryDTO.setLangType(LangTypeEnum.ZH.getType());
            } else if (ObjectUtil.isEmpty(LangTypeEnum.queryByTag(richTextQueryDTO.getLangTypeName()))) {

                richTextQueryDTO.setLangType(LangTypeEnum.EN.getType());
            } else {

                LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(richTextQueryDTO.getLangTypeName());
                richTextQueryDTO.setLangType(langTypeEnum.getType());
            }
        }
        Objects.requireNonNull(this.richTextApiService);
        return Msg.<RichTextQueryDTO, RichTextVo>executeService(richTextQueryDTO, this.richTextApiService::queryRichText);
    }


    @ApiOperation("查询")
    @RequestMapping({"/status"})
    public Msg<String> queryStatus() {
        String id = IdUtil.simpleUUID();
        this.messageServer.sendMessage(id);
        return Msg.success(id);
    }

    @PostMapping({"/addCacheItem"})
    public Msg<Boolean> addCacheItem(@RequestBody RechargeCacheItemDTO rechargeCacheItemDTO) {
        Objects.requireNonNull(this.rechargeApiService);
        return Msg.executeSimpleService(rechargeCacheItemDTO, this.rechargeApiService::addCacheItem);
    }

    @PostMapping({"/clearCacheItem"})
    public Msg<Boolean> clearCacheItem(@RequestBody RechargeCacheItemDTO rechargeCacheItemDTO) {
        Objects.requireNonNull(this.rechargeApiService);
        return Msg.executeService(this.rechargeApiService::clearCacheItem);
    }

    @PostMapping({"/queryCacheItem"})
    public Msg<RechargeCacheItemDTO> queryCacheItem(String code) {
        Objects.requireNonNull(this.rechargeApiService);
        return Msg.executeSimpleService(code, this.rechargeApiService::getCacheItem);
    }
}

