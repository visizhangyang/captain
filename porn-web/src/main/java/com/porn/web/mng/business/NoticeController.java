package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.notice.api.NoticeApiService;
import com.porn.client.notice.dto.NoticeDeleteDTO;
import com.porn.client.notice.dto.NoticeQueryPageDTO;
import com.porn.client.notice.dto.NoticeSaveOrUpdateDTO;
import com.porn.client.notice.dto.NoticeUpdateCreateTimeDTO;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.web.common.controller.BaseController;
import com.porn.web.common.msg.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@Api(tags = {"后台管理-业务管理-公告管理"})
@RestController
@RequestMapping({"/mng/business/notice"})
public class NoticeController
        extends BaseController {
    @Autowired
    private NoticeApiService noticeApiService;

    @ApiOperation("查询公告")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<NoticeVo>> queryPage(@RequestBody NoticeQueryPageDTO noticeQueryPageDTO) {
        Objects.requireNonNull(this.noticeApiService);
        return Msg.executeService(noticeQueryPageDTO, this.noticeApiService::queryPage);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<NoticeVo> saveOrUpdate(@RequestBody NoticeSaveOrUpdateDTO noticeSaveOrUpdateDTO) {
        Objects.requireNonNull(this.noticeApiService);
        return Msg.executeService(noticeSaveOrUpdateDTO, this.noticeApiService::saveOrUpdate);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody NoticeDeleteDTO noticeDeleteDTO) {
        Objects.requireNonNull(this.noticeApiService);
        return Msg.executeService(noticeDeleteDTO, this.noticeApiService::delete);
    }

    @ApiOperation("更新创建时间")
    @PostMapping({"/updateCreateTime"})
    public Msg<Boolean> updateCreateTime(@RequestBody NoticeUpdateCreateTimeDTO noticeUpdateCreateTimeDTO) {
        Objects.requireNonNull(this.noticeApiService);
        return Msg.executeService(noticeUpdateCreateTimeDTO, this.noticeApiService::updateCreateTime);
    }
}

