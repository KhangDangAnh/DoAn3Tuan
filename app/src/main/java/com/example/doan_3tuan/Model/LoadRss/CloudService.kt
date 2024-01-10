package com.example.doan_3tuan.Model.LoadRss

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import tw.ktrssreader.kotlin.model.channel.RssStandardChannel
import tw.ktrssreader.kotlin.parser.RssStandardParser
class CloudService(private val httpClient: HttpClient) {
    suspend fun getArticles(
        urlString: String = "https://nld.com.vn/rss/home.rss"
    ): RssStandardChannel {
        val response = httpClient.get(urlString).bodyAsText()
        return RssStandardParser().parse(response)
    }
}