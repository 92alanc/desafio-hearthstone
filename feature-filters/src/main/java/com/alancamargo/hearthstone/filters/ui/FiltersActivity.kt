package com.alancamargo.hearthstone.filters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.hearthstone.filters.databinding.ActivityFiltersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class FiltersActivity : AppCompatActivity() {

    private var _binding: ActivityFiltersBinding? = null
    private val binding: ActivityFiltersBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
