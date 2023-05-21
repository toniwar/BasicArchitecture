package ru.otus.basicarchitecture.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.presentation.viewmodels.ActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var vm:ActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[ActivityViewModel::class.java]

    }
}