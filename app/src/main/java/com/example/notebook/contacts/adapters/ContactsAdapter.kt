package com.example.notebook.contacts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.contacts.SortMode
import com.example.notebook.databinding.ContactItemBinding
import com.example.notebook.db.Contact
import com.squareup.picasso.Picasso


class ContactsAdapter(
    private var contacts: MutableList<Contact>,
    private val listener: OnItemClickListener,
    private val onDeleteListener: OnDeleteClickListener,
    private val onEditListener: OnEditClickListener
) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    inner class ContactsViewHolder(binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val profilePic: ImageView = binding.profilePic
        val firstAndLastName: TextView = binding.firstAndLastName
        val phoneNumber: TextView = binding.phoneNumber

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(contacts[position])
                }
            }
            binding.buttonDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteListener.onDeleteClick(Pair(contacts[position], position))
                }
            }
            binding.buttonEdit.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onEditListener.onEditClick(contacts[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = ContactItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val currentItem = contacts[position]
        if (currentItem.picUrl != null) {
            Picasso.get().load(currentItem.picUrl).placeholder(holder.profilePic.drawable)
                .error(R.drawable.ic_baseline_error_64)
                .fit().centerCrop()
                .into(holder.profilePic)
        } else {
            holder.profilePic.setImageResource(R.drawable.ic_baseline_person_64)
        }
        holder.firstAndLastName.text =
            currentItem.firstName.plus(" ").plus(currentItem.lastName ?: "")
        holder.phoneNumber.text = currentItem.phoneNumbers?.first()
    }

    override fun getItemCount() = contacts.size


    interface OnItemClickListener {
        fun onItemClick(contact: Contact)
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(contact: Pair<Contact, Int>)
    }

    interface OnEditClickListener {
        fun onEditClick(contact: Contact)
    }

    fun updateContactListItems(contacts: List<Contact>) {
        val diffCallback = ContactsDiffUtil(this.contacts, contacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.contacts.clear()
        this.contacts.addAll(contacts)
        diffResult.dispatchUpdatesTo(this)
    }

    fun deleteContactListItem(position: Int) {
        val newContacts = contacts.toMutableList()
        newContacts.removeAt(position)
        val diffCallback = ContactsDiffUtil(contacts, newContacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        contacts.clear()
        contacts.addAll(newContacts)
        diffResult.dispatchUpdatesTo(this)
    }

    fun sortBy(mode: SortMode) {
        val newContacts = contacts.sortedBy {
            if (mode == SortMode.FIRSTNAME) it.firstName
            else it.lastName
        }
        updateContactListItems(newContacts)
    }
}