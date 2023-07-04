package com.hugo.andrada.conectivity.observer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.hugo.andrada.conectivity.observer.databinding.ActivityMainBinding
import com.hugo.andrada.conectivity.observer.util.ConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.status.collect { status ->
                    when (status) {
                        ConnectivityObserver.Status.Available -> {
                            binding.status.setTextColor(getColor(R.color.success_color))
                            binding.status.text = status.toString()
                            showSnackBar("Connection Ok")
                        }
                        ConnectivityObserver.Status.Unvailable -> {
                            binding.status.setTextColor(getColor(R.color.error_color))
                            binding.status.text = status.toString()
                            showSnackBar("No Internet")
                        }
                        ConnectivityObserver.Status.Losing -> {
                            binding.status.setTextColor(getColor(R.color.error_color))
                            binding.status.text = status.toString()
                            showSnackBar("No Internet")
                        }
                        ConnectivityObserver.Status.Lost -> {
                            binding.status.setTextColor(getColor(R.color.error_color))
                            binding.status.text = status.toString()
                            showSnackBar("No Internet")
                        }
                    }
                }
            }
        }
    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }
}