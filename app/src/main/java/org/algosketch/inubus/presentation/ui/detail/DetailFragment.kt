package org.algosketch.inubus.presentation.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.algosketch.inubus.R
import org.algosketch.inubus.common.base.BaseFragment
import org.algosketch.inubus.common.constant.BusStop
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.common.util.setBackgroundTint
import org.algosketch.inubus.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val layoutResourceId = R.layout.fragment_detail
    private val viewModel: DetailViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        lifecycleScope.launch {
            viewModel.restTime.value = arguments?.getInt("restTime").toString()
            viewModel.busNumber.value = arguments?.getString("busNumber") ?: "???"
            viewModel.exit.value = arguments?.getInt("exit").toString()
            viewModel.where.value = arguments?.getString("where") ?: "???"
            viewModel.distance.value = arguments?.getInt("distance").toString()
        }

        viewModel.imageId.value = Bus.getMapImageIdByBusNumber(
            arguments?.getString("busNumber"),
            if (arguments?.getString("where") == "인천대입구") BusStop.INU else BusStop.BIT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorMap = mapOf(
            "red" to R.color.red_bus,
            "blue" to R.color.blue_bus,
            "green" to R.color.green_bus,
            "purple" to R.color.purple_bus
        )

        binding.toolbarBackground.setBackgroundTint(
            colorMap[arguments?.getString("busColor")] ?: R.color.black_3
        )

        setupEvents()
    }

    private fun setupEvents() {
        viewModel.undoEvent.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }
}