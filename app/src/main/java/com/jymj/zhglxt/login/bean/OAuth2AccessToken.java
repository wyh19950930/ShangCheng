package com.jymj.zhglxt.login.bean;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/12/29 9:37
 * 商城登录
 */
public class OAuth2AccessToken {


    /**
     * code : 200
     * data : {"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwib3BlbklkIjoidXNlciIsInNjb3BlIjpbImFsbCJdLCJhdXRoZW50aWNhdGlvbklkZW50aXR5IjoidXNlcm5hbWUiLCJleHAiOjE2NzIzMTAyMzEsInVzZXJJZCI6MzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiMzczNDNiYWItYjhjZS00NzdkLTkyYTAtY2FkZjc0MzM5MjY3IiwiY2xpZW50X2lkIjoidXNlci1hbmRyb2lkIiwidXNlcm5hbWUiOiJ1c2VyIn0.VVWabXfUEc2Z3T0dmiquw5XLvb8z698Od7G_X8NUdY9psuRq4KqSDBAtwT6JbQgZyqj0k46xpao2cXzJEaEr8nGVgaGaomvMDwbZowmCaDmqNrOtWbBiB0_Q53Qx6R6RVX5NFbYzJGpbm7neIEBEk5cakzZeorSUv92FIIBemu9fi-umVKk_fkbLdQHqteUtlBG-SXJV8R_akPLYMopzlL59yLC3QmyGh30TvYCWxjXdvBRpdjeEaBQPa3IuV73qje2XmjCmZGn2EFMvUKAJZqEWFzxrlbX-Kzo-r1rtRcAYJFU9ZLsTiCvEBkG2WWhNzd9aNbEuCjLsCvFKqpaPJQ","token_type":"bearer","refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwib3BlbklkIjoidXNlciIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiIzNzM0M2JhYi1iOGNlLTQ3N2QtOTJhMC1jYWRmNzQzMzkyNjciLCJhdXRoZW50aWNhdGlvbklkZW50aXR5IjoidXNlcm5hbWUiLCJleHAiOjE2NzI1NDA2MzEsInVzZXJJZCI6MzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiYjg0NDI0NGYtODk4OS00ZmQ3LWE1MzItMzc2OTY1OTU4OWY3IiwiY2xpZW50X2lkIjoidXNlci1hbmRyb2lkIiwidXNlcm5hbWUiOiJ1c2VyIn0.dxWHpNyj7h2isNyipz4PaK8ftRCpz3Btjqxj1klUC7pt8C0e2kFBvfMVMT3VQBMQvS0HqWi_YWQtd_7tLBebEV0AQeWSQlkTZtSksu65kusQNey0N0ZMg7NG3LgOzeERE_udfI-xjpNxpZdLOPb4XsKP2n4UOvjxcwVhrXJgGsWcqMmFkR1NE7ilYrNqemd-NAnkIotMIfzXmvi_XcJoj6ayl35HFNjrQwRWmdqVTmihQJQtL-ZY8Nh-Ox1qxtlxbHfTFfaEOfIWAZhLpeR16dIchWsVIryFb4_0stdZrq1kZVr0dHk0h_zebUW-c58NXViIaEqTgq5gQHc2HQTcZQ","expires_in":28799,"scope":"all","openId":"user","authenticationIdentity":"username","userId":36,"username":"user","jti":"37343bab-b8ce-477d-92a0-cadf74339267"}
     * msg : OK
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * access_token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwib3BlbklkIjoidXNlciIsInNjb3BlIjpbImFsbCJdLCJhdXRoZW50aWNhdGlvbklkZW50aXR5IjoidXNlcm5hbWUiLCJleHAiOjE2NzIzMTAyMzEsInVzZXJJZCI6MzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiMzczNDNiYWItYjhjZS00NzdkLTkyYTAtY2FkZjc0MzM5MjY3IiwiY2xpZW50X2lkIjoidXNlci1hbmRyb2lkIiwidXNlcm5hbWUiOiJ1c2VyIn0.VVWabXfUEc2Z3T0dmiquw5XLvb8z698Od7G_X8NUdY9psuRq4KqSDBAtwT6JbQgZyqj0k46xpao2cXzJEaEr8nGVgaGaomvMDwbZowmCaDmqNrOtWbBiB0_Q53Qx6R6RVX5NFbYzJGpbm7neIEBEk5cakzZeorSUv92FIIBemu9fi-umVKk_fkbLdQHqteUtlBG-SXJV8R_akPLYMopzlL59yLC3QmyGh30TvYCWxjXdvBRpdjeEaBQPa3IuV73qje2XmjCmZGn2EFMvUKAJZqEWFzxrlbX-Kzo-r1rtRcAYJFU9ZLsTiCvEBkG2WWhNzd9aNbEuCjLsCvFKqpaPJQ
         * token_type : bearer
         * refresh_token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwib3BlbklkIjoidXNlciIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiIzNzM0M2JhYi1iOGNlLTQ3N2QtOTJhMC1jYWRmNzQzMzkyNjciLCJhdXRoZW50aWNhdGlvbklkZW50aXR5IjoidXNlcm5hbWUiLCJleHAiOjE2NzI1NDA2MzEsInVzZXJJZCI6MzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiYjg0NDI0NGYtODk4OS00ZmQ3LWE1MzItMzc2OTY1OTU4OWY3IiwiY2xpZW50X2lkIjoidXNlci1hbmRyb2lkIiwidXNlcm5hbWUiOiJ1c2VyIn0.dxWHpNyj7h2isNyipz4PaK8ftRCpz3Btjqxj1klUC7pt8C0e2kFBvfMVMT3VQBMQvS0HqWi_YWQtd_7tLBebEV0AQeWSQlkTZtSksu65kusQNey0N0ZMg7NG3LgOzeERE_udfI-xjpNxpZdLOPb4XsKP2n4UOvjxcwVhrXJgGsWcqMmFkR1NE7ilYrNqemd-NAnkIotMIfzXmvi_XcJoj6ayl35HFNjrQwRWmdqVTmihQJQtL-ZY8Nh-Ox1qxtlxbHfTFfaEOfIWAZhLpeR16dIchWsVIryFb4_0stdZrq1kZVr0dHk0h_zebUW-c58NXViIaEqTgq5gQHc2HQTcZQ
         * expires_in : 28799
         * scope : all
         * openId : user
         * authenticationIdentity : username
         * userId : 36
         * username : user
         * jti : 37343bab-b8ce-477d-92a0-cadf74339267
         */

        private String access_token;
        private String token_type;
        private String refresh_token;
        private int expires_in;
        private String scope;
        private String openId;
        private String authenticationIdentity;
        private int userId;
        private String username;
        private String jti;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getAuthenticationIdentity() {
            return authenticationIdentity;
        }

        public void setAuthenticationIdentity(String authenticationIdentity) {
            this.authenticationIdentity = authenticationIdentity;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getJti() {
            return jti;
        }

        public void setJti(String jti) {
            this.jti = jti;
        }
    }
}
