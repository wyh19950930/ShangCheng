package com.jymj.zhglxt.ui.bean.homepage;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2023/1/30 17:04
 */
public class AddAddressBean {

    /**
     * code : 200
     * data : {"addressId":"21","name":"里衬","mobile":"15859575955","region":"北京市大兴区奥宇科技大厦停车场","detailedAddress":"不想玩了","label":null,"status":1}
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
         * addressId : 21
         * name : 里衬
         * mobile : 15859575955
         * region : 北京市大兴区奥宇科技大厦停车场
         * detailedAddress : 不想玩了
         * label : null
         * status : 1
         */

        private String addressId;
        private String name;
        private String mobile;
        private String region;
        private String detailedAddress;
        private Object label;
        private int status;

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getDetailedAddress() {
            return detailedAddress;
        }

        public void setDetailedAddress(String detailedAddress) {
            this.detailedAddress = detailedAddress;
        }

        public Object getLabel() {
            return label;
        }

        public void setLabel(Object label) {
            this.label = label;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
