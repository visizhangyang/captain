package com.porn.web.mng.business;

import com.porn.client.common.vo.PageVo;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.GoodsBatchSaveDTO;
import com.porn.client.goods.dto.GoodsDeleteDTO;
import com.porn.client.goods.dto.GoodsQueryPageDTO;
import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
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


@Api(tags = {"后台管理-业务管理-商品管理"})
@RestController
@RequestMapping({"/mng/business/goods"})
public class GoodsController
        extends BaseController {
    @Autowired
    private GoodsApiService goodsApiService;

    @ApiOperation("查询")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<GoodsVo>> queryPage(@RequestBody GoodsQueryPageDTO goodsQueryPageDTO) {
        goodsQueryPageDTO.setGoodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus());
        Objects.requireNonNull(this.goodsApiService);
        return Msg.executeService(goodsQueryPageDTO, this.goodsApiService::queryPage);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<GoodsVo> saveOrUpdate(@RequestBody GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO) {
        Objects.requireNonNull(this.goodsApiService);
        return Msg.executeService(goodsSaveOrUpdateDTO, this.goodsApiService::saveOrUpdate);
    }

    @ApiOperation("批量创建")
    @PostMapping({"/batchCreate"})
    public Msg<Boolean> batchCreate(@RequestBody GoodsBatchSaveDTO goodsBatchSaveDTO) {
        Objects.requireNonNull(this.goodsApiService);
        return Msg.executeService(goodsBatchSaveDTO, this.goodsApiService::batchCreate);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody GoodsDeleteDTO goodsDeleteDTO) {
        Objects.requireNonNull(this.goodsApiService);
        return Msg.executeService(goodsDeleteDTO, this.goodsApiService::delete);
    }
}

