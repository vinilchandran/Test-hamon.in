package com.example.screeningapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.screeningapp.R
import com.example.screeningapp.adapter.SubjectAdapter
import com.example.screeningapp.databinding.FragmentSubjectBinding
import com.example.screeningapp.model.Subject
import com.example.screeningapp.network.RResponse
import com.example.screeningapp.view_model.SubjectViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.Disposable

/**
 * A simple [Fragment] subclass.
 */
class SubjectFragment : Fragment() {
    private lateinit var disposable: Disposable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.subject).toUpperCase()

        val binding : FragmentSubjectBinding = DataBindingUtil.inflate(inflater ,
            R.layout.fragment_subject,container , false)

        val viewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)

        val adapter = SubjectAdapter()
        binding.recyclerView.adapter = adapter

        binding.progress.visibility = View.VISIBLE
        viewModel.getSubjectList().observe(this, Observer {
            binding.progress.visibility = View.GONE
            when (it) {
                is RResponse.Success -> {
                    binding.progress.visibility = View.GONE
                    adapter.addItems(ArrayList(it.data.subjects))
                }
                is RResponse.Error -> {
                    Snackbar.make(
                        binding.root,
                        "Something went wrong",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
        disposable = adapter.clickEvent().subscribe {
            openSubject(it)
        }
        return binding.root
    }

    override fun onDetach() {
        disposable.dispose()
        super.onDetach()
    }

    private fun openSubject(subject:Subject){
        findNavController().navigate(SubjectFragmentDirections.actionOpenSubjectDetails(subject))
    }
}