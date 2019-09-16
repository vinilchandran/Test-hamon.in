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
import androidx.navigation.fragment.navArgs

import com.example.screeningapp.R
import com.example.screeningapp.databinding.FragmentStudentDetailsBinding
import com.example.screeningapp.network.RResponse
import com.example.screeningapp.view_model.StudentViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class StudentDetailsFragment : Fragment() {
    val args: StudentDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.student_details).toUpperCase()

        val binding : FragmentStudentDetailsBinding = DataBindingUtil.inflate(inflater ,
            R.layout.fragment_student_details,container , false)
        val viewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)

        binding.student = args.student
        viewModel.getStudent(args.student.id).observe(this, Observer {
            when (it) {
                is RResponse.Success -> {

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
