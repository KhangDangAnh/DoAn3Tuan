package com.example.doan_3tuan.ViewModel.BVviewModel
import android.text.Html
import com.example.doan_3tuan.Model.LoadRss.Baiviet
import com.example.doan_3tuan.Model.LoadRss.Rss
import tw.ktrssreader.kotlin.model.channel.RssStandardChannel
import tw.ktrssreader.kotlin.model.item.RssStandardItem

class HomeUiMapper {

    fun map(response: RssStandardChannel): Rss {
        return Rss(
            title = response.title.orEmpty(),
            link = response.link.orEmpty(),
            description = response.description.orEmpty(),
            baiviet = mapArticle(response.items.orEmpty())
        )
    }

    private fun mapArticle(response: List<RssStandardItem>): List<Baiviet> {
        return response.map { baiviet ->
            val string = baiviet.description.orEmpty()
            val substring = "http"
            val position = string.indexOf(substring)
            val result1 = string.substring(position)
            val substring2 = "></a>"
            val position2 = result1.indexOf(substring2) - 1
            val result2= result1.substring(position,position2)
            val position3 = result2.indexOf(substring)
            val result3 = result2.substring(position3)
            Baiviet(
                title = baiviet.title.orEmpty(),
                description = Html.fromHtml(baiviet.description, Html.FROM_HTML_MODE_COMPACT).toString(),
                link = baiviet.link.orEmpty(),
                imageUrl = result3
            )
        }
    }
}
