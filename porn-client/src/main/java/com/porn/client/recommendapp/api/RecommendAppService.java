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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recommendapp/api/RecommendAppService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */