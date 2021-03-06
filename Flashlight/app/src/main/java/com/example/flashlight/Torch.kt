package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch(context: Context) { // 생성자로 Context 객체 받음
    private var cameraId: String? = null

    // getSystemService() 메서드는 안드로이드 시스템에서 제공하는 각종 서비스를 관리하는 매니저 클래스 생성
    // Object형을 반환하기 때문에 as 연산자를 사용하여 CameraService형으로 형변환
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init {
        cameraId = getCameraId()
    }

    fun flashOn(){
        cameraId?.let {
            cameraManager.setTorchMode(it, true)
        }
    }

    fun flashOff(){
        cameraId?.let {
            cameraManager.setTorchMode(it, false)
        }
    }

    private fun getCameraId(): String? {
        val cameraIds = cameraManager.cameraIdList
        for(id in cameraIds){
            val info = cameraManager.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.LENS_FACING)
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)

            if(flashAvailable != null && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK)
                return id
        }

        return null
    }
}