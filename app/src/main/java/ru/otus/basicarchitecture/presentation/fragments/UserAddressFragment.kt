package ru.otus.basicarchitecture.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.StateFlags
import ru.otus.basicarchitecture.app.App
import ru.otus.basicarchitecture.databinding.FragmentUserAddressBinding
import ru.otus.basicarchitecture.domain.models.NetworkError
import ru.otus.basicarchitecture.domain.models.NetworkException
import ru.otus.basicarchitecture.domain.models.NetworkRequest
import ru.otus.basicarchitecture.domain.models.NetworkSuccess
import ru.otus.basicarchitecture.domain.models.Suggestions
import ru.otus.basicarchitecture.domain.models.UserAddress
import ru.otus.basicarchitecture.presentation.viewmodels.ActivityVMFactory
import ru.otus.basicarchitecture.presentation.viewmodels.ActivityViewModel
import ru.otus.basicarchitecture.presentation.viewmodels.UserAddressFragmentVMFactory
import ru.otus.basicarchitecture.presentation.viewmodels.UserAddressFragmentViewModel
import javax.inject.Inject


class UserAddressFragment : Fragment() {
    private val request by lazy { NetworkRequest() }
    private val binding by lazy { FragmentUserAddressBinding.inflate(layoutInflater) }

    @Inject
    lateinit var factory: UserAddressFragmentVMFactory
    private lateinit var viewModel: UserAddressFragmentViewModel

    @Inject
    lateinit var activityVMFactory: ActivityVMFactory
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var adapter: ArrayAdapter<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as App).appComponent.injectUserAddressFragment(this)
        viewModel = ViewModelProvider(this, factory)[UserAddressFragmentViewModel::class.java]
        activityViewModel =
            ViewModelProvider(requireActivity(), activityVMFactory)[ActivityViewModel::class.java]
        adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item)
        binding.editAddress.setAdapter(adapter)

        viewModel.outputData.observe(viewLifecycleOwner) { user ->
            binding.editAddress.setText(user.address)
        }

        binding.sendDataButton.setOnClickListener {

            val address = binding.editAddress.text.toString()
            viewModel.inputData.value = UserAddress(address = address)
            viewModel.saveData()
            activityViewModel.setFlag(StateFlags.FRAGMENT_3)

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sendData(
                    binding.editAddress.textChanges().debounce(500)
                )

                viewModel.state.collect { result ->
                    result?.let {

                        when (result) {
                            is NetworkSuccess<*> -> {
                                adapter.clear()
                                val suggestions = result.networkData as Suggestions
                                suggestions.suggestions.forEach { address ->
                                    adapter.add(address.value)
                                }
                            }

                            is NetworkError -> {
                                Toast.makeText(
                                    requireContext(),
                                    "${result.errorCode}: ${result.errorMessage}",
                                    Toast.LENGTH_LONG

                                ).show()
                            }

                            is NetworkException -> {
                                Log.e("NetworkException", (result.exception as Throwable).message!!)
                            }

                        }
                    }

                }
            }


        }

    }


    private fun sendData(flow: Flow<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            flow.collect {
                request.query = it
                viewModel.sendRequest(request)
            }
        }
    }


}

