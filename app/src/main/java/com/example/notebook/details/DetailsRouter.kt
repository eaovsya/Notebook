package com.example.notebook.details

import androidx.fragment.app.FragmentManager
import com.example.notebook.R
import com.example.notebook.addedit.edit.EditFragment
import com.example.notebook.db.Contact
import javax.inject.Inject

class DetailsRouter @Inject constructor(private val fragmentManager: FragmentManager) :
    DetailsContract.Router {

    override fun openEdit(contact: Contact) {
        fragmentManager.beginTransaction().run {
            val fragment = EditFragment.newInstance(contact)
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment, EditFragment.FRAGMENT_EDIT_TAG)
            addToBackStack(EditFragment.FRAGMENT_EDIT_TAG)
            commit()
        }
    }

    override fun back() {
        fragmentManager.popBackStack()
    }
}