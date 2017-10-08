package com.example.laura.crypthub;

import android.app.Activity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by laura on 10/7/17.
 */

public class RigGroup {

    protected Activity activity;

    protected String type;

    private String base_url;

    public RigGroup(String type, Activity _activity) {
        this.type = type;
        this.activity = _activity;
        this.setBaseUrl();
        this.getGroup();
    }

    public void setBaseUrl() {
        switch (this.type) {
            case "zcash":
                this.base_url = "https://api-zcash.flypool.org/";
                break;
            case "ether":
                this.base_url = "https://api.ethermine.org/";
                break;
        }
    }

    public void getGroup() {
        final TextView testElement = (TextView) this.activity.findViewById(R.id.test);
        HttpRequest request = new HttpRequest(new HttpRequest.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    JSONObject response = new JSONObject(output);
                    testElement.setText(response.getJSONArray("data").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    testElement.setText("error");
                }
            }
        });
//        request.execute("https://api-zcash.flypool.org/miner/t1W9BSTx9Gi4pzekr1woKu1X7WuRcSACLRj/workers");
        request.execute("https://api-zcash.flypool.org/miner/t1Z56yCJgGcaLu9WxPRihf7XqL6CMwHMQhP/workers");
//        request.execute("https://api.ethermine.org/miner/bac6f80CeAf51F5f0058004A328CdD820AeC1042/workers");

    }
}
