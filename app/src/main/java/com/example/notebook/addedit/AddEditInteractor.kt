package com.example.notebook.addedit

import com.example.notebook.db.Contact
import com.example.notebook.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AddEditInteractor @Inject constructor(private val repository: Repository) :
    AddEditContract.Interactor {

    private val disposables = CompositeDisposable()

    override fun insertContact(
        contact: Contact,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        repository.insertContact(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess()
            }, {
                onError(it)
            })
            .also {
                disposables.add(it)
            }
    }

    override fun updateContact(
        contact: Contact,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ): Unit = repository.updateContact(contact)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            onSuccess()
        }, {
            onError(it)
        })
        .let {
            disposables.add(it)
        }

    override fun dispose() {
        disposables.clear()
    }

}