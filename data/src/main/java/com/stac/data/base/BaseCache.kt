package com.stac.data.base

import android.app.Application
import com.stac.data.database.RoomDatabase

open class BaseCache(application: Application) {
    protected val database = RoomDatabase.getInstance(application)!!
}
