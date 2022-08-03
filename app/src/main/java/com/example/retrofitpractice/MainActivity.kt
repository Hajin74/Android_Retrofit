package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retrofitpractice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SignUpView, LoginView {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.joinSubmitBtn.setOnClickListener {
            signUp()
        }

        binding.loginSubmitBtn.setOnClickListener {
            login()
        }

        Log.d("MAIN/JWT_TO_SERVICE", getJwt().toString())
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt", "")
    }

    private fun getUser(): User {
        val email: String = binding.joinEmailEt.text.toString()
        val password: String = binding.joinPasswordEt.text.toString()
        val name: String = binding.joinNameEt.text.toString()

        return User(email, password, name)
    }

    private fun signUp() {
        if(binding.joinEmailEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.joinNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.joinPasswordEt.text.toString() != binding.joinPasswordCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val authService = AuthService()
        authService.setSignUpView(this)
        authService.signUp(getUser())
    }

    private fun login() {
        if(binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPasswordEt.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)
        authService.login(User(email, password, ""))
    }

    private fun saveJwt(jwt: String) {
        val spf = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    // SignupView 상속
    override fun onSignUpSuccess() {
        Toast.makeText(this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onSignUpFailure() {
        Toast.makeText(this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    // LoginView 상속
    override fun onLoginSuccess(code: Int, result: Result) {
        when(code) {
            1000 -> {
                saveJwt(result.jwt)
                onStart()
            }
        }
    }

    override fun onLoginFailure() {
        Toast.makeText(this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
    }
}