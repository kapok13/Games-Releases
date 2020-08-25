
package com.vb.gamesreleases.data.network.models.upcomingreleases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("store")
    @Expose
    private Store_ store;

    public Store_ getStore() {
        return store;
    }

    public void setStore(Store_ store) {
        this.store = store;
    }

}
