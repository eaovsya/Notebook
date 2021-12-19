package com.example.notebook.contacts

import androidx.fragment.app.FragmentManager
import com.example.notebook.R
import com.example.notebook.addedit.add.AddFragment
import com.example.notebook.addedit.edit.EditFragment
import com.example.notebook.db.Contact
import com.example.notebook.details.DetailsFragment
import com.example.notebook.empty.EmptyFragment
import javax.inject.Inject

class ContactsRouter @Inject constructor(private val fragmentManager: FragmentManager) :
    ContactsContract.Router {

    override fun openDetails(contact: Contact) {
        fragmentManager.beginTransaction().run {
            val fragment = DetailsFragment.newInstance(contact)
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment, DetailsFragment.FRAGMENT_DETAILS_TAG)
            addToBackStack(DetailsFragment.FRAGMENT_DETAILS_TAG)
            commit()
        }
    }

    override fun openAdd() {
        fragmentManager.beginTransaction().run {
            val fragment = AddFragment.newInstance()
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment, AddFragment.FRAGMENT_ADD_TAG)
            addToBackStack(AddFragment.FRAGMENT_ADD_TAG)
            commit()
        }
    }

    override fun openEdit(contact: Contact) {
        fragmentManager.beginTransaction().run {
            val fragment = EditFragment.newInstance(contact)
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment, EditFragment.FRAGMENT_EDIT_TAG)
            addToBackStack(EditFragment.FRAGMENT_EDIT_TAG)
            commit()
        }
    }

    override fun openEmpty() {
        fragmentManager.beginTransaction().run {
            val fragment = EmptyFragment.newInstance()
            replace(R.id.fragmentContainerView, fragment, EmptyFragment.FRAGMENT_EMPTY_TAG)
            commit()
        }
    }
}