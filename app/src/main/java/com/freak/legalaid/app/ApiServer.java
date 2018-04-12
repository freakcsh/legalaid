package com.freak.legalaid.app;

import com.freak.legalaid.bean.HomeDataBean;
import com.freak.legalaid.bean.MemberRank;
import com.freak.legalaid.library.rxjava.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/2/4.
 * api请求接口
 */

public interface ApiServer {

    @GET("toutiao/index")
    Observable<HttpResult<HomeDataBean>> getNews(@Query("type") String type,
                                                 @Query("key") String key);

    /**
     * 会员等级
     */
    @GET("cashing/m/memberCard/memberRanks.htm")
    Observable<HttpResult<List<MemberRank>>> memberRanks(@Query("token") String token);
}
