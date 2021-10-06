package com.fushaolei.project_android.data;

import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.Comment;
import com.fushaolei.project_android.data.bean.InsertComment;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.data.bean.Pager;
import com.fushaolei.project_android.data.bean.Tree;
import com.fushaolei.project_android.data.bean.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String TEST_URL = "/test";

    String HOME_ROOT = "/home";
    String HOME_TREE = HOME_ROOT + "/list/tree";
    String HOME_LIST = HOME_ROOT + "/list/{page}/json/{cid}";
    String HOME_NEWS_DETAIL = HOME_ROOT + "/news/{id}";

    // user
    String USER = "/user/";

    String ID = "{id}";

    /**
     * 测试用
     */
    @GET(TEST_URL)
    Observable<BaseResponse<String>> test();

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @GET(USER + ID)
    Observable<BaseResponse<User>> getUser(@Path("id") int id);


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PUT(USER)
    Observable<BaseResponse<Integer>> updateUser(@Body User user);

    /**
     * 登录和注册
     *
     * @param user
     * @return
     */
    @POST(USER)
    Observable<BaseResponse<Integer>> loginUser(@Body User user);


    @Multipart
    @POST("/user/upload/{id}")
    Observable<BaseResponse<Integer>> uploadAvator(@Path("id") int id, @Part MultipartBody.Part file);

    @GET("/news/list")
    Observable<BaseResponse<List<Tree>>> getTree(@Query("type") int type);

    @GET("/news/list/{page}/json/{cid}")
    Observable<BaseResponse<Pager<News>>> getNewsList(@Path("page") int page, @Path("cid") int cid);

    @GET("/news/{id}")
    Observable<BaseResponse<News>> getNewsById(@Path("id") int id);


    @GET("/comments/{id}")
    Observable<BaseResponse<List<Comment>>> getCommentList(@Path("id") int id);

    @POST("/comments/")
    Observable<BaseResponse<Integer>> insertComment(@Body InsertComment insertComment);

    @GET("/collect/")
    Observable<BaseResponse<Boolean>> getCollect(@Query("news_id") int news_id, @Query("user_id") int user_id);

    @POST("/collect/")
    Observable<BaseResponse<Boolean>> clickOrCancelCollect(@Query("news_id") int news_id, @Query("user_id") int user_id);

    @GET("/collect/list/{id}")
    Observable<BaseResponse<List<News>>> getUserCollect(@Path("id") int id);
}
