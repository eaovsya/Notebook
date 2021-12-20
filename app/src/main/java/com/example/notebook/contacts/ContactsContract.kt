package com.example.notebook.contacts

import com.example.notebook.db.Contact

interface ContactsContract {
    interface View {
        fun showContacts(contacts: List<Contact>)
        fun showMessage(message: String)
        fun onItemDeleted(position: Int)
        fun sortContactsBy(mode: SortMode)
        fun initSpinner()
    }

    interface Presenter {
        fun bindView(view: View)
        fun unBindView()
        fun onViewCreated(sortMode: SortMode)
        fun onItemClicked(contact: Contact)
        fun onEditClicked(contact: Contact)
        fun onDeleteClicked(contact: Pair<Contact, Int>)
        fun onSortModeChanged(mode: SortMode)
        fun onAddContact()
        fun checkEmpty(itemCount: Int)
    }

    interface Interactor {
        fun getContacts(
            onSuccess: (List<Contact>) -> Unit,
            onError: (Throwable) -> Unit,
            sortMode: SortMode
        )

        fun deleteContact(contact: Contact, onComplete: () -> Unit, onError: (Throwable) -> Unit)
        fun dispose()
    }

    interface Router {
        fun openDetails(contact: Contact)
        fun openAdd()
        fun openEdit(contact: Contact)
        fun openEmpty()
    }
}