package com.example.notebook.addedit

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.notebook.R
import com.example.notebook.addedit.add.AddFragment
import com.example.notebook.contacts.ContactsFragment
import com.example.notebook.db.Contact
import com.example.notebook.details.DetailsFragment
import javax.inject.Inject

class AddEditRouter @Inject constructor(private val fragmentManager: FragmentManager) :
    AddEditContract.Router {

    override fun backWithChanges(contact: Contact) {
        val fragment = fragmentManager.findFragmentByTag(DetailsFragment.FRAGMENT_DETAILS_TAG)
        fragment?.arguments = Bundle().apply {
            putParcelable(DetailsFragment.DETAILS_CONTACT_EXTRA, contact)
        }
        fragmentManager.popBackStack()
    }

    override fun back() {
        fragmentManager.popBackStack()
    }

    override fun confirmBack() {
        if (fragmentManager.findFragmentByTag(ContactsFragment.FRAGMENT_CONTACTS_TAG) == null) {
            fragmentManager.popBackStack(
                AddFragment.FRAGMENT_ADD_TAG,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            fragmentManager.beginTransaction().run {
                val fragment = ContactsFragment.newInstance()
                replace(
                    R.id.fragmentContainerView,
                    fragment,
                    ContactsFragment.FRAGMENT_CONTACTS_TAG
                )
                commit()
            }
        } else {
            fragmentManager.popBackStack()
        }
    }

}