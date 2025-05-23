package com.porn.web.mng.business;

import com.porn.client.goods.api.GoodsRuleApiService;
import com.porn.client.goods.dto.GoodsRuleQueryDTO;
import com.porn.client.goods.dto.GoodsRuleSaveOrUpdateDTO;
import com.porn.client.goods.vo.GoodsRuleVo;
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


@Api(tags = {"后台管理-业务管理-商品规则管理"})
@RestController
@RequestMapping({"/mng/business/goodsrule"})
public class GoodsRuleController
        extends BaseController {
    @Autowired
    private GoodsRuleApiService goodsRuleApiService;

    @ApiOperation("查询")
    @PostMapping({"/queryGoodsRule"})
    public Msg<GoodsRuleVo> queryGoodsRule(@RequestBody GoodsRuleQueryDTO goodsRuleQueryDTO) {
        Objects.requireNonNull(this.goodsRuleApiService);
        return Msg.executeService(goodsRuleQueryDTO, this.goodsRuleApiService::queryGoodsRule);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<GoodsRuleVo> saveOrUpdate(@RequestBody GoodsRuleSaveOrUpdateDTO goodsRuleSaveOrUpdateDTO) {
        Objects.requireNonNull(this.goodsRuleApiService);
        return Msg.executeService(goodsRuleSaveOrUpdateDTO, this.goodsRuleApiService::saveOrUpdate);
    }
}

