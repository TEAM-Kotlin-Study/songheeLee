package ddwu.mobile.week06.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.util.Log
import ddwu.mobile.week06.bmicalculator.databinding.ActivityMainBinding
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        //ActivityMainBinding 클래스는 activity_main 파일의 이름을 참고하여 자동으로 생성된 클래스
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //onCreate 메소드에 추가한 코드에 의해
        // 바인딩 객체의 근원인 activity_main.xml을 액티비티 화면으로 인식하게 됨
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()

        binding.resultButton.setOnClickListener {
            if (binding.weightEditText.text.isNotBlank() && binding.heightEditText.text.isNotBlank()) {
                saveData(
                    binding.heightEditText.text.toString().toFloat(),
                    binding.weightEditText.text.toString().toFloat(),
                )

                val intent = Intent(this, ResultActivity::class.java).apply {
                    Log.i(NULL.toString(), "binding.resultButton.setOnClickListener의 if문 실행")
                    putExtra("weight", binding.weightEditText.text.toString().toFloat())
                    putExtra("height", binding.heightEditText.text.toString().toFloat())
                }

                startActivity(intent)
                Log.i(NULL.toString(), "binding.resultButton.setOnClickListener의 startActivity 실행")
            }
        }
    }

    private fun saveData(height: Float, weight: Float) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putFloat("KEY_HEIGHT", height)
            .putFloat("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getFloat("KEY_HEIGHT", 0f)
        val weight = pref.getFloat("KEY_WEIGHT", 0f)

        if (height != 0f && weight != 0f) {
            binding.heightEditText.setText(height.toString())
            binding.weightEditText.setText(weight.toString())
        }
    }
}