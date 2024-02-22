package com.example.hiltsetup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hiltsetup.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        initViews()
        setUpObserver()
    }

    private fun setUpObserver() {
        with(viewModel) {
            dogResponse.observe(this@MainActivity) {
                Glide.with(this@MainActivity)
                    .load(it.message)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(android.R.drawable.ic_dialog_alert)
                    .into(binding.imageOfDog)
            }

            viewModel.error.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[DogViewModel::class.java]
    }

    private fun initViews() {
        binding.btnNewDog.setOnClickListener {
            viewModel.getRandomDogs()
        }
    }
}