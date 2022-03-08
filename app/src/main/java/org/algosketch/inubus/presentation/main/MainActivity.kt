package org.algosketch.inubus.presentation.main

import androidx.activity.viewModels
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.ActivityMainBinding
import org.algosketch.inubus.global.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId = R.layout.activity_main
    private val viewModel: MainViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }
}