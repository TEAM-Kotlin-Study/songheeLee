package ddwu.mobile.week06.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        //화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //화면이 가로 모드로 고정되게 하기
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.d("MainActivity", "onSensorChanged: x: " +
                    "${event.values[0]}, y: ${event.values[1]}, x: ${event.values[2]}")

            tiltView.onSensorEvent(event)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,                     //메서드로 사용할 센서 등록
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),  //사용할 센서 종류 지정 
            SensorManager.SENSOR_DELAY_NORMAL)                          //센서값을 얼마나 자주 받을지 지정

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }
}