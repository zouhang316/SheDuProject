package com.beenvip.shedu.user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.beenvip.shedu.base.HttpBaseResponseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/4/17.
 * 497239511@qq.com
 */

public class FenleiBean extends HttpBaseResponseBean implements Parcelable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * name : 土石方/爆破
         * sub : [{"fid":"11","name":"运输队/弃置"},{"fid":"108","name":"土石方"},{"fid":"141","name":"爆破"}]
         */

        private String name;
        private List<SubBean> sub;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SubBean> getSub() {
            return sub;
        }

        public void setSub(List<SubBean> sub) {
            this.sub = sub;
        }

        public static class SubBean implements Parcelable {
            /**
             * fid : 11
             * name : 运输队/弃置
             */

            private String fid;
            private String name;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "SubBean{" +
                        "fid='" + fid + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.fid);
                dest.writeString(this.name);
            }

            public SubBean() {
            }

            protected SubBean(Parcel in) {
                this.fid = in.readString();
                this.name = in.readString();
            }

            public static final Creator<SubBean> CREATOR = new Creator<SubBean>() {
                @Override
                public SubBean createFromParcel(Parcel source) {
                    return new SubBean(source);
                }

                @Override
                public SubBean[] newArray(int size) {
                    return new SubBean[size];
                }
            };
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", sub=" + sub +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeList(this.sub);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.name = in.readString();
            this.sub = new ArrayList<SubBean>();
            in.readList(this.sub, SubBean.class.getClassLoader());
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
    public String toString() {
        return "FenleiBean{" +
                "data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public FenleiBean() {
    }

    protected FenleiBean(Parcel in) {
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FenleiBean> CREATOR = new Parcelable.Creator<FenleiBean>() {
        @Override
        public FenleiBean createFromParcel(Parcel source) {
            return new FenleiBean(source);
        }

        @Override
        public FenleiBean[] newArray(int size) {
            return new FenleiBean[size];
        }
    };
}
