package ru.otus.basicarchitecture.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.VMStateFlags
import ru.otus.basicarchitecture.presentation.fragments.*
import ru.otus.basicarchitecture.presentation.viewmodels.ActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var vm:ActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[ActivityViewModel::class.java]
        vm.liveData.observe(this){
            @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
            when(it){
                VMStateFlags.FRAGMENT_1 -> openFragment(Fragment1())
                VMStateFlags.FRAGMENT_2 -> openFragment(Fragment2())
                VMStateFlags.FRAGMENT_3 -> openFragment(Fragment3())
                VMStateFlags.FRAGMENT_4 -> openFragment(Fragment4())
            }
        }


    }

    private fun openFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrameLayout, fragment )
            .commit()
    }
}