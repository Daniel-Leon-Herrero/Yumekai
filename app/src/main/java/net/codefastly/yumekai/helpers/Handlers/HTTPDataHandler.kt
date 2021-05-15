package net.codefastly.yumekai.helpers.Handlers

import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class HTTPDataHandler {
    var stream: String = ""

    inner class HTTPReference {

        fun getHTTPDataHandler( url: String ): String? {
            try {
                val connection: HttpURLConnection = URL(url.trim()).openConnection() as HttpURLConnection
                if (connection!!.responseCode == HttpURLConnection.HTTP_OK) {
                    var sb: StringBuilder = StringBuilder()
                    connection.inputStream.bufferedReader().use { reader ->
                        sb.append(reader.readLine())
                        stream = sb.toString()
                    }
                    connection.disconnect()
                }
            }catch (e: Exception){
                return ""
            }
            return stream
        }

    }



}