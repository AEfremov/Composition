package ru.efremov.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.efremov.composition.R
import ru.efremov.composition.databinding.FragmentGameBinding
import ru.efremov.composition.domain.entity.GameResult

class GameFragment : Fragment(R.layout.fragment_game) {

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory: GameViewModelFactory by lazy {
//        val args = GameFragmentArgs.fromBundle(requireArguments())
        GameViewModelFactory(args.level, requireActivity().application)
    }
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

//    private val tvOptions by lazy {
//        mutableListOf<TextView>().apply {
//            add(binding.tvOption1)
//            add(binding.tvOption2)
//            add(binding.tvOption3)
//            add(binding.tvOption4)
//            add(binding.tvOption5)
//            add(binding.tvOption6)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
    }

    private fun observeViewModel() {
//        viewModel.question.observe(viewLifecycleOwner) {
//            binding.tvSum.text = it.sum.toString()
//            binding.tvLeftNumber.text = it.visibleNumber.toString()
//            for (i in 0 until tvOptions.size) {
//                tvOptions[i].text = it.options[i].toString()
//            }
//        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

//    private fun setClickListenersToOptions() {
//        for (tvOption in tvOptions) {
//            tvOption.setOnClickListener {
//                viewModel.chooseAnswer(tvOption.text.toString().toInt())
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult)
        )
    }
}