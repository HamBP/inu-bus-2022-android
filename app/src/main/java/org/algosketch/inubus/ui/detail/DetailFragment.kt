package org.algosketch.inubus.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentDetailBinding
import org.algosketch.inubus.global.base.BaseFragment
import org.algosketch.inubus.global.util.Bus

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val layoutResourceId = R.layout.fragment_detail
    private val viewModel: DetailViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
        viewModel.shouldUndo.observe(this, {
            findNavController().popBackStack()
        })
    }

    override fun initState() {
        viewModel.restTime.postValue("\"버스 ${arguments?.getInt("restTime")}분 뒤에 와요\"")
        viewModel.busNumber.postValue(arguments?.getString("busNumber"))
        viewModel.exit.postValue("정류장은 ${arguments?.getString("where")}역 ${arguments?.getInt("exit")}번 출구에서")
        viewModel.distance.postValue("${arguments?.getInt("distance")}m")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorMap = mapOf(
            "red" to R.color.red_bus,
            "blue" to R.color.blue_bus,
            "green" to R.color.green_bus,
            "purple" to R.color.purple_bus
        )

        val toolbarBackground = view.findViewById<View>(R.id.toolbar_background)
        setBackgroundTint(toolbarBackground, colorMap[arguments?.getString("busColor")] ?: R.color.black_3)

        val mapImage = view.findViewById<ImageView>(R.id.map_image)
        mapImage.setImageDrawable(ContextCompat.getDrawable(view.context, Bus.getMapImageIdByBusNumber(arguments?.getString("busNumber"))))
    }

    fun setBackgroundTint(view: View, colorId: Int) {
        var drawable: Drawable? = DrawableCompat.wrap(view.background!!)
        drawable = DrawableCompat.wrap(drawable!!)

        DrawableCompat.setTint(drawable, ContextCompat.getColor(view.context, colorId))
    }
}