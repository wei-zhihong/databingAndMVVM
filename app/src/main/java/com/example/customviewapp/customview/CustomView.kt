package com.example.customviewapp.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customviewapp.R

class CustomView : View {
    private var defaultSize = 100

    private val paint: Paint = Paint().apply {
        color = Color.GREEN
    }

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        defaultSize = array.getDimensionPixelSize(R.styleable.CustomView_defaultSize, 100)
        array.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = getRectSize(defaultSize, widthMeasureSpec)
        var height = getRectSize(defaultSize, heightMeasureSpec)

        if (width < height) {
            height = width
        } else {
            width = height
        }
        setMeasuredDimension(width, height)
    }

    private fun getRectSize(defaultSize: Int, measureSpec: Int): Int {
        var rectSize = defaultSize

        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.UNSPECIFIED -> rectSize = defaultSize
            MeasureSpec.AT_MOST -> rectSize = defaultSize
            MeasureSpec.EXACTLY -> rectSize = defaultSize
        }
        return rectSize
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val r = measuredHeight / 2
        val centerX = left + r
        val centerY = top + r

        canvas?.drawCircle(centerX.toFloat(), centerY.toFloat(), r.toFloat(), paint)
    }
}