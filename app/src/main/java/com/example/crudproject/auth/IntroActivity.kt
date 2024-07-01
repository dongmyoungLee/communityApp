package com.example.crudproject.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.crudproject.MainActivity
import com.example.crudproject.R
import com.example.crudproject.databinding.ActivityIntroBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class IntroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    /*

    lateinit
    해당 변수가 나중에 초기화될 것임을 나타냅니다.
    즉, 변수 선언 시에 초기값을 할당하지 않고, 나중에 반드시 초기화할 것임을 보장합니다.
    이 키워드는 var로 선언된 비널(null) 타입이 아닌 변수를 나중에 초기화할 때 사용됩니다.

     */
    private lateinit var binding: ActivityIntroBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent);
        }

        binding.joinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent);
        }

        binding.noAccountBtn.setOnClickListener {
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

                        // 메인으로 이동..
                        val intent = Intent(this, MainActivity::class.java)

                        // 메인화면에서 뒤로가기 클릭 시 ..기존 activity를 다 날려버림 ..
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)

                    } else {

                        Toast.makeText(this, "로그인 실패: ${task.exception?.message}", Toast.LENGTH_LONG).show()


                    }
                }
        }


    }
}