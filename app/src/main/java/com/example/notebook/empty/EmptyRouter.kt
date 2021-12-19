package com.example.notebook.empty

import androidx.fragment.app.FragmentManager
import com.example.notebook.R
import com.example.notebook.addedit.add.AddFragment
import com.example.notebook.contacts.ContactsFragment
import javax.inject.Inject

class EmptyRouter @Inject constructor(private val fragmentManager: FragmentManager) :
    EmptyContract.Router {
    override fun openAdd() {
        fragmentManager.beginTransaction().run {
            val fragment = AddFragment.newInstance()
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment, AddFragment.FRAGMENT_ADD_TAG)
            addToBackStack(AddFragment.FRAGMENT_ADD_TAG)
            commit()
        }
    }

    override fun openContacts() {
        fragmentManager.beginTransaction().run {
            val fragment = ContactsFragment.newInstance()
            replace(R.id.fragmentContainerView, fragment, ContactsFragment.FRAGMENT_CONTACTS_TAG)
            commit()
        }
    }
}