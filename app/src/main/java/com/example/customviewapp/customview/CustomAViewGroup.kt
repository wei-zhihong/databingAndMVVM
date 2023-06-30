package com.example.customviewapp.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class CustomAViewGroup: ViewGroup {

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var currentHeight = t
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val height = childView.measuredHeight
            val width = childView.measuredWidth
            childView.layout(l, currentHeight, l + width, currentHeight + height)
            currentHeight += height
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (childCount == 0) {
            setMeasuredDimension(0, 0)
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxChildWidth(), getTotalHeight())
            } else if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, getTotalHeight())
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxChildWidth(), heightSize)
            }
        }
    }

    private fun getTotalHeight(): Int {
        var height = 0
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            height += childView.measuredHeight
        }
        return height
    }

    private fun getMaxChildWidth(): Int {
        val childCount = childCount
        var width = 0
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView.measuredWidth > width) {
                width = childView.measuredWidth
            }
        }
        return width
    }
}