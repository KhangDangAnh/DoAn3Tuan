package com.example.doan_3tuan.Model.LoadRss

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import tw.ktrssreader.kotlin.model.channel.RssStandardChannel
import tw.ktrssreader.kotlin.parser.RssStandardParser
import java.net.URL

class CloudService() {
    val httpClient = HttpClient(CIO)
    var url : String = "https://nld.com.vn/rss/home.rss"
    init {
        this.url = url
    }
    suspend fun getBaiviet(
        urlString: String = url
    ): RssStandardChannel
    {
        val response = httpClient.get(urlString).bodyAsText()
        return RssStandardParser().parse(response)
    }
}