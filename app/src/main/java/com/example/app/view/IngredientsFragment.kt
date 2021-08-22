package com.example.app.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.FragmentIngredientsBinding
import com.example.app.service.constants.IngredientConstants
import com.example.app.view.adapter.IngredientAdapter
import com.example.app.view.listener.IngredientListener
import com.example.app.viewmodel.IngredientsViewModel

/** AllGuestsFragment - referente a essa classe no curso
 * Aulas: 160-161-162-163-165*/
class IngredientsFragment : Fragment() {

    private lateinit var mViewModel: IngredientsViewModel
    private var _binding: FragmentIngredientsBinding? = null

    private val mAdapter: IngredientAdapter = IngredientAdapter()

    private val binding get() = _binding!!

    private lateinit var  mListener: IngredientListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mViewModel = ViewModelProvider(this).get(IngredientsViewModel::class.java)

        listIngredients(root)
        observe()
        createIngredient()
        listenerListIngredients()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load()
    }

    private fun listIngredients( root: View) {
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_ingredients)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter
    }

    private fun observe() {
        mViewModel.ingredientList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateIngredients(it)
        })
    }

    private fun createIngredient() {
        binding.fab.setOnClickListener {
            startActivity(Intent(context, CreateAndUpdateIngredientActivity::class.java))
        }
    }

    private fun listenerListIngredients() {
        mListener = object : IngredientListener {
            override fun onClick(id: Int) {
                val intent = Intent (context, CreateAndUpdateIngredientActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(IngredientConstants.INGREDIENTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }
            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load()
            }
        }
        mAdapter.attachListener(mListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}







