package com.example.remainder_for_android.view_model

import com.example.remainder_for_android.model.Tag

class TagVM(val title: String, val color: String) {
    constructor(tagM: Tag):
            this(tagM.title, tagM.color)
}