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
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs

import com.example.screeningapp.R
import com.example.screeningapp.databinding.FragmentStudentBinding
import com.example.screeningapp.databinding.FragmentSubjectDetailsBinding
import com.example.screeningapp.network.RResponse
import com.example.screeningapp.view_model.SubjectViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class SubjectDetailsFragment : Fragment() {
    val args: SubjectDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.subject_details).toUpperCase()
        val binding: FragmentSubjectDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_subject_details, container, false
        )
        val viewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)

        binding.subject = args.subject
        viewModel.getSubject(args.subject.id).observe(this, Observer {
            when (it) {
                is RResponse.Success -> {
                    binding.subject = it.data
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
        return binding.root
    }
}
