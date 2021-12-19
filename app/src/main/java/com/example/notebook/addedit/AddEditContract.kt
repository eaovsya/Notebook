package com.example.notebook.addedit

import com.example.notebook.db.Contact
import com.example.notebook.details.DetailsContract

interface AddEditContract : DetailsContract {
    interface View {
        fun shrinkAddFab()
        fun hideNestedFabs()
        fun extendAddFab()
        fun showNestedFabs()
        fun addSocialMediaItem(link: String? = "")
        fun addPhoneItem(phone: String? = "")
        fun showMessage(message: String)
        fun fillFirstName(firstName: String?)
        fun fillLastName(lastName: String?)
        fun fillMiddleName(middleName: String?)
        fun fillPicUrl(picUrl: String?)
        fun fillDescription(description: String?)

    }

    interface Presenter {
        fun bindView(view: View)
        fun unBindView()
        fun onViewCreated()
        fun onBackPressed()
        fun onConfirmAddClicked(contact: Contact)
        fun onConfirmEditClicked(contact: Contact)
        fun onAddFabClicked(isExtended: Boolean)
        fun onAddLinkClicked()
        fun onAddPhoneClicked()
        fun fillView(contact: Contact?)
    }

    interface Interactor {
        fun insertContact(
            contact: Contact,
            onSuccess: () -> Unit,
            onError: (Throwable) -> Unit
        )

        fun updateContact(
            contact: Contact,
            onSuccess: () -> Unit,
            onError: (Throwable) -> Unit
        )

        fun dispose()
    }

    interface Router {
        fun backWithChanges(contact: Contact)
        fun back()
        fun confirmBack()
    }
}