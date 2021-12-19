package com.example.notebook.details

import com.example.notebook.db.Contact
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val router: DetailsContract.Router) :
    DetailsContract.Presenter {

    private var view: DetailsContract.View? = null

    override fun onBackPressed() {
        router.back()
    }

    override fun onEditClicked(contact: Contact?) {
        if (contact != null) {
            router.openEdit(contact)
        }
    }

    override fun onViewCreated(contact: Contact?) {
        val firstName = contact?.firstName ?: ""
        var middleName =
            if (!contact?.middleName.isNullOrEmpty()) contact?.middleName.plus(" ") else null
        middleName = " ".plus(middleName ?: "")
        val lastName = contact?.lastName ?: ""
        view?.addProfilePic(contact?.picUrl)
        view?.addFullName(firstName.plus(middleName).plus(lastName))
        view?.addNumbers(contact?.phoneNumbers)
        view?.addSocialMedia(contact?.socialMedia)
        view?.addDescription(contact?.description)
    }

    override fun bindView(view: DetailsContract.View) {
        this.view = view
    }

    override fun unBindView() {
        view = null
    }
}