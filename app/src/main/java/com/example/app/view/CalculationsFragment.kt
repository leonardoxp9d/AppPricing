package com.example.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.databinding.FragmentCalculationsBinding
import com.example.app.viewmodel.CalculationsViewModel

class CalculationsFragment : Fragment() {

    private lateinit var calculationsViewModel: CalculationsViewModel
    private var _binding: FragmentCalculationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calculationsViewModel =
            ViewModelProvider(this).get(CalculationsViewModel::class.java)

        _binding = FragmentCalculationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCalculations
        calculationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}