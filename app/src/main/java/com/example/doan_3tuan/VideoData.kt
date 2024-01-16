package com.example.doan_3tuan

import android.net.Uri

object VideoData {
    val videos = listOf<Video>(
        Video(
            id = 1,
            video = "cachcony.mp4",
            userImage = "https://yt3.googleusercontent.com/hHr3K3eN8-vaBSbYIYr4_ghHu7UUsH0CTLCgHEGfTSMdtuFFvPKejXrgfL3lg2mKeUgTBF0nREA=s176-c-k-c0x00ffffff-no-rj",
            userName = "MinhQuan",
            isLiked = false,
            likesCount = 999999,
            commentsCount = 20000,
            comment = "Cách để có người yêu nhanh nhất",
            music = "Nhạc alala"
        ),
        Video(
            id = 2,
            video = "bocphotcongai.mp4",
            userImage = "https://yt3.googleusercontent.com/J7lbeCa2OTqHvZjhOFfjkQfR2938L3Hny149FhjsMnzFGzU89xoAIotEqOHcYssAe0Ks-hl7bi0=s176-c-k-c0x00ffffff-no-rj",
            userName = "Tin 3P",
            isLiked = true,
            likesCount = 16598,
            commentsCount = 1102,
            comment = "bốc phốt cô con gái của tôi",
            music = "Nhạc tyoti"
        ),
        Video(
            id = 3,
            video = "Food.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/3.png",
            userName = "Christian Juned",
            isLiked = false,
            likesCount = 2314,
            comment = "Es krim dingin sedapp",
            commentsCount = 200,
            music = "Nhạc nunino"
        ),
        Video(
            id = 4,
            video = "cogiaoxinh.mp4",
            userImage = "https://yt3.googleusercontent.com/IVgG4cYfvsz2SgS-Bn3Xn8i3ntH89HvLpju0Fj3pLUjZkklkPskrrLD9A-Qbjsk7wrV1PdVsHbM=s176-c-k-c0x00ffffff-no-rj",
            userName = "CoGiaoThao",
            isLiked = false,
            likesCount = 1890,
            comment = "Cô giáo cấp 3 ",
            commentsCount = 232,
            music = "Nhạc lolol"
        ),
        Video(
            id = 5,
            video = "icecream.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/7.png",
            userName = "Tin Vui",
            isLiked = true,
            likesCount = 16595,
            comment = "Khoảng khắc tuyệt vời",
            commentsCount = 659,
            music = "Nhạc oách xà lách"
        ),
        Video(
            id = 6,
            video = "baothu.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/6.png",
            userName = "David Dulkader",
            isLiked = true,
            likesCount = 65988,
            comment = "Báo thủ À? ",
            commentsCount = 5987,
            music = "Nhạc ba be me"
        ),
        Video(
            id = 7,
            video = "lake.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/2.png",
            userName = "David Dulkader",
            isLiked = false,
            likesCount = 16598,
            comment = "dòng nước tuyệt vời",
            commentsCount = 356,
            music = "Nhạc lo li lu"
        ),
        Video(
            id = 8,
            video = "chongluong10cu.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/3.png",
            userName = "David Dulkader",
            isLiked = true,
            likesCount = 795625,
            comment = "Khi chồng lương 100 củ và bàn 10 củ",
            commentsCount = 3660,
            music = "Nhạc a go la"
        ),
        Video(
            id = 9,
            video = "thaotungtamly.mp4",
            userImage = "https://generated.photos/vue-static/home/hero/2.png",
            userName = "Tin 10h",
            isLiked = false,
            likesCount = 65988,
            comment = "Thao túng tâm lý cực mạnh",
            commentsCount = 6598,
            music = "Nhạc hihi haha"
        ),

    )
}

data class Video(
    val id: Int,
    private val video: String,
    val userImage: String,
    val userName: String,
    var isLiked: Boolean = false,
    var likesCount: Int,
    var comment: String,
    var commentsCount: Int,
    val music:String
) {


    fun getVideoUrl(): Uri {

        return Uri.parse("asset:///${video}")
    }

}