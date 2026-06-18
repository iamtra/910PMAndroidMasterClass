package kh.com.pheaktra.developer.basic.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()

/**
 * Related to multiple module, you need to check about
 * 1. module name
 * 2. include in setting gradle
 * 3. implementation in build.gradle (other module)
 */