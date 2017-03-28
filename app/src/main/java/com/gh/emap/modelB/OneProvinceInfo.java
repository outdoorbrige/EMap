package com.gh.emap.modelB;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class OneProvinceInfo implements Serializable {
    private OneCityInfo mProvince;
    private ArrayList<OneCityInfo> mCities;

    public OneProvinceInfo() {

    }

    public OneCityInfo getProvince() {
        return mProvince;
    }

    public void setProvince(OneCityInfo province) {
        this.mProvince = province;
    }

    public ArrayList<OneCityInfo> getCities() {
        return mCities;
    }

    public void setCities(ArrayList<OneCityInfo> cities) {
        this.mCities = cities;
    }
}
