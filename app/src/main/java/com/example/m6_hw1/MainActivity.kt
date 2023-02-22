package com.example.m6_hw1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.m6_hw1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var forResult: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() {
        binding.btnSend.setOnClickListener {
            if (binding.etData.text.isEmpty()) {
                Toast.makeText(this, getString(R.string.edit_text_cannot_be_emty), Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, DataActivity::class.java)
                intent.putExtra(KEY, binding.etData.text.toString())
                forResult?.launch(intent)
            }
        }

        forResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val text = result.data?.getStringExtra(DATA)
                    binding.etData.setText(text)
                }
            }
    }

    companion object {
        const val KEY = "data"
        const val DATA = "key"
    }
}