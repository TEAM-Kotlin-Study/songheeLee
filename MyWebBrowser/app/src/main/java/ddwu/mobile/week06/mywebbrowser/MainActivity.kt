package ddwu.mobile.week06.mywebbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import ddwu.mobile.week06.mywebbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    binding.urlEditText.setText(url)
                }
            }
        }

        binding.webView.loadUrl("https://www.google.com")

        binding.urlEditText.setOnEditorActionListener {_, actionId, _ ->
            //actionId값은 검색 버튼에 해당하는 상수와 비교하여 검색 버튼이 눌렸는지 확인
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.webView.loadUrl(binding.urlEditText.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onBackPressed(){
        if(binding.webView.canGoBack()){    //웹뷰가 이전 페이지로 갈 수 있다면
            binding.webView.goBack()        //이전 페이지로 이동
        } else {                            //그렇지 않다면
            super.onBackPressed()           //원래 동작(엑티비티종료) 수행
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater 객체의 inflate() 메서드 사용하여 메뉴 리소스 지정
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_google, R.id.action_home -> {
                binding.webView.loadUrl("https://google.com")
                return true
            }
            R.id.action_naver -> {
                binding.webView.loadUrl("https://naver.com")
                return true
            }
            R.id.action_daum -> {
                binding.webView.loadUrl("https://daum.net")
                return true
            }
            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
            //문자 보내기? 이메일 보내기?
        }

        return super.onOptionsItemSelected(item)
    }
}