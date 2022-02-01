package com.javiermtz.marvelapp.data.repository

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.javiermtz.marvelapp.BuildConfig
import com.javiermtz.marvelapp.util.Constans
import java.security.MessageDigest
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.SimpleTimeZone
import javax.inject.Inject

class MD5 @Inject constructor() {

  val currentTimestamp = System.currentTimeMillis()

  fun getMD5(): String {
    val md5 = currentTimestamp.toString()+BuildConfig.PRIVATE_API_KEY_MARVEL+BuildConfig.PUBLIC_API_KEY_MARVEL
    return md5.toMD5()
  }
}

fun String.toMD5(): String {
  val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
  return bytes.toHex()
}

fun ByteArray.toHex(): String {
  return joinToString("") { "%02x".format(it) }
}
