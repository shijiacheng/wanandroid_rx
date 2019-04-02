package com.shijc.wanandroidrx.ui.account.bean;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.account.bean
 * @Description:
 * @date 2019/4/2 下午 7:55
 */
public class LoginResult {

    /**
     * data : {"chapterTops":[],"collectIds":[7568,7761,7654,7892,-1,7907,7922,7921,7916,7917,7918,7909,7919,7908,7910,7815,7816,7817,7818,7874,7834,7832,7828,7825,7889,7829,7824,7820,7836,7837,7833,-1,-1],"email":"","icon":"","id":16396,"password":"","token":"","type":0,"username":"iot_shijiacheng"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * chapterTops : []
         * collectIds : [7568,7761,7654,7892,-1,7907,7922,7921,7916,7917,7918,7909,7919,7908,7910,7815,7816,7817,7818,7874,7834,7832,7828,7825,7889,7829,7824,7820,7836,7837,7833,-1,-1]
         * email :
         * icon :
         * id : 16396
         * password :
         * token :
         * type : 0
         * username : iot_shijiacheng
         */

        private String email;
        private String icon;
        private int id;
        private String password;
        private String token;
        private int type;
        private String username;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }
}
