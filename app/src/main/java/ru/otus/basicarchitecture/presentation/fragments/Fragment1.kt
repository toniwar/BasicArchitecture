package ru.otus.basicarchitecture.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.otus.basicarchitecture.app.App
import ru.otus.basicarchitecture.databinding.Fragment1Binding
import ru.otus.basicarchitecture.domain.models.UserAge
import ru.otus.basicarchitecture.domain.models.UserName
import ru.otus.basicarchitecture.domain.models.UserSurname
import ru.otus.basicarchitecture.presentation.viewmodels.Fragment1ViewModel
import ru.otus.basicarchitecture.presentation.viewmodels.FragmentVMFactory
import javax.inject.Inject


class Fragment1 : Fragment() {
    @Inject
    lateinit var factory:FragmentVMFactory
    private lateinit var viewModel:Fragment1ViewModel
    private lateinit var binding: Fragment1Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as App).appComponent.injectFragment1(this)
        viewModel = ViewModelProvider(this, factory)[Fragment1ViewModel::class.java]
        viewModel.outputData.observe(viewLifecycleOwner){user->
            binding.apply {
                f1NameField.setText(user.name)
                f1SurnameField.setText(user.surname)
                f1BirthDate.setText(user.age.toString())
            }
        }

        binding.f1Button.setOnClickListener {
            val name = binding.f1NameField.text.toString()
            val surname = binding.f1SurnameField.text.toString()
            val age = binding.f1BirthDate.text.toString()

            viewModel.inputData.value = listOf(
                UserName(name),
                UserSurname(surname),
                UserAge(age.toInt())
            )
            viewModel.saveData()
        }
    }


}