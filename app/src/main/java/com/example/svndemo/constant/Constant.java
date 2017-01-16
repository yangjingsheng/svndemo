package com.example.svndemo.constant;

/**
 * Created by 星期八 on 2017/1/16.
 */
public interface Constant {
    //超市接口
    String XIN_KUANG="http://api.zaijiadd.com/store/2487/category?ts=1484308564&sign=1f1064961af51eec";
    //首页接口
    String SHR_QU="http://api.zaijiadd.com/v1/_index/Index.action?sid=2487&ts=1484308947&sign=3d0cc13c557cd5ae";
    //特价专区
    String FA_XIAN="http://api.zaijiadd.com/store/2487/category/0/goods?is_discount=1&offset=0&size=30&ts=1484309305&sign=6bf4073ed1aada59";
    //店铺评分
    String SHI_YI="http://api.zaijiadd.com/store/2487/commentList?offset=0&size=30&ts=1484309658&sign=7ea5519d8b9f5b52";
    //超市每个fragment的接口
    String CONTSATNT="http://api.zaijiadd.com/store/1862/category/3/goods?is_discount=0&offset=0&size=30&ts=1484554105&sign=6b218bbe4b0e9829";
}
