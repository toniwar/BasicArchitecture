package ru.otus.basicarchitecture.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.databinding.FragmentStartRegistrationBinding
import ru.otus.basicarchitecture.presentation.DateChecker
import ru.otus.basicarchitecture.presentation.dialogs.DatePickerDialog
import ru.otus.basicarchitecture.presentation.fragments.listeners.FragmentListener
import ru.otus.basicarchitecture.presentation.view_models.StartRegistrationFragmentVM
import javax.inject.Inject


class StartRegistrationFragment @Inject constructor() : Fragment() {

    private val binding by lazy {
        FragmentStartRegistrationBinding.inflate(layoutInflater)
    }

    private val vm by lazy {
        ViewModelProvider(requireActivity())[StartRegistrationFragmentVM::class.java]
    }
    private lateinit var listener: FragmentListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            listener = context
        } else throw RuntimeException("Unknown element: $context")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                DateChecker.dateMap.collect { dMap ->
                    binding.apply {
                        dMap?.let {
                            dateEt.setText(dMap[DateChecker.DateContent.DD])
                            monthEt.setText(dMap[DateChecker.DateContent.MM])
                            yearEt.setText(dMap[DateChecker.DateContent.YYYY])
                        }
                    }
                }
            }
        }

        binding.apply {
            startRegistrationFragmentButton.setOnClickListener {
                listener.action(FragmentListener.Companion.ActionFlags.FRAGMENT_3)

            }

            calendarIcon.setOnClickListener {
                DatePickerDialog.showDatePickerDialog(requireContext()) {
                    val date = it.split(".")
                    dateEt.setText(date[0])
                    monthEt.setText(date[1])
                    yearEt.setText(date[2])
                }

            }
            dateEt.textChanges(dateEt, monthEt, yearEt)
            monthEt.textChanges(dateEt, monthEt, yearEt)
            yearEt.textChanges(dateEt, monthEt, yearEt)

        }
    }

    private fun EditText.textChanges(
        dateET: EditText,
        monthET: EditText,
        yearET: EditText
    ) {
        setOnFocusChangeListener { _, b ->

            if(!b) {
                (DateChecker.verifyDate(
                    dateET.text.toString(),
                    monthET.text.toString(),
                    yearET.text.toString()
                ))
            }
        }

    }

}
