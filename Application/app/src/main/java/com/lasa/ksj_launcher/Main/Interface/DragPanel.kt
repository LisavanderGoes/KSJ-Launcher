package com.lasa.ksj_launcher.Main.Interface

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.lasa.ksj_launcher.R


class DraggingPanel(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private val AUTO_OPEN_SPEED_LIMIT = 800.0
    private var mDragHelper: ViewDragHelper? = null
    private var mDraggingBorder: Int = 0
    private var mHorizontalRange: Int = 0
    private var mDragView: LinearLayout? = null
    private var mTouchView: LinearLayout? = null
    var isOpen: Boolean = false
        private set

    inner class DragHelperCallback : ViewDragHelper.Callback() {

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            mDraggingBorder = left
        }
        override fun getViewHorizontalDragRange(child: View?): Int {
            return width - mDragView!!.width
        }

        override fun tryCaptureView(view: View, i: Int): Boolean {
            return view === mDragView
        }

        override fun clampViewPositionHorizontal(child: View?, left: Int, dx: Int): Int {

            val leftBound = -(mDragView!!.width + mHorizontalRange)
            val rightBound = width - mDragView!!.width

            return Math.min(Math.max(left, leftBound), rightBound)
        }


        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            val rangeToCheck = 100.toFloat()
            if (mDraggingBorder == 0) {
                isOpen = false
                return
            }
            if (mDraggingBorder.toFloat() == rangeToCheck) {
                isOpen = true
                return
            }
            var settleToOpen = false
            when {
                xvel > AUTO_OPEN_SPEED_LIMIT -> // speed has priority over position
                    settleToOpen = true
                xvel < -AUTO_OPEN_SPEED_LIMIT -> settleToOpen = false
                mDraggingBorder > rangeToCheck / 2 -> settleToOpen = true
                mDraggingBorder < rangeToCheck / 2 -> settleToOpen = false
            }

            val settleDestX = if (settleToOpen) width - mDragView!!.width else -(mDragView!!.width + mHorizontalRange)

            if (mDragHelper!!.settleCapturedViewAt(settleDestX, 0)) {
                ViewCompat.postInvalidateOnAnimation(this@DraggingPanel)
            }
        }
    }

    init {
        isOpen = false
    }

    override fun onFinishInflate() {
        mDragView  = findViewById(R.id.main_layout)
        mTouchView = findViewById(R.id.touch_layout)
        mDragHelper = ViewDragHelper.create(this, 1.0f, DragHelperCallback())
        isOpen = false
        super.onFinishInflate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mHorizontalRange = -100
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val action = MotionEventCompat.getActionMasked(event)
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper!!.cancel()
            return false
        }
        return mDragHelper!!.shouldInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper!!.processTouchEvent(event)
        return true
    }

    override fun computeScroll() { // needed for automatic settling.
        if (mDragHelper!!.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val offSet = width - 200
        val top = top
        val bottom = bottom
        val right = r - offSet
        val left = -offSet + 1

        mDragView!!.layout(left, top, right, bottom)
    }
}