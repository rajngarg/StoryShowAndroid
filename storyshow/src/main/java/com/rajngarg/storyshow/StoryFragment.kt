package com.rajngarg.storyshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_story.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class StoryFragment(val story: StoryModel) :
    DialogFragment(), StoriesProgressView.StoriesListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Translucent_NoTitleBar_Fullscreen
        )
    }

    private val PROGRESS_COUNT = story.images.size

    private var counter = 0

    var pressTime = 0L
    var limit = 500L

    private val onTouchListener = View.OnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pressTime = System.currentTimeMillis()
                storiesProgressView.pause()
                return@OnTouchListener false
            }
            MotionEvent.ACTION_UP -> {
                val now = System.currentTimeMillis()
                setDetailsVisibility(View.VISIBLE)
                storiesProgressView.resume()
                return@OnTouchListener limit < now - pressTime
            }
        }
        false
    }

    private fun setDetailsVisibility(visibility: Int) {
        storyHeader.visibility = visibility
    }

    private fun setDetailsVisibilityWithAnim(visibility: Int) {
        val alphaAnimation: AlphaAnimation = if (visibility == View.GONE) {
            AlphaAnimation(1f, 0f)
        } else {
            AlphaAnimation(0f, 1f)
        }
        alphaAnimation.duration = 300
        storyHeader.animation = alphaAnimation
        storyHeader.visibility = visibility
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        storiesProgressView.setStoriesCount(PROGRESS_COUNT)
        storiesProgressView.setStoryDuration(3000L)

        counter = 0
        storiesProgressView.startStories(counter)

        textUsername.text = story.username
        textTimeAgo.text = Utils.getDurationBetweenDates(
            story.timeAgo[counter],
            Calendar.getInstance().time
        )

        Glide.with(this).load(story.images[counter]).into(image)
        Glide.with(this).load(story.userImage).into(imageUser)
        textDescription.text = story.descriptions[counter]

        setListeners()
    }

    private fun setListeners() {
        storiesProgressView.setStoriesListener(this)
        reverse.setOnClickListener { storiesProgressView.reverse() }
        reverse.setOnTouchListener(onTouchListener)
        reverse.setOnLongClickListener {
            setDetailsVisibilityWithAnim(View.GONE)
            return@setOnLongClickListener true
        }

        imageCross.setOnClickListener {
            dismiss()
        }

        skip.setOnClickListener { storiesProgressView.skip() }
        skip.setOnTouchListener(onTouchListener)
        skip.setOnLongClickListener {
            setDetailsVisibilityWithAnim(View.GONE)
            return@setOnLongClickListener true
        }
    }

    override fun onNext() {
        Glide.with(this).load(story.images[++counter]).into(image)
        textDescription.text = story.descriptions[counter]
        textTimeAgo.text = Utils.getDurationBetweenDates(
            story.timeAgo[counter],
            Calendar.getInstance().time
        )
    }

    override fun onPrev() {
        if (counter - 1 < 0) return
        Glide.with(this).load(story.images[--counter]).into(image)
        textDescription.text = story.descriptions[counter]
        textTimeAgo.text = Utils.getDurationBetweenDates(
            story.timeAgo[counter],
            Calendar.getInstance().time
        )
    }

    override fun onComplete() {
        dismiss()
    }

}
