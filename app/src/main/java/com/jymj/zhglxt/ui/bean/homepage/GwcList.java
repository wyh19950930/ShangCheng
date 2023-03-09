package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2023/1/29 14:52
 */
public class GwcList implements Serializable {

    /**
     * code : 200
     * data : {"content":[{"shoppingCartId":"60","mdseId":"501","pictureInfo":{"pictureId":"501","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665628845961_8af943a5.jpg","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"测试商品（多规格）","number":"10099","price":19.9,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"506","specA":{"specId":"683","key":"尺寸","value":"XL"},"specB":{"specId":"684","key":"颜色","value":"白色"},"specC":{"specId":"685","key":"套餐","value":"套餐一"},"price":0.01,"totalInventory":997,"remainingStock":996,"number":"90007","pictureList":[{"pictureId":"508","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665629007389_59bbbc50.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"506","cardId":null}]},"status":1,"quantity":2},{"shoppingCartId":"61","mdseId":"501","pictureInfo":{"pictureId":"501","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665628845961_8af943a5.jpg","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"测试商品（多规格）","number":"10099","price":19.9,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"502","specA":{"specId":"674","key":"尺寸","value":"XL"},"specB":{"specId":"675","key":"颜色","value":"红色"},"specC":{"specId":"676","key":"套餐","value":"套餐一"},"price":0.01,"totalInventory":995,"remainingStock":995,"number":"90005","pictureList":[{"pictureId":"504","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665629002983_56f5a690.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"502","cardId":null}]},"status":1,"quantity":2},{"shoppingCartId":"53","mdseId":"37","pictureInfo":{"pictureId":"188","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918296750_cfb7c6fd.png","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"坡峰岭｜儿童免费票 （6岁以下含6周岁）儿童、身高1.2米以下儿童（须有成人带领）","number":"20002","price":0.01,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"59","specA":{"specId":"643","key":"默认","value":"默认"},"specB":null,"specC":null,"price":0.01,"totalInventory":96,"remainingStock":63,"number":"20002","pictureList":[{"pictureId":"193","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918296750_cfb7c6fd.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"194","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918299838_ffce8b56.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"195","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918302148_ed555938.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"196","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918304467_8c8252c3.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"197","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918307051_b1013a17.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null}]},"status":1,"quantity":1},{"shoppingCartId":"54","mdseId":"509","pictureInfo":{"pictureId":"554","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1670377496198_edece207.jpg","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"矿泉水","number":"1111111","price":0.04,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"520","specA":{"specId":"616","key":"默认","value":"默认"},"specB":null,"specC":null,"price":0.04,"totalInventory":5,"remainingStock":4,"number":"1111111","pictureList":[{"pictureId":"556","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1670377496198_edece207.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"520","cardId":null},{"pictureId":"557","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1670377508803_b0c29596.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"520","cardId":null}]},"status":1,"quantity":1}],"totalPages":1,"totalElements":"4","number":1,"size":20,"first":true,"last":true,"numberOfElements":4,"empty":false}
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

    public static class DataBean implements Serializable {
        /**
         * content : [{"shoppingCartId":"60","mdseId":"501","pictureInfo":{"pictureId":"501","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665628845961_8af943a5.jpg","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"测试商品（多规格）","number":"10099","price":19.9,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"506","specA":{"specId":"683","key":"尺寸","value":"XL"},"specB":{"specId":"684","key":"颜色","value":"白色"},"specC":{"specId":"685","key":"套餐","value":"套餐一"},"price":0.01,"totalInventory":997,"remainingStock":996,"number":"90007","pictureList":[{"pictureId":"508","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665629007389_59bbbc50.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"506","cardId":null}]},"status":1,"quantity":2},{"shoppingCartId":"61","mdseId":"501","pictureInfo":{"pictureId":"501","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665628845961_8af943a5.jpg","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"测试商品（多规格）","number":"10099","price":19.9,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"502","specA":{"specId":"674","key":"尺寸","value":"XL"},"specB":{"specId":"675","key":"颜色","value":"红色"},"specC":{"specId":"676","key":"套餐","value":"套餐一"},"price":0.01,"totalInventory":995,"remainingStock":995,"number":"90005","pictureList":[{"pictureId":"504","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665629002983_56f5a690.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"502","cardId":null}]},"status":1,"quantity":2},{"shoppingCartId":"53","mdseId":"37","pictureInfo":{"pictureId":"188","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918296750_cfb7c6fd.png","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"坡峰岭｜儿童免费票 （6岁以下含6周岁）儿童、身高1.2米以下儿童（须有成人带领）","number":"20002","price":0.01,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"59","specA":{"specId":"643","key":"默认","value":"默认"},"specB":null,"specC":null,"price":0.01,"totalInventory":96,"remainingStock":63,"number":"20002","pictureList":[{"pictureId":"193","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918296750_cfb7c6fd.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"194","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918299838_ffce8b56.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"195","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918302148_ed555938.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"196","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918304467_8c8252c3.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null},{"pictureId":"197","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1663918307051_b1013a17.png","type":"STOCK_SPEC","mdseId":null,"stockId":"59","cardId":null}]},"status":1,"quantity":1},{"shoppingCartId":"54","mdseId":"509","pictureInfo":{"pictureId":"554","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1670377496198_edece207.jpg","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null},"name":"矿泉水","number":"1111111","price":0.04,"startingQuantity":1,"shopInfo":{"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"},"stockInfo":{"stockId":"520","specA":{"specId":"616","key":"默认","value":"默认"},"specB":null,"specC":null,"price":0.04,"totalInventory":5,"remainingStock":4,"number":"1111111","pictureList":[{"pictureId":"556","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1670377496198_edece207.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"520","cardId":null},{"pictureId":"557","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1670377508803_b0c29596.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"520","cardId":null}]},"status":1,"quantity":1}]
         * totalPages : 1
         * totalElements : 4
         * number : 1
         * size : 20
         * first : true
         * last : true
         * numberOfElements : 4
         * empty : false
         */

        private int totalPages;
        private String totalElements;
        private int number;
        private int size;
        private boolean first;
        private boolean last;
        private int numberOfElements;
        private boolean empty;
        private List<ContentBean> content;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public String getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(String totalElements) {
            this.totalElements = totalElements;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean implements Serializable {
            /**
             * shoppingCartId : 60
             * mdseId : 501
             * pictureInfo : {"pictureId":"501","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665628845961_8af943a5.jpg","type":"MDSE_PIC","mdseId":null,"stockId":null,"cardId":null}
             * name : 测试商品（多规格）
             * number : 10099
             * price : 19.9
             * startingQuantity : 1
             * shopInfo : {"shopId":"6","name":"老白麻酱场","address":"什刹海","director":"白上树","mobile":"13252101478","status":1,"inBusiness":1,"businessStartTime":"01:00:00","businessEndTime":"21:00:00","longitude":"116.39751289749995","latitude":"39.93719273908455"}
             * stockInfo : {"stockId":"506","specA":{"specId":"683","key":"尺寸","value":"XL"},"specB":{"specId":"684","key":"颜色","value":"白色"},"specC":{"specId":"685","key":"套餐","value":"套餐一"},"price":0.01,"totalInventory":997,"remainingStock":996,"number":"90007","pictureList":[{"pictureId":"508","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665629007389_59bbbc50.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"506","cardId":null}]}
             * status : 1
             * quantity : 2
             */

            private String shoppingCartId;
            private String mdseId;
            private PictureInfoBean pictureInfo;
            private String name;
            private String number;
            private double price;
            private int startingQuantity;
            private ShopInfoBean shopInfo;
            private StockInfoBean stockInfo;
            private int status;//1商品 2卡产品
            private int quantity;
            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public String getShoppingCartId() {
                return shoppingCartId;
            }

            public void setShoppingCartId(String shoppingCartId) {
                this.shoppingCartId = shoppingCartId;
            }

            public String getMdseId() {
                return mdseId;
            }

            public void setMdseId(String mdseId) {
                this.mdseId = mdseId;
            }

            public PictureInfoBean getPictureInfo() {
                if (pictureInfo==null){
                    pictureInfo = new PictureInfoBean();
                }
                return pictureInfo;
            }

            public void setPictureInfo(PictureInfoBean pictureInfo) {
                this.pictureInfo = pictureInfo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getStartingQuantity() {
                return startingQuantity;
            }

            public void setStartingQuantity(int startingQuantity) {
                this.startingQuantity = startingQuantity;
            }

            public ShopInfoBean getShopInfo() {
                if (shopInfo == null){
                    shopInfo = new ShopInfoBean();
                }
                return shopInfo;
            }

            public void setShopInfo(ShopInfoBean shopInfo) {
                this.shopInfo = shopInfo;
            }

            public StockInfoBean getStockInfo() {
                if (stockInfo==null){
                    stockInfo = new StockInfoBean();
                }
                return stockInfo;
            }

            public void setStockInfo(StockInfoBean stockInfo) {
                this.stockInfo = stockInfo;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public static class PictureInfoBean implements Serializable{
                /**
                 * pictureId : 501
                 * url : http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665628845961_8af943a5.jpg
                 * type : MDSE_PIC
                 * mdseId : null
                 * stockId : null
                 * cardId : null
                 */

                private String pictureId;
                private String url;
                private String type;
                private Object mdseId;
                private Object stockId;
                private Object cardId;

                public String getPictureId() {
                    return pictureId;
                }

                public void setPictureId(String pictureId) {
                    this.pictureId = pictureId;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Object getMdseId() {
                    return mdseId;
                }

                public void setMdseId(Object mdseId) {
                    this.mdseId = mdseId;
                }

                public Object getStockId() {
                    return stockId;
                }

                public void setStockId(Object stockId) {
                    this.stockId = stockId;
                }

                public Object getCardId() {
                    return cardId;
                }

                public void setCardId(Object cardId) {
                    this.cardId = cardId;
                }
            }

            public static class ShopInfoBean implements Serializable{
                /**
                 * shopId : 6
                 * name : 老白麻酱场
                 * address : 什刹海
                 * director : 白上树
                 * mobile : 13252101478
                 * status : 1
                 * inBusiness : 1
                 * businessStartTime : 01:00:00
                 * businessEndTime : 21:00:00
                 * longitude : 116.39751289749995
                 * latitude : 39.93719273908455
                 */

                private String shopId;
                private String name;
                private String address;
                private String director;
                private String mobile;
                private int status;
                private int inBusiness;
                private String businessStartTime;
                private String businessEndTime;
                private String longitude;
                private String latitude;

                public String getShopId() {
                    return shopId == null?"0":shopId;
                }

                public void setShopId(String shopId) {
                    this.shopId = shopId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getDirector() {
                    return director;
                }

                public void setDirector(String director) {
                    this.director = director;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getInBusiness() {
                    return inBusiness;
                }

                public void setInBusiness(int inBusiness) {
                    this.inBusiness = inBusiness;
                }

                public String getBusinessStartTime() {
                    return businessStartTime;
                }

                public void setBusinessStartTime(String businessStartTime) {
                    this.businessStartTime = businessStartTime;
                }

                public String getBusinessEndTime() {
                    return businessEndTime;
                }

                public void setBusinessEndTime(String businessEndTime) {
                    this.businessEndTime = businessEndTime;
                }

                public String getLongitude() {
                    return longitude;
                }

                public void setLongitude(String longitude) {
                    this.longitude = longitude;
                }

                public String getLatitude() {
                    return latitude;
                }

                public void setLatitude(String latitude) {
                    this.latitude = latitude;
                }
            }

            public static class StockInfoBean implements Serializable{
                /**
                 * stockId : 506
                 * specA : {"specId":"683","key":"尺寸","value":"XL"}
                 * specB : {"specId":"684","key":"颜色","value":"白色"}
                 * specC : {"specId":"685","key":"套餐","value":"套餐一"}
                 * price : 0.01
                 * totalInventory : 997
                 * remainingStock : 996
                 * number : 90007
                 * pictureList : [{"pictureId":"508","url":"http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665629007389_59bbbc50.jpg","type":"STOCK_SPEC","mdseId":null,"stockId":"506","cardId":null}]
                 */

                private String stockId;
                private SpecABean specA;
                private SpecBBean specB;
                private SpecCBean specC;
                private double price;
                private int totalInventory;
                private int remainingStock;
                private String number;
                private List<PictureListBean> pictureList;

                public String getStockId() {
                    return stockId;
                }

                public void setStockId(String stockId) {
                    this.stockId = stockId;
                }

                public SpecABean getSpecA() {
                    return specA;
                }

                public void setSpecA(SpecABean specA) {
                    this.specA = specA;
                }

                public SpecBBean getSpecB() {
                    return specB;
                }

                public void setSpecB(SpecBBean specB) {
                    this.specB = specB;
                }

                public SpecCBean getSpecC() {
                    return specC;
                }

                public void setSpecC(SpecCBean specC) {
                    this.specC = specC;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public int getTotalInventory() {
                    return totalInventory;
                }

                public void setTotalInventory(int totalInventory) {
                    this.totalInventory = totalInventory;
                }

                public int getRemainingStock() {
                    return remainingStock;
                }

                public void setRemainingStock(int remainingStock) {
                    this.remainingStock = remainingStock;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public List<PictureListBean> getPictureList() {
                    return pictureList;
                }

                public void setPictureList(List<PictureListBean> pictureList) {
                    this.pictureList = pictureList;
                }

                public static class SpecABean implements Serializable{
                    /**
                     * specId : 683
                     * key : 尺寸
                     * value : XL
                     */

                    private String specId;
                    private String key;
                    private String value;

                    public String getSpecId() {
                        return specId;
                    }

                    public void setSpecId(String specId) {
                        this.specId = specId;
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }

                public static class SpecBBean implements Serializable{
                    /**
                     * specId : 684
                     * key : 颜色
                     * value : 白色
                     */

                    private String specId;
                    private String key;
                    private String value;

                    public String getSpecId() {
                        return specId;
                    }

                    public void setSpecId(String specId) {
                        this.specId = specId;
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }

                public static class SpecCBean implements Serializable{
                    /**
                     * specId : 685
                     * key : 套餐
                     * value : 套餐一
                     */

                    private String specId;
                    private String key;
                    private String value;

                    public String getSpecId() {
                        return specId;
                    }

                    public void setSpecId(String specId) {
                        this.specId = specId;
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }

                public static class PictureListBean implements Serializable{
                    /**
                     * pictureId : 508
                     * url : http://jymj-mall.oss-cn-beijing.aliyuncs.com/1665629007389_59bbbc50.jpg
                     * type : STOCK_SPEC
                     * mdseId : null
                     * stockId : 506
                     * cardId : null
                     */

                    private String pictureId;
                    private String url;
                    private String type;
                    private Object mdseId;
                    private String stockId;
                    private Object cardId;

                    public String getPictureId() {
                        return pictureId;
                    }

                    public void setPictureId(String pictureId) {
                        this.pictureId = pictureId;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public Object getMdseId() {
                        return mdseId;
                    }

                    public void setMdseId(Object mdseId) {
                        this.mdseId = mdseId;
                    }

                    public String getStockId() {
                        return stockId;
                    }

                    public void setStockId(String stockId) {
                        this.stockId = stockId;
                    }

                    public Object getCardId() {
                        return cardId;
                    }

                    public void setCardId(Object cardId) {
                        this.cardId = cardId;
                    }
                }
            }
        }
    }
}
