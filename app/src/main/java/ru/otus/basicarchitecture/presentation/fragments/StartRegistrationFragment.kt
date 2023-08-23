package ru.otus.basicarchitecture.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.app.App
import ru.otus.basicarchitecture.databinding.FragmentStartRegistrationBinding
import ru.otus.basicarchitecture.di.DaggerChildComponent
import ru.otus.basicarchitecture.domain.repositories.DataStoreManager
import ru.otus.basicarchitecture.presentation.date_manage.DateChecker
import ru.otus.basicarchitecture.presentation.date_manage.DatePickerDialog
import ru.otus.basicarchitecture.presentation.fragments.listeners.FragmentListener
import ru.otus.basicarchitecture.presentation.view_models.StartRegistrationFragmentVM
import ru.otus.basicarchitecture.presentation.view_models.view_models_fabric.ViewModelsFabric
import javax.inject.Inject


class StartRegistrationFragment: Fragment() {

    private val binding by lazy {
        FragmentStartRegistrationBinding.inflate(layoutInflater)
    }
    private val mainComponent by lazy { (requireActivity().application as App).provideMainComponent() }

    private val component by lazy {DaggerChildComponent.factory().create(mainComponent)}

    @Inject
    lateinit var fabric:ViewModelsFabric
    private val vm by lazy {
        ViewModelProvider(this, fabric)[StartRegistrationFragmentVM::class.java]
    }

    private lateinit var listener: FragmentListener

    private val keys = listOf(
        DataStoreManager.Keys.UserInfoKeys.NAME,
        DataStoreManager.Keys.UserInfoKeys.SURNAME,
        DataStoreManager.Keys.UserInfoKeys.BIRTH_DAY,
        DataStoreManager.Keys.UserInfoKeys.BIRTH_MONTH,
        DataStoreManager.Keys.UserInfoKeys.BIRTH_YEAR

    )


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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                DateChecker.dateFlow.collect { dHolder ->
                    binding.apply {
                        dHolder?.let {
                            dayEt.setText(if(it.day == -1)"" else it.day.toString())
                            monthEt.setText(if(it.month == -1)"" else it.month.toString())
                            yearEt.setText(if(it.year == -1)"" else it.year.toString())
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                keys.forEach {
                    vm.getUserInfoFromDataStore(it)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                vm.state.collect{
                    val info = it
                    binding.apply {

                        info?.let { data ->

                                when (data.first) {
                                    DataStoreManager.Keys.UserInfoKeys.NAME ->
                                        nameEditText.setText(data.second)
                                    DataStoreManager.Keys.UserInfoKeys.SURNAME ->
                                        surnameEditText.setText(data.second)
                                    DataStoreManager.Keys.UserInfoKeys.BIRTH_DAY ->
                                        dayEt.setText(data.second)
                                    DataStoreManager.Keys.UserInfoKeys.BIRTH_MONTH ->
                                        monthEt.setText(data.second)
                                    DataStoreManager.Keys.UserInfoKeys.BIRTH_YEAR ->
                                        yearEt.setText(data.second)
                                    else ->{}
                                }


                        }
                    }
                }
            }
        }
        binding.apply {
            startRegistrationFragmentButton.setOnClickListener {
                val data = mapOf(
                    DataStoreManager.Keys.UserInfoKeys.NAME to nameEditText.text.toString(),
                    DataStoreManager.Keys.UserInfoKeys.SURNAME to surnameEditText.text.toString(),
                    DataStoreManager.Keys.UserInfoKeys.BIRTH_DAY to dayEt.text.toString(),
                    DataStoreManager.Keys.UserInfoKeys.BIRTH_MONTH to monthEt.text.toString(),
                    DataStoreManager.Keys.UserInfoKeys.BIRTH_YEAR to yearEt.text.toString()
                )
                if(vm.checkForEmptyData(data)){
                    if(vm.checkUserAge(yearEt.text.toString()))
                        listener.action(FragmentListener.Companion.ActionFlags.FRAGMENT_3)
                    else Toast.makeText(activity, "Access denied!", Toast.LENGTH_SHORT).show()
                }
                else Toast.makeText(activity, "Fill in the empty fields!", Toast.LENGTH_SHORT).show()
            }
            calendarIcon.setOnClickListener {
                DatePickerDialog.showDatePickerDialog(requireContext()) {
                    val date = it.split(".")
                    dayEt.setText(date[0])
                    monthEt.setText(date[1])
                    yearEt.setText(date[2])
                }

            }
        }
    }
}
