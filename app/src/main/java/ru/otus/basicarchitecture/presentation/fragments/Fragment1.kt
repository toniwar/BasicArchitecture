package ru.otus.basicarchitecture.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.Fragment1Binding


class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(inflater)
        return binding.root
    }


}