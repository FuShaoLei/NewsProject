package com.fushaolei.project_android.data;

import com.fushaolei.project_android.constant.HttpConstant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit单例工具类
 */
public class DataManager {
    private volatile static DataManager dataManager;
    private ApiService apiService;

    public static DataManager getInstance() {
        if (dataManager == null) {
            synchronized (Object.class) {
                if (dataManager == null) {
                    dataManager = new DataManager();
                }
            }
        }
        return dataManager;
    }

    public ApiService getApiService() {
        return apiService;
    }

    private DataManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstant.SERVICE_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }
}