package ru.otus.basicarchitecture.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import ru.otus.basicarchitecture.app.App
import ru.otus.basicarchitecture.databinding.FragmentStartRegistrationBinding
import ru.otus.basicarchitecture.di.DaggerChildComponent
import ru.otus.basicarchitecture.presentation.DateChecker
import ru.otus.basicarchitecture.presentation.dialogs.DatePickerDialog
import ru.otus.basicarchitecture.presentation.fragments.listeners.FragmentListener
import ru.otus.basicarchitecture.presentation.view_models.StartRegistrationFragmentVM
import ru.otus.basicarchitecture.presentation.view_models.view_models_fabric.ViewModelsFabric
import javax.inject.Inject


class StartRegistrationFragment @Inject constructor() : Fragment() {

    private val binding by lazy {
        FragmentStartRegistrationBinding.inflate(layoutInflater)
    }
    private val mainComponent by lazy { App.provideApp().provideMainComponent() }

    private val component by lazy {DaggerChildComponent.factory().create(mainComponent)}

    @Inject
    lateinit var fabric:ViewModelsFabric
    private val vm by lazy {
        ViewModelProvider(this, fabric)[StartRegistrationFragmentVM::class.java]
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
        component.injectStartRegistrationFragment(this)
        Log.d("CheckVMInstance", vm.toString())

        val job1 = lifecycleScope.launch {
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

        val job2 = lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                vm.state.collect{

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

    private fun View.textChanges(
        dateET: EditText,
        monthET: EditText,
        yearET: EditText
    ) {
        setOnFocusChangeListener { _, b ->

            if(!b) {
                DateChecker.verifyDate(
                    dateET.text.toString(),
                    monthET.text.toString(),
                    yearET.text.toString()
                )
            }
        }

    }

}
