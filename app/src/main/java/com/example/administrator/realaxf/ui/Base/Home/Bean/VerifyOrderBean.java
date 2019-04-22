package com.example.administrator.realaxf.ui.Base.Home.Bean;

import java.util.List;

public class VerifyOrderBean {


    /**
     * result : 0
     * resultcode : 2
     * msg : 成功
     * orderid : 914
     * totalprice : 26
     * data : [{"goodsPrice":"26","goodsId":"72","goodsSmallImg":"http://questionnaire.dzqcedu.com:81/upload/2018-12-25/5c21a95da6617.jpg","goodsName":"好吃的板栗","goodsNumber":"1"}]
     */

    private int result;
    private int resultcode;
    private String msg;
    private String orderid;
    private int totalprice;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodsPrice : 26
         * goodsId : 72
         * goodsSmallImg : http://questionnaire.dzqcedu.com:81/upload/2018-12-25/5c21a95da6617.jpg
         * goodsName : 好吃的板栗
         * goodsNumber : 1
         */

        private String goodsPrice;
        private String goodsId;
        private String goodsSmallImg;
        private String goodsName;
        private String goodsNumber;

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsSmallImg() {
            return goodsSmallImg;
        }

        public void setGoodsSmallImg(String goodsSmallImg) {
            this.goodsSmallImg = goodsSmallImg;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsNumber() {
            return goodsNumber;
        }

        public void setGoodsNumber(String goodsNumber) {
            this.goodsNumber = goodsNumber;
        }
    }
}
