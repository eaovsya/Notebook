package com.example.notebook.details

import com.example.notebook.db.Contact

interface DetailsContract {
    interface View {
        fun addProfilePic(picUrl: String?)
        fun addFullName(fullName: String)
        fun addNumbers(phoneNumbers: List<String?>?)
        fun addSocialMedia(socialMedia: List<String?>?)
        fun addDescription(description: String?)
    }

    interface Presenter {
        fun onBackPressed()
        fun onEditClicked(contact: Contact?)
        fun onViewCreated(contact: Contact?)
        fun bindView(view: View)
        fun unBindView()
    }

    interface Router {
        fun openEdit(contact: Contact)
        fun back()
    }
}