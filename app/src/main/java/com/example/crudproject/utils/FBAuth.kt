package com.example.crudproject.utils

import com.google.firebase.auth.FirebaseAuth

class FBAuth {

    /*
    *   java static과 같음 하지만 동반객체도 하나의 객체로 간주된다.
        때문에 이름을 붙일 수도 있고, interface를 구현할 수도 있다
    *
    * */
    companion object {

        private lateinit var auth: FirebaseAuth

        fun getUid() : String {

            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }

    }
}