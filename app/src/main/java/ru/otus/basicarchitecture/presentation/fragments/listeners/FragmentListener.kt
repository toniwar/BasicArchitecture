package ru.otus.basicarchitecture.presentation.fragments.listeners



interface FragmentListener {

    fun action(flag: ActionFlags)

    companion object{
        enum class ActionFlags{
            FRAGMENT_1,
            FRAGMENT_2,
            FRAGMENT_3,
            FRAGMENT_4,
            FRAGMENT_5,
            FRAGMENT_6
        }
    }
}
