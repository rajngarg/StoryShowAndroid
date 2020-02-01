package com.codingeagles.storyshowsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.codingeagles.storyshow.StoryFragment
import com.codingeagles.storyshow.StoryModel
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showStory(view: View) {

        val images = arrayListOf(
            "https://i.picsum.photos/id/426/400/600.jpg",
            "https://i.picsum.photos/id/426/400/600.jpg",
            "https://i.picsum.photos/id/426/400/600.jpg"
        )

        val desc = arrayListOf("Description 1", "Description 2", "Description 3")

        val dates = arrayListOf(
            Date(Calendar.getInstance().timeInMillis - 10000),
            Date(Calendar.getInstance().timeInMillis - 20000),
            Date(Calendar.getInstance().timeInMillis - 30000)
        )

        val userImage =
            "https://i.picsum.photos/id/23/400/600.jpg"

        val storyModel =
            StoryModel(
                images,
                desc,
                "User Name",
                dates,
                userImage
            )

        StoryFragment(storyModel).show(supportFragmentManager, "")
    }
}
