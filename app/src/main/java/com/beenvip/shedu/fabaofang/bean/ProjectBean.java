package com.beenvip.shedu.fabaofang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.beenvip.shedu.base.HttpBaseResponseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/4/27.
 * 497239511@qq.com
 */

public class ProjectBean extends HttpBaseResponseBean implements Parcelable {

    /**
     * result : true
     * data : [{"pid":"9","pName":"测试","pType":"11","pDevelop":"比万科技","pDesign":"比万科技","pReconnaissance":"比万科技",
     * "pConstruction":"比万科技","pSupervision":"比万科技","pDescript":"测试测试测试","pEndtime":"2017-4-30","pCity":"312",
     * "pCityname":"","pAddress":"桐梓坡西路","pContact":"周航","pPhone":"15576310006","pCost":"2000000000","posttime":"1493279630",
     * "uid":"3","lat":"","lng":"","pFloor":"100000","pCovered":"10000000","picurl":"","pTypename":""}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * pid : 9
         * pName : 测试
         * pType : 11
         * pDevelop : 比万科技
         * pDesign : 比万科技
         * pReconnaissance : 比万科技
         * pConstruction : 比万科技
         * pSupervision : 比万科技
         * pDescript : 测试测试测试
         * pEndtime : 2017-4-30
         * pCity : 312
         * pCityname :
         * pAddress : 桐梓坡西路
         * pContact : 周航
         * pPhone : 15576310006
         * pCost : 2000000000
         * posttime : 1493279630
         * uid : 3
         * lat :
         * lng :
         * pFloor : 100000
         * pCovered : 10000000
         * picurl :
         * pTypename :
         */

        private String pid;
        private String pName;
        private String pType;
        private String pDevelop;
        private String pDesign;
        private String pReconnaissance;
        private String pConstruction;
        private String pSupervision;
        private String pDescript;
        private String pEndtime;
        private String pCity;
        private String pCityname;
        private String pAddress;
        private String pContact;
        private String pPhone;
        private String pCost;
        private String posttime;
        private String uid;
        private String lat;
        private String lng;
        private String pFloor;
        private String pCovered;
        private String picurl;
        private String pTypename;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }

        public String getPType() {
            return pType;
        }

        public void setPType(String pType) {
            this.pType = pType;
        }

        public String getPDevelop() {
            return pDevelop;
        }

        public void setPDevelop(String pDevelop) {
            this.pDevelop = pDevelop;
        }

        public String getPDesign() {
            return pDesign;
        }

        public void setPDesign(String pDesign) {
            this.pDesign = pDesign;
        }

        public String getPReconnaissance() {
            return pReconnaissance;
        }

        public void setPReconnaissance(String pReconnaissance) {
            this.pReconnaissance = pReconnaissance;
        }

        public String getPConstruction() {
            return pConstruction;
        }

        public void setPConstruction(String pConstruction) {
            this.pConstruction = pConstruction;
        }

        public String getPSupervision() {
            return pSupervision;
        }

        public void setPSupervision(String pSupervision) {
            this.pSupervision = pSupervision;
        }

        public String getPDescript() {
            return pDescript;
        }

        public void setPDescript(String pDescript) {
            this.pDescript = pDescript;
        }

        public String getPEndtime() {
            return pEndtime;
        }

        public void setPEndtime(String pEndtime) {
            this.pEndtime = pEndtime;
        }

        public String getPCity() {
            return pCity;
        }

        public void setPCity(String pCity) {
            this.pCity = pCity;
        }

        public String getPCityname() {
            return pCityname;
        }

        public void setPCityname(String pCityname) {
            this.pCityname = pCityname;
        }

        public String getPAddress() {
            return pAddress;
        }

        public void setPAddress(String pAddress) {
            this.pAddress = pAddress;
        }

        public String getPContact() {
            return pContact;
        }

        public void setPContact(String pContact) {
            this.pContact = pContact;
        }

        public String getPPhone() {
            return pPhone;
        }

        public void setPPhone(String pPhone) {
            this.pPhone = pPhone;
        }

        public String getPCost() {
            return pCost;
        }

        public void setPCost(String pCost) {
            this.pCost = pCost;
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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getPFloor() {
            return pFloor;
        }

        public void setPFloor(String pFloor) {
            this.pFloor = pFloor;
        }

        public String getPCovered() {
            return pCovered;
        }

        public void setPCovered(String pCovered) {
            this.pCovered = pCovered;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getPTypename() {
            return pTypename;
        }

        public void setPTypename(String pTypename) {
            this.pTypename = pTypename;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.pid);
            dest.writeString(this.pName);
            dest.writeString(this.pType);
            dest.writeString(this.pDevelop);
            dest.writeString(this.pDesign);
            dest.writeString(this.pReconnaissance);
            dest.writeString(this.pConstruction);
            dest.writeString(this.pSupervision);
            dest.writeString(this.pDescript);
            dest.writeString(this.pEndtime);
            dest.writeString(this.pCity);
            dest.writeString(this.pCityname);
            dest.writeString(this.pAddress);
            dest.writeString(this.pContact);
            dest.writeString(this.pPhone);
            dest.writeString(this.pCost);
            dest.writeString(this.posttime);
            dest.writeString(this.uid);
            dest.writeString(this.lat);
            dest.writeString(this.lng);
            dest.writeString(this.pFloor);
            dest.writeString(this.pCovered);
            dest.writeString(this.picurl);
            dest.writeString(this.pTypename);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.pid = in.readString();
            this.pName = in.readString();
            this.pType = in.readString();
            this.pDevelop = in.readString();
            this.pDesign = in.readString();
            this.pReconnaissance = in.readString();
            this.pConstruction = in.readString();
            this.pSupervision = in.readString();
            this.pDescript = in.readString();
            this.pEndtime = in.readString();
            this.pCity = in.readString();
            this.pCityname = in.readString();
            this.pAddress = in.readString();
            this.pContact = in.readString();
            this.pPhone = in.readString();
            this.pCost = in.readString();
            this.posttime = in.readString();
            this.uid = in.readString();
            this.lat = in.readString();
            this.lng = in.readString();
            this.pFloor = in.readString();
            this.pCovered = in.readString();
            this.picurl = in.readString();
            this.pTypename = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
        dest.writeList(this.data);
    }

    public ProjectBean() {
    }

    protected ProjectBean(Parcel in) {
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProjectBean> CREATOR = new Parcelable.Creator<ProjectBean>() {
        @Override
        public ProjectBean createFromParcel(Parcel source) {
            return new ProjectBean(source);
        }

        @Override
        public ProjectBean[] newArray(int size) {
            return new ProjectBean[size];
        }
    };
}
