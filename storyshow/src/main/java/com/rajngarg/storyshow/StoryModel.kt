package com.rajngarg.storyshow

import java.util.*
import kotlin.collections.ArrayList

data class StoryModel(
    var images: ArrayList<String>,
    var descriptions: ArrayList<String>,
    var username: String,
    var timeAgo: ArrayList<Date>,
    var userImage: String
)