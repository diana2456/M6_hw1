package com.example.m6_hw1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.m6_hw1.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {

        binding.etData.setText(intent.getStringExtra(MainActivity.KEY))

        binding.btnSend.setOnClickListener {
            if (binding.etData.text.isEmpty()) {
                Toast.makeText(this, getString(R.string.edit_text_cannot_be_emty), Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent()
                intent.putExtra(MainActivity.DATA, binding.etData.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}


