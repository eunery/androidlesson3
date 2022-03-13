package com.example.androidlesson3.cabinet

data class UserData(
    val user: User = User(names = "Иванов Иван Иванович", address = "Сахалин, ул. Пушкина, д. Колотушкина"),
    val balanceData : Balance = Balance(id = 1726872, amount = 182.21, toPay = 0.0),
    val tariffs : List<Tariff> = mutableListOf(
        Tariff(id = 1, name = "Улыбка (бесплатный тариф)", description = "Скорость до 100 Мбит/c", amount = 0.0),
        Tariff(id = 2, name = "Победа", description = "Скорость до 350 Мбит/c", amount = 400.0)
    )
)
