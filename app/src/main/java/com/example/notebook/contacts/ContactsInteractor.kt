package com.example.notebook.contacts

import com.example.notebook.db.Contact
import com.example.notebook.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ContactsInteractor @Inject constructor(private val repository: Repository) :
    ContactsContract.Interactor {

    private val disposables = CompositeDisposable()

    override fun getContacts(
        onSuccess: (List<Contact>) -> Unit,
        onError: (Throwable) -> Unit,
        sortMode: SortMode
    ): Unit =
        repository.getAllContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                it.sortedBy { contact ->
                    if (sortMode == SortMode.FIRSTNAME)
                        contact.firstName
                    else contact.lastName
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onError(it)
            })
            .let {
                disposables.add(it)
            }


    override fun deleteContact(
        contact: Contact,
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit
    ): Unit =
        repository.deleteContact(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onComplete()
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