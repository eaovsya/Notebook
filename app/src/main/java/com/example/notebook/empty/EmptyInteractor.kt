package com.example.notebook.empty

import com.example.notebook.db.Contact
import com.example.notebook.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class EmptyInteractor @Inject constructor(private val repository: Repository) :
    EmptyContract.Interactor {

    private val disposables = CompositeDisposable()

    override fun insertAll(
        contacts: List<Contact>,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ): Unit = repository.insertAll(contacts)
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