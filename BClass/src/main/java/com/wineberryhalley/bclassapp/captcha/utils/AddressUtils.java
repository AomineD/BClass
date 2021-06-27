package com.wineberryhalley.bclassapp.captcha.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.wineberryhalley.bclassapp.captcha.Constants;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chensongsong on 2020/8/26.
 */
public class AddressUtils {

    public static String getRegister(Context context, RiskTypeEnum typeEnum) {
        return joinUrl(context, typeEnum, true);
    }

    public static String getValidate(Context context, RiskTypeEnum typeEnum) {
        return joinUrl(context, typeEnum, false);
    }

    private static String joinUrl(Context context, RiskTypeEnum typeEnum, boolean isRegister) {
        String base = getPreferences(context).getString("settings_address_base", Constants.ADDRESS_DEFAULT_BASE);
        String type;
        if (typeEnum == RiskTypeEnum.OTHER) {
            type = getPreferences(context).getString("settings_address_captcha_type", RiskTypeEnum.SLIDE.type);
        } else {
            type = typeEnum.type;
        }
        return base + (isRegister ? "register-" : "validate-") + type;
    }

    private static String subUrl(String address, RiskTypeEnum typeEnum, boolean isRegister) {
        if (typeEnum == RiskTypeEnum.OTHER) {
            return address;
        }
        try {
            URL url = new URL(address);
            String path = url.getPath().substring(0, url.getPath().lastIndexOf("/"));
            String register_base = new URL(url.getProtocol(), url.getHost(), path).toString();
            return register_base + (isRegister ? "/register-" : "/validate-") + typeEnum.type;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return address;
    }

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
