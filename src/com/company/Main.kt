package com.company

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import org.json.simple.JSONObject
import org.json.simple.JSONValue
import java.io.IOException
import java.io.InputStreamReader
import java.net.InetSocketAddress

object Main {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val server = HttpServer.create(InetSocketAddress(5000), 0)
        server.createContext("/", CustomHandler())
        server.start()
    }
}

internal class CustomHandler : HttpHandler {
    override fun handle(exchange: HttpExchange) {
        val obj = JSONValue.parse(InputStreamReader(exchange.requestBody))
        val jsonObject = obj as JSONObject
        val payload = jsonObject["payload_fields"] as JSONObject
        println(payload.toJSONString())
    }
}