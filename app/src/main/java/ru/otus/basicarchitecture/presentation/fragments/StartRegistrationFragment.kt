package ru.otus.basicarchitecture.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.otus.basicarchitecture.R
import javax.inject.Inject


class StartRegistrationFragment @Inject constructor(): Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_registration, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = StartRegistrationFragment()


    }
}