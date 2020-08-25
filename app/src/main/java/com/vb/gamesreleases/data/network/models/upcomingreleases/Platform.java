
package com.vb.gamesreleases.data.network.models.upcomingreleases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Platform {

    @SerializedName("platform")
    @Expose
    private Platform_ platform;

    public Platform_ getPlatform() {
        return platform;
    }

    public void setPlatform(Platform_ platform) {
        this.platform = platform;
    }

}
