package com.chinasofti.guguanjia.utils;

/**
 * Created by hshuai on 2017/3/1.
 */

public class UrlConfig {

    public static final String BASEURL="http://192.168.1.110:8080/guguanjia";
    //地址注释
    public static final String LOGIN_URL = BASEURL + "/api/user/loginByPhone";
    public static final String TEST_URL = BASEURL + "/api/user/updateUserInfo";
    //发送短信验证
    public static final String YZM = BASEURL + "/api/message/sendRegisterVerifCode";
    //修改密码
    public static final  String FDPWD = BASEURL + "/api/user/retrievePassword";
    /**
     * 我的工单
     */
    public static final String MY_WORK_ORDERS_URL=BASEURL+"/api/workOrder/findMyWorkOrders";
    /**
     * 在途工单
     */
    public static final String ON_ROAD_WORK_ORDERS_URL=BASEURL+"/api/workOrder/findByTransport";
    /**
     * 发起工单
     */
    public static final String  ADD_WORK_ORDERS_URL=BASEURL+"/api/workOrder/add";

    /**
     * 电子台账
     * 所有工单
     */
    public static final String FIND_ALL_ORDERS_URL=BASEURL+"/api/workOrder/findAll";

    /**
     * 电子台账
     * 待运输工单
     */
    public static final String WAIT_TRANSPORT_ORDERS_URL=BASEURL+"/api/workOrder/findByWaitTransport";
    /**
     * 电子台账
     * 已结束工单
     */
    public static final String COMPLETED_ORDERS_URL=BASEURL+"/api/workOrder/findByFinish";

    /**
     * 工单详情
     */
    public static final String DERAIL_ORDERS_URL=BASEURL+"/api/workOrder/detail";
    /**
     * 废物大类
     */
    public static final String WASTE_TYPE_URL=BASEURL+"/api/waste/findAll";
    /**
     * 废物小类
     */
    public static final String WASTE_URL=BASEURL+"/api/waste/findByWasteType";

}
