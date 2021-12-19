package com.example.notebook.empty

import com.example.notebook.db.Contact

interface EmptyContract {
    interface View {
        fun showMessage(message: String)
//        fun showContacts(contacts: List<Contact>)
//        fun showMessage(message: String)
//        fun onItemDeleted(position: Int)
//        fun sortContactsBy(mode: SortMode)
//        fun showEmptyContacts()
//        fun hideEmptyContacts()
    }

    interface Presenter {
        fun bindView(view: View)
        fun unBindView()
        fun onAddClicked()
        fun onAddDummiesClicled()
//        fun onViewCreated()
//        fun onItemClicked(contact: Contact)
//        fun onEditClicked(contact: Contact)
//        fun onDeleteClicked(contact: Pair<Contact, Int>)
//        fun onSortModeChanged(mode: SortMode)
//        fun onAddContact()
    }

    interface Interactor {
        fun insertAll(
            contacts: List<Contact>,
            onSuccess: () -> Unit,
            onError: (Throwable) -> Unit
        )

        fun dispose()
//        fun getContacts(
//                onSuccess: (List<Contact>) -> Unit,
//        onError: (Throwable) -> Unit,
//        sortMode: SortMode
//        )
//
//        fun deleteContact(contact: Contact, onComplete: () -> Unit, onError: (Throwable) -> Unit)
//        fun dispose()
    }

    interface Router {
        fun openAdd()
        fun openContacts()
//        fun openDetails(contact: Contact)
//        fun openAdd()
//        fun openEdit(contact: Contact)
    }
}