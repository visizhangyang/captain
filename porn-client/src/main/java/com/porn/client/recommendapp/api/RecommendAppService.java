package com.porn.client.recommendapp.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.recommendapp.dto.RecommendAppDeleteDTO;
import com.porn.client.recommendapp.dto.RecommendAppQueryDTO;
import com.porn.client.recommendapp.dto.RecommendAppQueryPageDTO;
import com.porn.client.recommendapp.dto.RecommendAppSaveOrUpdateDTO;
import com.porn.client.recommendapp.vo.RecommendAppVo;

import java.util.List;

public interface RecommendAppService {
    RecommendAppVo queryRecommendApp(RecommendAppQueryDTO paramRecommendAppQueryDTO);

    List<RecommendAppVo> queryRecommendAppList(RecommendAppQueryDTO paramRecommendAppQueryDTO);

    PageVo<RecommendAppVo> queryPage(RecommendAppQueryPageDTO paramRecommendAppQueryPageDTO);

    RecommendAppVo saveOrUpdate(RecommendAppSaveOrUpdateDTO paramRecommendAppSaveOrUpdateDTO);

    Boolean delete(RecommendAppDeleteDTO paramRecommendAppDeleteDTO);
}

