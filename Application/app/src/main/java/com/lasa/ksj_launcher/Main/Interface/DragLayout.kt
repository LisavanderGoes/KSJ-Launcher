package com.lasa.ksj_launcher.Main.Interface

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v4.widget.ViewDragHelper
import android.support.v4.widget.ViewDragHelper.create
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout


class DragLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private var mDragHelper: ViewDragHelper = create(this, 1.0f, DragHelperCallback())
    internal lateinit var mDragView: View

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val action = MotionEventCompat.getActionMasked(event)
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel()
            return false
        }
        return mDragHelper.shouldInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }

    private class DragHelperCallback : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionHorizontal(child: View?, left: Int, dx: Int): Int {
            Log.d("DragLayout", "clampViewPositionHorizontal $left,$dx")

            val leftBound = 50
            val rightBound = 50

            return Math.min(Math.max(left, leftBound), rightBound)
        }
    }
}

