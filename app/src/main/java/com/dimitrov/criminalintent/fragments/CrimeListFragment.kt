package com.dimitrov.criminalintent.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimitrov.criminalintent.CrimeListAdapter
import com.dimitrov.criminalintent.CrimeListViewModel
import com.dimitrov.criminalintent.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CrimeListFragment : Fragment() {
    // bind the layout
    private var _binding: FragmentCrimeListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val crimeListViewModel: CrimeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(inflater, container, false)

        binding.crimeListRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    // null out the binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //repeatOnLifecycle(…) will begin executing your coroutine code when your fragment enters the
        // started state and will continue running in the resumed state.
        // But if your app is backgrounded and your fragment is no longer visible, repeatOnLifecycle(…) will
        // cancel the work once the fragment falls from the started state to the created state.
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val crimes = crimeListViewModel.loadCrimes()
                binding.crimeListRecyclerView.adapter = CrimeListAdapter(crimes)
            }
        }
    }
}