package com.example.notebook.contacts

import com.example.notebook.db.Contact
import javax.inject.Inject

class ContactsPresenter @Inject constructor(
    private val router: ContactsContract.Router,
    private val interactor: ContactsContract.Interactor
) : ContactsContract.Presenter {

    private var view: ContactsContract.View? = null

    override fun bindView(view: ContactsContract.View) {
        this.view = view
    }

    override fun unBindView() {
        view = null
        interactor.dispose()
    }

    override fun onViewCreated(sortMode: SortMode) {
        interactor.getContacts(
            {
                if (it.isEmpty()) {
                    router.openEmpty()
                } else {
                    view?.showContacts(it)
                    view?.initSpinner()
                }
            },
            this::onError,
            sortMode
        )
    }

    override fun onItemClicked(contact: Contact) {
        router.openDetails(contact)
    }

    override fun onEditClicked(contact: Contact) {
        router.openEdit(contact)
    }

    override fun onDeleteClicked(contact: Pair<Contact, Int>) {
        interactor.deleteContact(contact.first, {
            view?.onItemDeleted(contact.second)
        }, this::onError)
    }

    override fun onSortModeChanged(mode: SortMode) {
        view?.sortContactsBy(mode)
    }

    override fun onAddContact() {
        router.openAdd()
    }

    override fun checkEmpty(itemCount: Int) {
        if (itemCount == 0) router.openEmpty()
    }

    private fun onError(error: Throwable) {
        error.message?.let { view?.showMessage(it) }
    }
}