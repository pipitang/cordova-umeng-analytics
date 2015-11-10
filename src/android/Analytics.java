package org.apache.cordova.umeng;

import android.util.Log;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Analytics extends CordovaPlugin {

    private static final String TAG = "cordova-umeng-analytics";

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        MobclickAgent.onPause(cordova.getActivity());
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        MobclickAgent.onResume(cordova.getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callback) throws JSONException {
        Log.d(TAG, action + " is called.");
        if (action.equals("config")) init(args.getJSONObject(0), callback);
        else if (action.equals("startPage")) startPage(args.getString(0), callback);
        else if (action.equals("endPage")) endPage(args.getString(0), callback);
        else if (action.equals("setDebug")) enableDebug(args.getBoolean(0), callback);
        else if (action.equals("logEvent")) logEvent(args.getJSONObject(0), callback);
        else return false;
        return true;
    }

    private void logEvent(JSONObject params, CallbackContext callback) throws JSONException {
        int number = params.has("num") ?  params.getInt("num") : 0;
        Map<String, String> attributes = null;
        if (params.has("attributes")){
            JSONObject jsonObj = params.getJSONObject("attributes");
            Iterator<String> keys = jsonObj.keys();
            while(keys.hasNext()){
                String key = keys.next();
                if (attributes == null) attributes = new HashMap<String, String>();
                attributes.put(key, jsonObj.getString(key));
            }
        }
        MobclickAgent.onEventValue(cordova.getActivity(), params.getString("eventId"), attributes, number);
        callback.success();
    }

    private void enableDebug(boolean enable, CallbackContext callback) {
        MobclickAgent.setDebugMode(enable);
        callback.success();
    }


    private void endPage(String page, CallbackContext callback) {
        MobclickAgent.onPageEnd(page);
        callback.success();
    }

    private void startPage(String page, CallbackContext callback) {
        MobclickAgent.onPageStart(page);
        callback.success();
    }


    private void init(JSONObject jsonObject, CallbackContext callback) throws JSONException{
        String key = jsonObject.getString("appkey");
        if (key != null && !key.isEmpty())AnalyticsConfig.setAppkey(cordova.getActivity(), key);
        String channel = jsonObject.getString("channel");
        if (channel != null && !channel.isEmpty()) AnalyticsConfig.setChannel(channel);
        callback.success();
    }

    protected JSONObject buildError(int code, String msg) throws JSONException {
        JSONObject result = new JSONObject();
        result.put("errorCode", code);
        result.put("errorMessage", msg);
        return result;
    }
}
