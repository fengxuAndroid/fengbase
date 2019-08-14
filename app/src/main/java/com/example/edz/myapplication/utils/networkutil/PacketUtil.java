package com.example.edz.myapplication.utils.networkutil;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.edz.myapplication.utils.CharsetUtil;
import com.example.edz.myapplication.utils.SpUtil;
import com.example.edz.myapplication.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 *  * @author 冯旭 2019-07-19
 *  * 邮箱：876111689@qq.com
 *  * CSDN: https://blog.csdn.net/weixin_40611659
 *  * github：https://github.com/FX19970117
 * 拼接请求报文的工具类

 */
public class PacketUtil  {

    public static String getRequestPacket(Context mContext, Object data) {
        String result = "";
        JSONObject rows = new JSONObject();
        try {

            // data为空时，添加空的JOSNObject
            if (data instanceof String) {
                rows.put("data", data == null ? new JSONObject() : new JSONObject((String) data));
            } else if (data instanceof Map) {
                rows.put("data", data == null ? new JSONObject() : new JSONObject((Map) data));
            } else {
                JSONObject jsonObject = (JSONObject) data;
                rows.put("data", data == null ? new JSONObject() : jsonObject);
            }

//            result = rows.toString();
            result = CharsetUtil.toUTF_8(rows.toString());
            //  LogUtil.e("请求报文", result);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;

//    public static String getRequestPacket(Context mContext, String packetNo) {
////            String user_id = "";
//            String result = "";
//            JSONObject rows = new JSONObject();
//            try {
////                if (!StringUtils.isEmpty(SpUtil.init(mContext).readString(ConstantApi.LOGIN_ID))) {//用户ID
////                    user_id = SpUtil.init(mContext).readString(ConstantApi.LOGIN_ID);
////                }
////                String date = System.currentTimeMillis() + "";/*当前系统时间*/
//                // 请求代码 + 用户ID + 当前时间 + key，然后用md5加密 = Token
////                String token = packetNo + user_id + date + ConstantApi.KEY;
//                // LogUtil.e("Token", "=====加密前=====" + token);     `
////                token = MD5Util.newInstance().getkeyBeanofStr(token);
//                // LogUtil.e("Token", "=====加密后=====" + token);
////                rows.put("token", "");
////                TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
////                rows.put("deviceId", "");
////                rows.put("roles", "");
//                // 报文编号
//                rows.put("action", packetNo);
//                // 请求时间
////                rows.put("date", date);
//                // 用户ID
////                rows.put("user_id", user_id);
//
//                // data为空时，添加空的JOSNObject
////                if (data instanceof String) {
////                    rows.put("data", data == null ? new JSONObject() : new JSONObject((String) data));
////                } else if (data instanceof Map) {
////                    rows.put("data", data == null ? new JSONObject() : new JSONObject((Map) data));
////                } else {
////                    JSONObject jsonObject = (JSONObject) data;
////                    rows.put("data", data == null ? new JSONObject() : jsonObject);
////                }
//
//                result = CharsetUtil.toUTF_8(rows.toString());
//                //  LogUtil.e("请求报文", result);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            return result;
    }


}
