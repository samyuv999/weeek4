package com.vervenest.week3

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator


class UserInfo : Parcelable {
    var userName: String? = null
    var email: String? = null
    var gender: String? = null
    var dob: String? = null
    var time: String? = null


    var age: String? = null

    constructor() {}
    constructor(
        userName: String?,
        email: String?,
        gender: String?,
        age: String?,
        dob: String?,
        time: String?
    ) {
        this.userName = userName
        this.email = email
        this.gender = gender
        this.age = age
        this.dob = dob
        this.time = time
    }

    protected constructor(`in`: Parcel) {
        userName = `in`.readString()
        email = `in`.readString()
        gender = `in`.readString()
        age = `in`.readString()
        dob = `in`.readString()
        time = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(userName)
        dest.writeString(email)
        dest.writeString(gender)
        dest.writeString(age)
        dest.writeString(dob)
        dest.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", time='" + time + '\'' +
                ", age=" + age +
                '}'
    }

    companion object CREATOR : Creator<UserInfo> {
        override fun createFromParcel(parcel: Parcel): UserInfo {
            return UserInfo(parcel)
        }

        override fun newArray(size: Int): Array<UserInfo?> {
            return arrayOfNulls(size)
        }
    }


}
