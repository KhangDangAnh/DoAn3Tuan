package com.example.doan_3tuan.Model

sealed class NavRoot(val root: String)
{
    object chitiet : NavRoot("chitiet")
    object trangchu : NavRoot("trangchu")

    object timkiem : NavRoot("timkiem")
    object xuhuong : NavRoot("xuhuong")
    object tienich : NavRoot("tienich")
    object video:NavRoot("video")
    object xuhuongquantam:NavRoot("xuhuongquantam")
    object xuhuongyeuthich:NavRoot("xuhuongyeuthich")

    object luunews : NavRoot("luunews")
}