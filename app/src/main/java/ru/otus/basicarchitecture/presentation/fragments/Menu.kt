package ru.otus.basicarchitecture.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.otus.basicarchitecture.databinding.FragmentMenuBinding


class Menu : Fragment() {
    private lateinit var listener: FragmentListener
    private val binding by lazy { FragmentMenuBinding.inflate(layoutInflater) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            listener = context
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            registerButton.setOnClickListener {
                listener.action(FragmentListener.Companion.ActionFlags.FRAGMENT_2)
            }
        }

    }


}
