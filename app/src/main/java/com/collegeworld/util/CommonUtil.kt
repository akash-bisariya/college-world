package com.one.vms.util

import android.content.Context
import android.content.res.Configuration
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.util.Log


/**
 * @author Akash Bisariya
 * Common utility
 */
object CommonUtil {


    /**
     * Method to convert string into camel case string
     *
     * @param inputString string value that need to convert into camel case
     * @return converted camel cased string
     */
    fun toCamelCase(inputString: String?): String {
        var result = ""
        if (inputString == null || inputString.isEmpty()) {
            return result
        }
        val firstChar = inputString[0]
        val firstCharToUpperCase = Character.toUpperCase(firstChar)
        result += firstCharToUpperCase
        for (i in 1 until inputString.length) {
            val currentChar = inputString[i]
            val previousChar = inputString[i - 1]
            if (previousChar == ' ') {
                val currentCharToUpperCase = Character.toUpperCase(currentChar)
                result += currentCharToUpperCase
            } else {
                val currentCharToLowerCase = Character.toLowerCase(currentChar)
                result += currentCharToLowerCase
            }
        }
        return result
    }

    /**
     * Method to check if internet is connected
     *
     * @param context context of calling class
     * @return true if connected to any network else return false
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val wifiMgr = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager;
        if (wifiMgr.isWifiEnabled) { // Wi-Fi adapter is ON
            val wifiInfo = wifiMgr.connectionInfo as WifiInfo
            if (wifiInfo.networkId != -1 && wifiInfo.ssid!!.equals("\"one.com\"",ignoreCase = true)) {
                Log.e("ssid",wifiInfo.ssid+"sdfsd")
                return true; //  connected to an one.com
            }
        }

        return false; // Wi-Fi adapter is OFF
    }


    /**
     * method to check if device is tab
     */
     fun  isTablet(context: Context): Boolean {
        val xlarge = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == 4
        val large = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        return xlarge || large
    }

}