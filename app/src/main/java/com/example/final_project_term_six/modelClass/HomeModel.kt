package com.example.final_project_term_six.modelClass

class HomeModel() {
    var title: String=""

        get() = field
        set(value) {
            field=value
        }
    var des: String=""
        get() = field
        set(value) {
            field=value
        }


    constructor(title: String, des: String):this() {
        this.title = title
        this.des = des
    }



}