package com.mungeun.games.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mungeun.games.R
import com.mungeun.games.databinding.SelectFragmentBinding
import com.mungeun.games.viewmodel.SelectViewModel

class SelectFragment : Fragment() {

    private lateinit var binding : SelectFragmentBinding

    private val viewModel : SelectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.select_fragment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 데이터 바인딩을 위한 viewModel 설정 - 바인딩된 레이아웃 액세스를 허용합니다.
        binding.selectViewModel = viewModel

        // 바인딩의 수명 주기 소유자로 fragment를 지정합니다.
        // 바인딩이 LiveData 업데이트를 관찰할 수 있도록 사용됩니다.
        binding.lifecycleOwner = viewLifecycleOwner

        // 버튼에 대한 클릭 리스너를 설정합니다.
        binding.btnScrabble.setOnClickListener {
            this.findNavController().popBackStack()
            this.findNavController().navigate(R.id.scrabbleFragment)
        }

        binding.btnCarrot.setOnClickListener {
            this.findNavController().popBackStack()
            this.findNavController().navigate(R.id.carrotFragment)
        }
    }

}