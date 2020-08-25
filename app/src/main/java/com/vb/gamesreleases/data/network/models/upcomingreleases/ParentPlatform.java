
package com.vb.gamesreleases.data.network.models.upcomingreleases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentPlatform {

    @SerializedName("platform")
    @Expose
    private Platform__ platform;

    public Platform__ getPlatform() {
        return platform;
    }

    public void setPlatform(Platform__ platform) {
        this.platform = platform;
    }

}
