package org.algosketch.inubus.presentation.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.algosketch.inubus.R
import org.algosketch.inubus.common.base.BaseFragment
import org.algosketch.inubus.common.constant.BusStop
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val layoutResourceId = R.layout.fragment_detail
    private val viewModel: DetailViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        viewModel.restTime.postValue("\"버스 ${arguments?.getInt("restTime")}분 뒤에 와요\"")
        viewModel.busNumber.postValue(arguments?.getString("busNumber"))
        viewModel.exit.postValue("정류장은 ${arguments?.getString("where")}역 ${arguments?.getInt("exit")}번 출구에서")
        viewModel.distance.postValue("${arguments?.getInt("distance")}m")
        viewModel.imageId.postValue(
            Bus.getMapImageIdByBusNumber(
                arguments?.getString("busNumber"),
                if (arguments?.getString("where") == "인천대입구") BusStop.INU else BusStop.BIT
            )
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

        setBackgroundTint(
            binding.toolbarBackground,
            colorMap[arguments?.getString("busColor")] ?: R.color.black_3
        )

        setupEvents()
    }

    private fun setBackgroundTint(view: View, colorId: Int) {
        var drawable: Drawable? = DrawableCompat.wrap(view.background!!)
        drawable = DrawableCompat.wrap(drawable!!)

        DrawableCompat.setTint(drawable, ContextCompat.getColor(view.context, colorId))
    }

    private fun setupEvents() {
        viewModel.undoEvent.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }
}