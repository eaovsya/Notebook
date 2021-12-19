package com.example.notebook.empty

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notebook.databinding.FragmentEmptyBinding
import com.example.notebook.main.MainActivity
import javax.inject.Inject

class EmptyFragment : Fragment(), EmptyContract.View {

    private var _binding: FragmentEmptyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: EmptyPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivityComponent.emptyComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyBinding.inflate(inflater, container, false)
        initView()
        presenter.bindView(this)
        return binding.root
    }

    private fun initView() {
        binding.addButton.setOnClickListener {
            presenter.onAddClicked()
        }
        binding.addDummies.setOnClickListener {
            presenter.onAddDummiesClicled()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unBindView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_EMPTY_TAG = "FRAGMENT_EMPTY_TAG"
        fun newInstance() = EmptyFragment()
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}