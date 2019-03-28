package com.otssso.samimchala.flick.utils

import com.otssso.samimchala.flick.FlickApplication
import java.io.IOException

object Utils {

    //there is only one application, its appropriate to be using the context this way in my opinion, as viewModels should be agnostic to android
    internal var context = FlickApplication.instance

    fun loadJSONFromAsset(jsonFileName: String): String {
        var json = ""
        try {
            val `is` = context.assets.open(jsonFileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }
}