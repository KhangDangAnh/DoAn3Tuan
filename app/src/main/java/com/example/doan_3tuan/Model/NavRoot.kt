package com.example.doan_3tuan.Model

sealed class NavRoot(val root: String)
{
    object chitiet : NavRoot("chitiet")
    object trangchu : NavRoot("trangchu")
}