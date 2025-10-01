package ru.kpfu.itis.springpractice.experiment.domain.model

data class Authorization(
    var isAuthorized: Boolean,
    var loggedInUser: User?
)