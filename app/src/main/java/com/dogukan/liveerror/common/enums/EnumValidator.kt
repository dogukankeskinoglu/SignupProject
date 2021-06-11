package com.dogukan.liveerror.common.enums

enum class EnumValidator(val constraint: Int){
    MinLengthEmail(5),
    MaxLengthEmail(50),
    MinLengthUsername(2),
    MaxLengthUsername(20),
    MinLengthPassword(7),
    MaxLengthPassword(40),
}
