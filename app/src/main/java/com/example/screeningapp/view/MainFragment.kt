package com.example.screeningapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.screeningapp.R
import com.example.screeningapp.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMainBinding = DataBindingUtil.inflate(inflater ,
            R.layout.fragment_main,container , false)

        binding.handler = ButtonClick()
        return binding.root
    }

    class ButtonClick{
        fun onClickSubject(view: View) {
            view.findNavController().navigate(R.id.action_openSubject)
        }

        fun onClickStudent(view: View) {
            view.findNavController().navigate(R.id.action_openStudent)
        }

        fun onClickClassRoom(view: View) {
            view.findNavController().navigate(R.id.action_openClassRoom)
        }
    }

}
