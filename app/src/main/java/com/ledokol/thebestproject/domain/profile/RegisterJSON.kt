package com.ledokol.thebestproject.domain.profile


data class RegisterJSON(
//    @field:Json(name = "user")
    val user: ProfileJSON,
//    @field:Json(name = "phone_confirmation")
    val phone_confirmation: ConfirmationPhoneAuth,
)