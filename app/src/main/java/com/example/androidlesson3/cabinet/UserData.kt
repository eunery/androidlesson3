package com.example.androidlesson3.cabinet

data class UserData(
    val user: User = User(names = "Иванов Иван Иванович", address = "Сахалин, ул. Пушкина, д. Колотушкина"),
    val balanceData : Balance = Balance(id = 1726872, amount = 1337.228, toPay = 10.0),
    val tariffs : List<Tariff> = mutableListOf(
        Tariff(id = 1, name = "Жаба (бесплатный тариф)", description = "Скорость до 15! Мбит/c", amount = 0.0),
        Tariff(id = 2, name = "Сосиска", description = "Скорость до 0.0001 Мбит/c", amount = 001.0)
    )
)
