package com.example.notebook.empty

import com.example.notebook.utils.createDummies
import javax.inject.Inject

class EmptyPresenter @Inject constructor(
    private val router: EmptyContract.Router,
    private val interactor: EmptyContract.Interactor
) : EmptyContract.Presenter {

    private var view: EmptyContract.View? = null

    override fun bindView(view: EmptyContract.View) {
        this.view = view
    }

    override fun unBindView() {
        interactor.dispose()
        view = null
    }

    override fun onAddClicked() {
        router.openAdd()
    }

    override fun onAddDummiesClicled() {
        interactor.insertAll(createDummies(), {
            router.openContacts()
            onSuccess()
        }, this::onError)
    }

    private fun onError(error: Throwable) {
        error.message?.let { view?.showMessage(it) }
    }

    private fun onSuccess() {
        view?.showMessage("Dummies added!")
    }
}