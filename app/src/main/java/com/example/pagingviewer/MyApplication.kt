package com.example.pagingviewer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Hilt uses @HiltAndroidApp to trigger code generation for dependency injection.
//It acts as the base container for the dependency graph that Hilt builds.
@HiltAndroidApp
class MyApplication : Application()