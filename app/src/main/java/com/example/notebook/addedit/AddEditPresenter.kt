package com.example.notebook.addedit

import android.database.sqlite.SQLiteConstraintException
import com.example.notebook.db.Contact
import javax.inject.Inject

class AddEditPresenter @Inject constructor(
    private val router: AddEditContract.Router,
    private val interactor: AddEditContract.Interactor
) : AddEditContract.Presenter {
    private var view: AddEditContract.View? = null

    override fun bindView(view: AddEditContract.View) {
        this.view = view
    }

    override fun unBindView() {
        view = null
        interactor.dispose()
    }

    override fun onViewCreated() {
        view?.shrinkAddFab()
        view?.hideNestedFabs()
    }

    override fun onBackPressed() {
        router.back()
    }

    override fun onConfirmAddClicked(contact: Contact) {
        if (contact.firstName.isNotEmpty()) {
            interactor.insertContact(contact, {
                router.confirmBack()
                onSuccess()
            }, this::onError)
        } else {
            view?.showMessage("At least enter first name")
        }
    }

    override fun onConfirmEditClicked(contact: Contact) {
        if (contact.firstName.isNotEmpty()) {
            interactor.updateContact(contact, {
                router.backWithChanges(contact)
                onSuccess()
            }, this::onError)
        } else {
            view?.showMessage("At least enter first name")
        }
    }

    override fun onAddFabClicked(isExtended: Boolean) {
        if (isExtended) {
            shrinkAndHideFabs()
        } else {
            extendAndShowFabs()
        }
    }

    override fun onAddLinkClicked() {
        view?.addSocialMediaItem()
        shrinkAndHideFabs()
    }

    override fun onAddPhoneClicked() {
        view?.addPhoneItem()
        shrinkAndHideFabs()
    }

    override fun fillView(contact: Contact?) {
        view?.fillFirstName(contact?.firstName)
        view?.fillLastName(contact?.lastName)
        view?.fillMiddleName(contact?.middleName)
        view?.fillPicUrl(contact?.picUrl)
        view?.fillDescription(contact?.description)
        contact?.socialMedia?.forEach {
            view?.addSocialMediaItem(it)
        }
        contact?.phoneNumbers?.forEach {
            view?.addPhoneItem(it)
        }
    }

    private fun shrinkAndHideFabs() {
        view?.shrinkAddFab()
        view?.hideNestedFabs()
    }

    private fun extendAndShowFabs() {
        view?.extendAddFab()
        view?.showNestedFabs()
    }

    private fun onError(error: Throwable) {
        when (error) {
            is SQLiteConstraintException -> view?.showMessage("Already exists")
            else -> error.message?.let { view?.showMessage(it) }
        }
    }

    private fun onSuccess() {
        view?.showMessage("Operation completed!")
    }
}