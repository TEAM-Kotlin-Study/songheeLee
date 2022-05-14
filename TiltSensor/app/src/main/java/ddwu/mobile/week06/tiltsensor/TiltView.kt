package ddwu.mobile.week06.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        greenPaint.color = Color.GREEN
        blackPaint.style = Paint.Style.STROKE
    }

    //뷰의 크기가 변경될 때 호출됨
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f
        cY = h / 2f
    }

    fun onSensorEvent(event: SensorEvent){
        yCoord = event.values[0] * 20;
        xCoord = event.values[1] * 20;

        invalidate()
    }

    //onDraw 메서드 오버라이드해 Canvas 객체 받기
    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(cX, cY, 100f, blackPaint)    //바깥 원
        canvas?.drawCircle(xCoord + cX, yCoord + cY, 100f, greenPaint)    //녹색 원

        //가운데 십자가
        canvas?.drawLine(cX - 20, cY, cX + 20, cY, blackPaint)
        canvas?.drawLine(cX, cY - 20, cX, cY + 20, blackPaint)
    }
}