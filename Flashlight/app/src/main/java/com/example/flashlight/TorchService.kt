package com.example.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TorchService : Service() {    // Service 클래스 상속받음
    private val torch: Torch by lazy {  // torch 객체를 처음 사용할 때 초기화 됨
        Torch(this)
    }

    // 외부에서 startService() 메서드로 TorchService 를 호출하면 해당 콜백 메서드가 호출
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "on"->{
                torch.flashOn()
            }
            "off"->{
                torch.flashOff()
            }
        }

        // 리턴값에 따라 시스템이 강제 종료 당하면 어떻게 할지를 결정
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}