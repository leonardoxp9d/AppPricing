package com.example.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.databinding.FragmentMaterialsBinding
import com.example.app.viewmodel.MaterialsViewModel

class MaterialsFragment : Fragment() {

    private lateinit var materialsViewModel: MaterialsViewModel
    private var _binding: FragmentMaterialsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        materialsViewModel =
            ViewModelProvider(this).get(MaterialsViewModel::class.java)

        _binding = FragmentMaterialsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMaterials
        materialsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}