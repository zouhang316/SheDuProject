package com.beenvip.shedu.fabaofang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.beenvip.shedu.base.HttpBaseResponseBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/24.
 * 497239511@qq.com
 */

public class BanzuListBean extends HttpBaseResponseBean implements Parcelable {

    /**
     * result : true
     * data : [{"bid":"2","bName":"周冶家班组","bType":"0","bTypename":"钢筋班","bCost":"","bNumer":"60","bContact":"周冶家","bPhone":"13874955797","bCity":"0","bCityname":"长沙","posttime":"0","uid":"1","pid":"0","bworktime":"0","bworkdesc":"","picurl":""},{"bid":"3","bName":"徐国强班组","bType":"0","bTypename":"混凝土班","bCost":"","bNumer":"29","bContact":"徐国强","bPhone":"13808478803","bCity":"0","bCityname":"长沙","posttime":"0","uid":"0","pid":"0","bworktime":"0","bworkdesc":"","picurl":""},{"bid":"4","bName":"吴运军班组","bType":"0","bTypename":"木工班","bCost":"","bNumer":"34","bContact":"吴运军","bPhone":"13786176651","bCity":"0","bCityname":"长沙","posttime":"0","uid":"4","pid":"0","bworktime":"0","bworkdesc":"","picurl":""},{"bid":"5","bName":"肖明亮班组","bType":"0","bTypename":"木工班","bCost":"","bNumer":"36","bContact":"肖明亮","bPhone":"13517319697","bCity":"0","bCityname":"长沙","posttime":"0","uid":"0","pid":"0","bworktime":"0","bworkdesc":"","picurl":""},{"bid":"6","bName":"殷军班组","bType":"0","bTypename":"整体爬架","bCost":"","bNumer":"16","bContact":"殷军","bPhone":"15974246063","bCity":"0","bCityname":"长沙","posttime":"0","uid":"0","pid":"0","bworktime":"0","bworkdesc":"","picurl":""},{"bid":"7","bName":"罗江湘班组","bType":"0","bTypename":"抹灰班","bCost":"","bNumer":"28","bContact":"罗江湘","bPhone":"13055176729","bCity":"0","bCityname":"长沙","posttime":"0","uid":"0","pid":"0","bworktime":"0","bworkdesc":"","picurl":""},{"bid":"8","bName":"刘乾程班组","bType":"0","bTypename":"抹灰班","bCost":"","bNumer":"53","bContact":"刘乾程","bPhone":"13808424311","bCity":"0","bCityname":"长沙","posttime":"0","uid":"0","pid":"0","bworktime":"0","bworkdesc":"","picurl":""},{"bid":"9","bName":"周汉军班组","bType":"0","bTypename":"水电安装","bCost":"","bNumer":"29","bContact":"周汉军","bPhone":"13973179549","bCity":"0","bCityname":"长沙","posttime":"0","uid":"0","pid":"0","bworktime":"0","bworkdesc":"","picurl":""}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        @Override
        public String toString() {
            return "DataBean{" +
                    "bid='" + bid + '\'' +
                    ", bName='" + bName + '\'' +
                    ", bType='" + bType + '\'' +
                    ", bTypename='" + bTypename + '\'' +
                    ", bCost='" + bCost + '\'' +
                    ", bNumer='" + bNumer + '\'' +
                    ", bContact='" + bContact + '\'' +
                    ", bPhone='" + bPhone + '\'' +
                    ", bCity='" + bCity + '\'' +
                    ", bCityname='" + bCityname + '\'' +
                    ", posttime='" + posttime + '\'' +
                    ", uid='" + uid + '\'' +
                    ", pid='" + pid + '\'' +
                    ", bworktime='" + bworktime + '\'' +
                    ", bworkdesc='" + bworkdesc + '\'' +
                    ", picurl='" + picurl + '\'' +
                    '}';
        }

        /**
         * bid : 2
         * bName : 周冶家班组
         * bType : 0
         * bTypename : 钢筋班
         * bCost :
         * bNumer : 60
         * bContact : 周冶家
         * bPhone : 13874955797
         * bCity : 0
         * bCityname : 长沙
         * posttime : 0
         * uid : 1
         * pid : 0
         * bworktime : 0
         * bworkdesc :
         * picurl :
         */

        private String bid;
        private String bName;
        private String bType;
        private String bTypename;
        private String bCost;
        private String bNumer;
        private String bContact;
        private String bPhone;
        private String bCity;
        private String bCityname;
        private String posttime;
        private String uid;
        private String pid;
        private String bworktime;
        private String bworkdesc;
        private String picurl;

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getBName() {
            return bName;
        }

        public void setBName(String bName) {
            this.bName = bName;
        }

        public String getBType() {
            return bType;
        }

        public void setBType(String bType) {
            this.bType = bType;
        }

        public String getBTypename() {
            return bTypename;
        }

        public void setBTypename(String bTypename) {
            this.bTypename = bTypename;
        }

        public String getBCost() {
            return bCost;
        }

        public void setBCost(String bCost) {
            this.bCost = bCost;
        }

        public String getBNumer() {
            return bNumer;
        }

        public void setBNumer(String bNumer) {
            this.bNumer = bNumer;
        }

        public String getBContact() {
            return bContact;
        }

        public void setBContact(String bContact) {
            this.bContact = bContact;
        }

        public String getBPhone() {
            return bPhone;
        }

        public void setBPhone(String bPhone) {
            this.bPhone = bPhone;
        }

        public String getBCity() {
            return bCity;
        }

        public void setBCity(String bCity) {
            this.bCity = bCity;
        }

        public String getBCityname() {
            return bCityname;
        }

        public void setBCityname(String bCityname) {
            this.bCityname = bCityname;
        }

        public String getPosttime() {
            return posttime;
        }

        public void setPosttime(String posttime) {
            this.posttime = posttime;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getBworktime() {
            return bworktime;
        }

        public void setBworktime(String bworktime) {
            this.bworktime = bworktime;
        }

        public String getBworkdesc() {
            return bworkdesc;
        }

        public void setBworkdesc(String bworkdesc) {
            this.bworkdesc = bworkdesc;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.bid);
            dest.writeString(this.bName);
            dest.writeString(this.bType);
            dest.writeString(this.bTypename);
            dest.writeString(this.bCost);
            dest.writeString(this.bNumer);
            dest.writeString(this.bContact);
            dest.writeString(this.bPhone);
            dest.writeString(this.bCity);
            dest.writeString(this.bCityname);
            dest.writeString(this.posttime);
            dest.writeString(this.uid);
            dest.writeString(this.pid);
            dest.writeString(this.bworktime);
            dest.writeString(this.bworkdesc);
            dest.writeString(this.picurl);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.bid = in.readString();
            this.bName = in.readString();
            this.bType = in.readString();
            this.bTypename = in.readString();
            this.bCost = in.readString();
            this.bNumer = in.readString();
            this.bContact = in.readString();
            this.bPhone = in.readString();
            this.bCity = in.readString();
            this.bCityname = in.readString();
            this.posttime = in.readString();
            this.uid = in.readString();
            this.pid = in.readString();
            this.bworktime = in.readString();
            this.bworkdesc = in.readString();
            this.picurl = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public BanzuListBean() {
    }

    protected BanzuListBean(Parcel in) {
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<BanzuListBean> CREATOR = new Parcelable.Creator<BanzuListBean>() {
        @Override
        public BanzuListBean createFromParcel(Parcel source) {
            return new BanzuListBean(source);
        }

        @Override
        public BanzuListBean[] newArray(int size) {
            return new BanzuListBean[size];
        }
    };

    @Override
    public String toString() {
        return "BanzuListBean{" +
                "data=" + data +
                '}';
    }
}
