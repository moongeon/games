package com.mungeun.games.view

import android.app.Activity
import android.graphics.Insets
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mungeun.games.R
import com.mungeun.games.databinding.CarrotFragmentBinding
import com.mungeun.games.viewmodel.CarrotViewModel
import java.util.*


class CarrotFragment : Fragment() {

    private lateinit var binding: CarrotFragmentBinding
    private val viewModel: CarrotViewModel by viewModels()
    val BUG = "Bug"
    var bugNumber = 0


    val soundPool = SoundPool.Builder().setMaxStreams(4).build()
    fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    fun getScreenHeight(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.carrot_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = ArrayList<ImageView>()
        // 데이터 바인딩을 위한 viewModel 설정 - 바인딩된 레이아웃 액세스를 허용합니다.
        binding.carrotViewModel = viewModel
        // 바인딩의 수명 주기 소유자로 fragment를 지정합니다.
        // 바인딩이 LiveData 업데이트를 관찰할 수 있도록 사용됩니다.
        binding.lifecycleOwner = viewLifecycleOwner
        val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.bg)


        val onClickListener = View.OnClickListener {
            if (it.id in 0..10) {
                binding.root.removeView(it)
            }
        }

        binding.start.setOnClickListener {
            bugNumber += 1
            mediaPlayer.start()
            val i = ImageView(requireActivity()).apply {
                setImageResource(R.mipmap.bug)
                id = bugNumber
                isClickable = true
                layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                )
                y = rand(0, getScreenHeight(requireActivity()) - 50).toFloat()
                x = rand(0, getScreenWidth(requireActivity())).toFloat()
            }
            i.setOnClickListener(onClickListener)
            binding.root.addView(i, 50, 50)

        }


    }

    fun rand(from: Int, to: Int): Int {
        val random = Random()
        return random.nextInt(to - from) + from
    }


}


