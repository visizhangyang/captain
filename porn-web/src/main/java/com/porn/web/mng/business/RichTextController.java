package com.porn.web.mng.business;

import com.porn.client.common.entity.Pair;
import com.porn.client.richtext.api.RichTextApiService;
import com.porn.client.richtext.dto.RichTextQueryDTO;
import com.porn.client.richtext.dto.RichTextSaveOrUpdateDTO;
import com.porn.client.richtext.vo.RichTextVo;
import com.porn.web.common.controller.BaseController;
import com.porn.web.common.msg.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@Api(tags = {"后台管理-业务管理-富文本管理"})
@RestController
@RequestMapping({"/mng/business/richtext"})
public class RichTextController
        extends BaseController {
    @Autowired
    private RichTextApiService richTextApiService;

    @ApiOperation("查询类型列表")
    @PostMapping({"/queryTypes"})
    public Msg<List<Pair>> queryTypes() {
        return Msg.success(this.richTextApiService.queryTypes());
    }

    @ApiOperation("查询")
    @PostMapping({"/queryRichText"})
    public Msg<RichTextVo> queryRichText(@RequestBody RichTextQueryDTO richTextQueryDTO) {
        Objects.requireNonNull(this.richTextApiService);
        return Msg.executeService(richTextQueryDTO, this.richTextApiService::queryRichText);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<RichTextVo> saveOrUpdate(@RequestBody RichTextSaveOrUpdateDTO richTextSaveOrUpdateDTO) {
        Objects.requireNonNull(this.richTextApiService);
        return Msg.executeService(richTextSaveOrUpdateDTO, this.richTextApiService::saveOrUpdate);
    }
}

