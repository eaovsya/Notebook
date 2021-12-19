package com.example.notebook.contacts.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.notebook.db.Contact

class ContactsDiffUtil(
    private val oldContacts: List<Contact>,
    private val newContacts: List<Contact>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldContacts[oldItemPosition].id == newContacts[newItemPosition].id
    }

    override fun getOldListSize() = oldContacts.size

    override fun getNewListSize() = newContacts.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldContacts[oldItemPosition] == newContacts[newItemPosition]
    }
}