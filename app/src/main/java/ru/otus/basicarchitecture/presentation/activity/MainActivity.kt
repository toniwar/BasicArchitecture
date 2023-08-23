package ru.otus.basicarchitecture.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.app.App
import ru.otus.basicarchitecture.di.DaggerChildComponent
import ru.otus.basicarchitecture.domain.repositories.UserRepository
import ru.otus.basicarchitecture.presentation.fragments.AddressFragment
import ru.otus.basicarchitecture.presentation.fragments.listeners.FragmentListener
import ru.otus.basicarchitecture.presentation.fragments.Menu
import ru.otus.basicarchitecture.presentation.fragments.StartRegistrationFragment
import ru.otus.basicarchitecture.presentation.fragments.UserInfoFragment
import ru.otus.basicarchitecture.presentation.fragments.UserInterestsFragment
import ru.otus.basicarchitecture.presentation.fragments.UserListFragment
import javax.inject.Inject


class MainActivity : AppCompatActivity(), FragmentListener {


    private val mainComponent by lazy { (application as App).provideMainComponent() }
    private val component by lazy { DaggerChildComponent.factory().create(mainComponent) }

    @Inject
    lateinit var menu: Menu

    @Inject
    lateinit var startRegistrationFragment: StartRegistrationFragment

    @Inject
    lateinit var addressFragment: AddressFragment

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
        userRepository.saveUser()

    }

    override fun action(flag: FragmentListener.Companion.ActionFlags) {
        when(flag){
            FragmentListener.Companion.ActionFlags.FRAGMENT_1 -> openFragment(menu)
            FragmentListener.Companion.ActionFlags.FRAGMENT_2 -> openFragment(startRegistrationFragment)
            FragmentListener.Companion.ActionFlags.FRAGMENT_3 -> openFragment(addressFragment)
            FragmentListener.Companion.ActionFlags.FRAGMENT_4 -> openFragment(UserInterestsFragment())
            FragmentListener.Companion.ActionFlags.FRAGMENT_5 -> openFragment(UserInfoFragment())
            FragmentListener.Companion.ActionFlags.FRAGMENT_6 -> openFragment(UserListFragment())

        }
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("$fragment")
            .commit()
    }

}