package com.example.screeningapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs

import com.example.screeningapp.R
import com.example.screeningapp.adapter.SubjectSpinnerAdapter
import com.example.screeningapp.databinding.FragmentClassRoomDetailsBinding
import com.example.screeningapp.view_model.ClassRoomViewModel
import com.example.screeningapp.model.ClassRoom
import com.example.screeningapp.model.Subject
import com.example.screeningapp.model.Subjects
import com.example.screeningapp.network.RResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_class_room_details.*


/**
 * A simple [Fragment] subclass.
 */
class ClassRoomDetailsFragment : Fragment() {
    private val args: ClassRoomDetailsFragmentArgs by navArgs()
    private var skipOnItemSelected = true
    private var spinnerSelectionIndex = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.classroom_details).toUpperCase()
        val binding: FragmentClassRoomDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_class_room_details, container, false
        )
        val viewModel = ViewModelProviders.of(this).get(ClassRoomViewModel::class.java)


        binding.progress.visibility = View.VISIBLE
        binding.classroom = args.classroom

        //Get class room data
        viewModel.getClassRoom(args.classroom.id)
            .observe(this, Observer { claaResponse: RResponse<ClassRoom>? ->
                when (claaResponse) {
                    is RResponse.Success -> {
                        binding.classroom = claaResponse.data
                        // get list of subjects
                        viewModel.getSubjectList().observe(
                            this,
                            Observer { response: RResponse<Subjects>? ->
                                when (response) {
                                    is RResponse.Success -> {
                                        response.data.subjectList.let { subjectList: ArrayList<Subject> ->
                                            binding.progress.visibility = View.GONE
                                            claaResponse.data.subject?.let {
                                                for ((index, value) in subjectList.withIndex()) {
                                                    if (value.id.toString() == claaResponse.data.subject) {
                                                        spinnerSelectionIndex = index
                                                    }
                                                }
                                            }
                                            subjectList.add(
                                                0,
                                                Subject(
                                                    credits = 0,
                                                    teacher = "",
                                                    name = "Select",
                                                    id = 0
                                                )
                                            )

                                            val adapter =
                                                SubjectSpinnerAdapter(context!!, subjectList)
                                            binding.spinner.adapter = adapter
                                            spinnerSelectionIndex++
                                            binding.spinner.setSelection(spinnerSelectionIndex)
                                            spinner.onItemSelectedListener = object :
                                                AdapterView.OnItemSelectedListener {
                                                override fun onNothingSelected(p0: AdapterView<*>?) {

                                                }
                                                override fun onItemSelected(
                                                    adapterView: AdapterView<*>?, view: View?,
                                                    position: Int, p3: Long
                                                ) =
                                                    if (skipOnItemSelected) {
                                                        skipOnItemSelected = false
                                                    } else {
                                                        val s_id =
                                                            subjectList[adapterView!!.selectedItemPosition].id
                                                        if (s_id == 0) {
                                                            Snackbar.make(
                                                                binding.root,
                                                                "Please select one subject!",
                                                                Snackbar.LENGTH_LONG
                                                            ).show()
                                                            resetSpinnerSelection(
                                                                binding.spinner,
                                                                this
                                                            )
                                                        } else {
                                                            binding.progress.visibility =
                                                                View.VISIBLE
                                                            viewModel.assignSubject(
                                                                args.classroom.id,
                                                                s_id
                                                            ).observe(
                                                                this@ClassRoomDetailsFragment,
                                                                Observer { result ->
                                                                    binding.progress.visibility =
                                                                        View.GONE
                                                                    when (result) {
                                                                        is RResponse.Success -> {
                                                                            spinnerSelectionIndex =
                                                                                adapterView.selectedItemPosition
                                                                            Snackbar.make(
                                                                                binding.root,
                                                                                "Subject updates successfully",
                                                                                Snackbar.LENGTH_LONG
                                                                            ).show()
                                                                        }
                                                                        is RResponse.Error -> {
                                                                            resetSpinnerSelection(
                                                                                binding.spinner,
                                                                                this
                                                                            )
                                                                            Snackbar.make(
                                                                                binding.root,
                                                                                "Something went wrong",
                                                                                Snackbar.LENGTH_LONG
                                                                            ).show()
                                                                        }
                                                                    }
                                                                }
                                                            )
                                                        }
                                                    }
                                            }
                                        }
                                    }
                                    is RResponse.Error -> {
                                        Toast.makeText(
                                            context,
                                            response.error.toString(),
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }
                                }
                            })
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

    private fun resetSpinnerSelection(
        spinner: Spinner,
        onItemSelectedListener: AdapterView.OnItemSelectedListener
    ) {
        spinner.onItemSelectedListener = null
        spinner.setSelection(spinnerSelectionIndex, false)
        spinner.onItemSelectedListener = onItemSelectedListener
    }
}
