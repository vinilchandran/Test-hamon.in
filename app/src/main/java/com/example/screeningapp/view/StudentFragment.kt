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
import com.example.screeningapp.adapter.StudentAdapter
import com.example.screeningapp.databinding.FragmentStudentBinding
import com.example.screeningapp.model.Student
import com.example.screeningapp.network.RResponse
import com.example.screeningapp.view_model.StudentViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.Disposable

/**
 * A simple [Fragment] subclass.
 */
class StudentFragment : Fragment() {
    private lateinit var disposable: Disposable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.student).toUpperCase()

        val binding : FragmentStudentBinding = DataBindingUtil.inflate(inflater ,
            R.layout.fragment_student,container , false)

        val viewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)

        val adapter = StudentAdapter()
        binding.recyclerView.adapter = adapter

        binding.progress.visibility = View.VISIBLE
        viewModel.getStudentList().observe(this, Observer {
            when (it) {
                is RResponse.Success -> {
                    binding.progress.visibility = View.GONE
                    adapter.addItems(ArrayList(it.data.students))
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
            openStudent(it)
        }
        return binding.root
    }

    override fun onDetach() {
        disposable.dispose()
        super.onDetach()
    }

    private fun openStudent(student:Student){
        findNavController().navigate(StudentFragmentDirections.actionOpenStudent(student))
    }
}